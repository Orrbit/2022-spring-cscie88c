package org.cscie88c.week10

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{ LazyLogging }
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.{ SparkUtils }
import org.apache.spark.sql.{ Dataset }
import pureconfig.generic.auto._

// write config case class below
case class SparkDSConfig(
    name: String,
    masterUrl: String,
    transactionFile: String
  )

// run with: sbt "runMain org.cscie88c.week10.SparkDSApplication"
object SparkDSApplication {

  val APP_CONFIG_PATH = "org.cscie88c.spark-ds-application"

  def main(args: Array[String]): Unit = {
    implicit val conf: SparkDSConfig = readConfig()
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)
    val transactionDS = loadData(spark)
    val totalsByCategoryDS = transactionTotalsByCategory(spark, transactionDS)
    printTransactionTotalsByCategory(totalsByCategoryDS)
    spark.stop()
  }

  def readConfig(): SparkDSConfig =
    ConfigUtils.loadAppConfig[SparkDSConfig](APP_CONFIG_PATH)

  def loadData(
      spark: SparkSession
    )(implicit
      conf: SparkDSConfig
    ): Dataset[CustomerTransaction] = {
    import spark.implicits._
    spark
      .read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(conf.transactionFile)
      .as[RawTransaction]
      .map(CustomerTransaction(_))
  }
  def transactionTotalsByCategory(
      spark: SparkSession,
      transactions: Dataset[CustomerTransaction]
    ): Dataset[(String, Double)] = {
    import spark.implicits._
    transactions
      .groupByKey(c => c.transactionCategory)
      .mapValues(_.transactionAmount)
      .reduceGroups((a, b) => a + b)
  }

  def printTransactionTotalsByCategory(ds: Dataset[(String, Double)]): Unit =
    ds.foreach(println)
}
