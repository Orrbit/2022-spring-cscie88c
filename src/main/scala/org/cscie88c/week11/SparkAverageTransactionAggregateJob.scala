package org.cscie88c.week11

import cats._
import cats.implicits._
import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{ LazyLogging }
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.SparkUtils
import pureconfig.generic.auto._
import org.apache.spark.sql.{ Dataset }
import org.apache.spark.sql.SaveMode
import scala.collection.immutable
import org.apache.spark.sql.DataFrame

case class SparkAverageTransactionAggregateJobConfig(
    name: String,
    masterUrl: String,
    inputPathTransaction: String,
    inputPathResponse: String,
    outputPathTransaction: String,
    outputPathResponseTransaction: String
  )

object SparkAverageTransactionAggregateJob extends LazyLogging {

  def main(args: Array[String]): Unit = {
    logger.info("XXXX: Start of Advanced SparkApp")
    implicit val appSettings =
      ConfigUtils.loadAppConfig[SparkAverageTransactionAggregateJobConfig](
        "org.cscie88c.spark-advanced-application"
      )
    val spark = SparkUtils.sparkSession(appSettings.name, appSettings.masterUrl)
    logger.info(s"settings: $appSettings")
    runJob(spark)
    spark.stop()
    logger.info("XXXX: Stopped Advanced SparkApp")
  }

  def runJob(
      spark: SparkSession
    )(implicit
      conf: SparkAverageTransactionAggregateJobConfig
    ): Unit = {
    val transactionDS: Dataset[RawTransaction] = loadTransactionData(spark)
    val responseDS: Dataset[RawResponse] = loadCampaignResponseData(spark)
    val averageTransactionById: Map[String, AverageTransactionAggregate] =
      aggregateDataWithMonoid(transactionDS)
    val customersInCampaign: Dataset[RawTransaction] =
      joinTransactionAndResponseData(responseDS, transactionDS)
    val averageTransactionForCampaign
        : Map[String, AverageTransactionAggregate] = aggregateDataWithMonoid(
      customersInCampaign
    )
    saveAverageTransactionByCustomerId(
      spark,
      averageTransactionById,
      conf.outputPathTransaction
    )
    saveAverageTransactionByCustomerId(
      spark,
      averageTransactionForCampaign,
      conf.outputPathResponseTransaction
    )
    // saveAverageTransactionAsParquet(spark,averageTransactionById, conf.outputPathTransaction)
  }

  def loadTransactionData(
      spark: SparkSession
    )(implicit
      conf: SparkAverageTransactionAggregateJobConfig
    ): Dataset[RawTransaction] = {
    import spark.implicits._
    spark
      .read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(conf.inputPathTransaction)
      .as[RawTransaction]
  }

  def loadCampaignResponseData(
      spark: SparkSession
    )(implicit
      conf: SparkAverageTransactionAggregateJobConfig
    ): Dataset[RawResponse] = {
    import spark.implicits._
    spark
      .read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(conf.inputPathResponse)
      .as[RawResponse]
  }

  def aggregateDataWithMonoid(
      transactionDS: Dataset[RawTransaction]
    ): Map[String, AverageTransactionAggregate] = {
    import transactionDS.sparkSession.implicits._
    transactionDS
      .map { rawTs =>
        Map(rawTs.customer_id -> AverageTransactionAggregate(rawTs))
      }
      .reduce(_ |+| _)
  }

  def joinTransactionAndResponseData(
      responseDS: Dataset[RawResponse],
      transactionDS: Dataset[RawTransaction]
    ): Dataset[RawTransaction] = {
    import transactionDS.sparkSession.implicits._
    responseDS
      .filter(_.response == 1)
      .joinWith(
        transactionDS,
        responseDS("customer_id") === transactionDS("customer_id"),
        "inner"
      )
      .map(_._2)
  }

  def saveAverageTransactionByCustomerId(
      spark: SparkSession,
      transactionsById: Map[String, AverageTransactionAggregate],
      path: String
    ): Unit = {
    import spark.implicits._
    transactionsById
      .map(avgTrans =>
        WritableRow(avgTrans._1, avgTrans._2.totalAmount / avgTrans._2.count)
      )
      .toSeq
      .toDF("customer_id", "average_amt")
      .coalesce(1)
      .write
      .format("csv")
      .option("header", "true")
      .mode("overwrite")
      .save(path)

  }

  // def saveAverageTransactionAsParquet(spark: SparkSession, transactionsById: Map[String,AverageTransactionAggregate], path: String): Unit = ???
}
