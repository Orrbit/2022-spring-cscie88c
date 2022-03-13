package org.cscie88c.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

final case class CustomerTransaction(
  customerId: String,
  transactionDate: String,
  transactionAmount: Double
)

object CustomerTransaction {
  def apply(csv:String): Option[CustomerTransaction] = {
    val fields = csv.split(",")
    Try{
      CustomerTransaction(
        fields(0).toString,
        fields(1).toString,
        fields(2).toDouble
      )
    }.toOption
  }

  def readFromCSVFile(resourceName:String): List[CustomerTransaction] = {
    OptionUtils.lineStreamFromFile(resourceName) match {
      case Some(l: LazyList[String]) => l.map(CustomerTransaction(_)).collect{ case Some(c) => c}.toList
      case None => List.empty[CustomerTransaction]
    }
  }
}