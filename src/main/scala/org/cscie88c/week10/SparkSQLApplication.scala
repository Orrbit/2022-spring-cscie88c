package org.cscie88c.week10

import org.apache.spark.sql.SparkSession
import com.typesafe.scalalogging.{ LazyLogging }
import org.cscie88c.config.{ ConfigUtils }
import org.cscie88c.utils.{ SparkUtils }
import org.apache.spark.sql.{ DataFrame, Dataset, Row }
import pureconfig.generic.auto._
import org.apache.spark.sql.functions.{ when }

// run with: sbt "runMain org.cscie88c.week10.SparkSQLApplication"
object SparkSQLApplication {

  val APP_CONFIG_PATH = "org.cscie88c.spark-ds-application"

  def main(args: Array[String]): Unit = {
    implicit val conf: SparkDSConfig = readConfig()
    val spark = SparkUtils.sparkSession(conf.name, conf.masterUrl)
    val transactionDF = loadData(spark)
    val augmentedTransactionsDF = addCategoryColumn(transactionDF)
    augmentedTransactionsDF.createOrReplaceTempView("transactions")
    val sparkSQL =
      "SELECT category, sum(tran_amount) FROM transactions GROUP BY category"
    val totalsByCategoryDF = spark.sql(sparkSQL)
    printTransactionTotalsByCategory(totalsByCategoryDF)
    spark.stop()
  }

  def readConfig(): SparkDSConfig =
    ConfigUtils.loadAppConfig[SparkDSConfig](APP_CONFIG_PATH)

  def loadData(spark: SparkSession)(implicit conf: SparkDSConfig): DataFrame =
    spark
      .read
      .format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(conf.transactionFile)

  def addCategoryColumn(raw: DataFrame): DataFrame = {
    import org.apache.spark.sql.functions.when
    import org.apache.spark.sql.functions.col
    raw.withColumn(
      "category",
      when(col("tran_amount") > 80, "High").otherwise("Standard")
    )
  }

  def printTransactionTotalsByCategory(df: DataFrame): Unit =
    df.foreach(println)

}
