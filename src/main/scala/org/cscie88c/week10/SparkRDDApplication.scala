package org.cscie88c.week10

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{ LazyLogging }
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.{ SparkUtils }
import org.apache.spark.rdd.{ PairRDDFunctions, RDD }
import pureconfig.generic.auto._
import org.cscie88c.week10.CustomerTransaction
import java.time.format.DateTimeFormatter

case class SparkRDDConfig(
    name: String,
    masterUrl: String,
    transactionFile: String
  )

// run with: sbt "runMain org.cscie88c.week10.SparkRDDApplication"
object SparkRDDApplication {

  val APP_CONFIG_PATH = "org.cscie88c.spark-rdd-application"

  // application entry point
  def main(args: Array[String]): Unit = {
    implicit val conf: SparkRDDConfig = readConfig()
    val spark = SparkUtils.sparkSession(
      conf.name,
      conf.masterUrl
    )
    val rddLines = loadData(spark)
    val rddTransactions = lineToTransactions(
      rddLines
    )
    val yearlyTransactionsRDD = transactionsAmountsByYear(
      rddTransactions
    )
    printTransactionsAmountsByYear(yearlyTransactionsRDD)
    spark.stop()
  }

  def readConfig(): SparkRDDConfig =
    ConfigUtils.loadAppConfig[SparkRDDConfig](APP_CONFIG_PATH)

  def loadData(
      spark: SparkSession
    )(implicit
      conf: SparkRDDConfig
    ): RDD[String] = spark.sparkContext.textFile(conf.transactionFile)

  def lineToTransactions(lines: RDD[String]): RDD[CustomerTransaction] =
    lines.map(CustomerTransaction(_)).collect {
      case Some(n: CustomerTransaction) => n
    }

  def transactionsAmountsByYear(
      transactions: RDD[CustomerTransaction]
    ): RDD[(String, Double)] = {
    val get_year_of_date: String => String = s =>
      s.slice(s.lastIndexOf("-") + 1, s.size)
    transactions
      .map(f => (get_year_of_date(f.transactionDate), f.transactionAmount))
      .reduceByKey((a, b) => a + b)
  }

  def printTransactionsAmountsByYear(
      transactions: RDD[(String, Double)]
    ): Unit = transactions.foreach(println)
}
