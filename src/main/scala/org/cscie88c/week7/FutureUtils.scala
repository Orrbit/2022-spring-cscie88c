package org.cscie88c.week7

import scala.concurrent.{ Future }
import scala.util.{ Failure, Success, Try }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.parallel.CollectionConverters._
import scala.util.Random

object FutureUtils {

  val rnd = new Random()

  def creditScoreAPI(applicantId: Int): Future[Int] = Future(
    300 + rnd.nextInt(500)
  )

  def printCreditScore(
      applicantId: Int
    ): Unit = creditScoreAPI(applicantId).onComplete({
    case Success(value) => s"The credit score for $applicantId is: $value"
    case Failure(_)     => s"Cannot get credit score for $applicantId at this time"
  })

  def passedCreditCheck(applicantId: Int): Future[Boolean] =
    creditScoreAPI(applicantId).map(_ > 600)

  def futureFactorial(n: Int): Future[Int] = Future(
    (1 to n).toList.reduce((acc, curr) => acc * curr)
  )

  def futurePermuations(n: Int, r: Int): Future[Int] =
    for {
      x <- futureFactorial(n)
      y <- futureFactorial(n - r)
    } yield (x / y)

  def asyncAverageCreditScore(idList: List[Int]): Future[Double] =
    Future
      .traverse(idList)(creditScoreAPI(_))
      .map(allScoresFuture =>
        allScoresFuture.sum.toDouble / allScoresFuture.length.toDouble
      )

  def slowMultiplication(x: Long, y: Long): Long = {
    Thread.sleep(1000)
    x * y
  }

  def concurrentFactorial(n: Long): Long =
    (1L to n).toList.par.reduce((acc, curr) => slowMultiplication(acc, curr))

  def sequentialFactorial(n: Long): Long =
    (1L to n).toList.reduce((acc, curr) => slowMultiplication(acc, curr))

}
