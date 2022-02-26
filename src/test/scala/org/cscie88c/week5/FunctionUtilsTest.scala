package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import FunctionUtils.CustomerTransaction

// run using: sbt "testOnly org.cscie88c.week5.FunctionUtilsTest"
class FunctionUtilsTest extends StandardTest {
  "FunctionUtils" when {
    // Problem 1 unit tests
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
        // write unit tests here
      }
    }

    // Problem 2 unit tests

    // Problem 3 unit tests

    // Bonus unit tests
  }

}
