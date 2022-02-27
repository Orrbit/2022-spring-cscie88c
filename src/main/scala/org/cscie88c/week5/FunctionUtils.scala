package org.cscie88c.week5

import scala.math

object FunctionUtils {

  case class CustomerTransaction(
      customerId: String,
      transactionDate: String,
      transactionAmount: Double
    )

  // Problem 1
  def colorToCode(color: String): (Int, Int, Int) = color match {
    case "Black"  => (0, 0, 0)
    case "White"  => (255, 255, 255)
    case "Red"    => (255, 0, 0)
    case "Lime"   => (0, 255, 0)
    case "Blue"   => (0, 0, 255)
    case "Yellow" => (255, 255, 0)
  }

  def fizzBuzzString(n: Int): String = n match {
    case x if x % 5 == 0 && x % 3 == 0 => "FizzBuzz"
    case x if x % 3 == 0               => "Fizz"
    case x if x % 5 == 0               => "Buzz"
    case _                             => n.toString
  }

  def fizzBuzz(n: Int): List[String] = (1 to n).toList.map(fizzBuzzString(_))

  // Problem 2
  def tanDegrees: PartialFunction[Double, Double] = {
    case x if x != -90 && x != 90 => Math.tan(x)
  }

  def totalHighValueTransactions(
      transactionList: List[CustomerTransaction]
    ): Double = transactionList
    .collect({
      case CustomerTransaction(_, _, amt) if amt > 100 => amt
    })
    .sum

  def flip2[A, B, C](f: (A, B) => C): (B, A) => C = (b: B, a: A) => f(a, b)

  def sampleList[A](fullList: List[A]): List[A] = fullList.take(5)

}
