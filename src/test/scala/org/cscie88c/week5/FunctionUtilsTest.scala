package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import FunctionUtils.CustomerTransaction

// run using: sbt "testOnly org.cscie88c.week5.FunctionUtilsTest"
class FunctionUtilsTest extends StandardTest {
  "FunctionUtils" when {
    "calling colorToCode" should {
      "return the correct value for white" in {
        FunctionUtils.colorToCode("White") should be((255, 255, 255))
      }

      "return the correct value for lime" in {
        FunctionUtils.colorToCode("Lime") should be((0, 255, 0))
      }
    }

    "calling fizzBuzzString" should {
      "return the correct when divisable by 3 and 5" in {
        FunctionUtils.fizzBuzzString(15) should be("FizzBuzz")
      }
      "return the correct when divisable by only 3" in {
        FunctionUtils.fizzBuzzString(9) should be("Fizz")
      }
      "return the correct when divisable by only 5" in {
        FunctionUtils.fizzBuzzString(10) should be("Buzz")
      }
      "return the correct when divisable by neither 3 nor 5" in {
        FunctionUtils.fizzBuzzString(2) should be("2")
      }
    }

    "calling fizzBuzz" should {
      "return the correct value" in {
        val expected: List[String] =
          "1" :: "2" :: "Fizz" :: "4" :: "Buzz" :: "Fizz" :: Nil
        FunctionUtils.fizzBuzz(6) should be(expected)
      }
    }

    "calling tanDegrees" should {
      "return not defined at 90" in {
        FunctionUtils.tanDegrees.isDefinedAt(90) should be(false)
      }

      "return not defined at -90" in {
        FunctionUtils.tanDegrees.isDefinedAt(-90) should be(false)
      }

      "return defined at 0" in {
        FunctionUtils.tanDegrees.isDefinedAt(0) should be(true)
      }
    }

    "calling totalHighValueTransactions" should {
      "return only sum of high value transactions" in {
        val testTransactions: List[CustomerTransaction] =
          CustomerTransaction("abc", "01-02-2022", 1000) :: CustomerTransaction(
            "abc",
            "01-02-2022",
            101
          ) :: CustomerTransaction(
            "efg",
            "01-02-2022",
            100
          ) :: CustomerTransaction("hij", "01-02-2022", 17) :: Nil
        FunctionUtils.totalHighValueTransactions(testTransactions) should be(
          1101
        )
      }
    }

    "calling flip2" should {
      "return the correct value when flipped variables" in {
        FunctionUtils.flip2(Math.pow)(5, 2) should be(Math.pow(2, 5))
      }
    }

    "calling sampleList" should {
      "return a sample list of 5 Ints" in {
        val in: List[Int] = (1 to 10).toList
        val expected: List[Int] = (1 to 5).toList
        FunctionUtils.sampleList(in) should be(expected)
      }
      "return a sample list of 5 Strings" in {
        val in: List[String] = ('a' to 'z').toList.map(c => c.toString)
        val expected: List[String] = ('a' to 'e').toList.map(c => c.toString)
        FunctionUtils.sampleList(in) should be(expected)
      }
    }
  }

}
