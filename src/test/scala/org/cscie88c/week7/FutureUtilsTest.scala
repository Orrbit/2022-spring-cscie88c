package org.cscie88c.week7

import org.cscie88c.testutils.{FuturesTest}
import scala.concurrent.Future
import cats.instances.future

class FutureUtilsTest extends FuturesTest {
  
  "Any future function" should {
    "return a future assertion" in {
      def futureAdd2(x: Int) = Future(x + 2)
      futureAdd2(5).map { x =>
        x shouldBe 7
      }
    }
  }

  "FutureFunctions" when {
    "calling creditScoreAPI" should {
      "return a credit score greater than or equal to 300" in {
        FutureUtils.creditScoreAPI(1).map { x => 
          x >= 300 should be(true)
        }
      }
      "return a credit score less than 800" in {
        FutureUtils.creditScoreAPI(1).map { x => 
          x < 800 should be(true)
        }
      }
    }

    "calling futureFactorial" should {
      "return correct result for 4" in {
        FutureUtils.futureFactorial(4).map( _ should be(24))
      }
    }

    "calling futurePermutation" should {
      "return correct permutation" in {
        FutureUtils.futurePermuations(5, 3).map(_ should be(60))
      }
    }

    "calling asyncAverageCreditScore" should {
      "return an average credit score greater than or equal to 300" in {
        FutureUtils.asyncAverageCreditScore(1 :: 2 :: 3 :: Nil).map { x => 
          x >= 300 should be(true)
        }
      }
      "return a credit score less than 800" in {
        FutureUtils.asyncAverageCreditScore(1 :: 2 :: 3 :: Nil).map { x => 
          x < 800 should be(true)
        }
      }
    }
  }
}
