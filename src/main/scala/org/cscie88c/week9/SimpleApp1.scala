package org.cscie88c.week9

import com.typesafe.scalalogging.LazyLogging
import scala.util.{ Failure, Success, Try }
import scala.io.Source

// run using: sbt "runMain org.cscie88c.week9.SimpleApp1 <args>"
object SimpleApp1 extends LazyLogging {

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] =
    Try {
      LazyList.from(Source.fromResource(fileName).getLines())
    }.toOption

  def monthLines(month: String)(lines: LazyList[String]): LazyList[String] =
    lines.filter(s => s.contains(month))

  def main(args: Array[String]): Unit = {
    val fileName = args(0)
    val numJan = lineStreamFromFile(fileName).map(monthLines("Jan")(_).size)
    numJan match {
      case Some(value) => println(s"Transactions in Jan: $value")
      case None        => println("No Transactions found for Jan")
    }

  }

}
