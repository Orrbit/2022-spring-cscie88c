package org.cscie88c.week9

import com.typesafe.scalalogging.LazyLogging
import scala.util.{ Failure, Success, Try }
import scala.io.Source
import org.cscie88c.config.{ ConfigUtils }
import pureconfig.generic.auto._

// write case class here

case class SimpleApp2Config(fileName: String, month: String)

// run with: sbt "runMain org.cscie88c.week9.SimpleApp2"
object SimpleApp2 extends LazyLogging {

  val APP_CONFIG_PATH = "org.cscie88c.simple-app-2"

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(s => s.contains(month))

  def main(args: Array[String]): Unit = {
    val SimpleApp2Config =
      ConfigUtils.loadAppConfig[SimpleApp2Config](APP_CONFIG_PATH)
    val numJan = lineStreamFromFile(SimpleApp2Config.fileName).map(
      monthLines(SimpleApp2Config.month)(_).size
    )
    numJan match {
      case Some(value) =>
        println(s"Transactions in ${SimpleApp2Config.month}: $value")
      case None =>
        println(s"No Transactions found for ${SimpleApp2Config.month}")
    }

  }
}
