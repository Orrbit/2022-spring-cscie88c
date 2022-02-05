package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

class UtilFunctionsTest extends StandardTest {
  
  "UtilFunctions" when {
    "maximum" should {
      "return maximum of two ints when first integer is greater" in {
        UtilFunctions.maximum(2, 1) should be (2)
      }
      "return maximum of two ints when second integer is greater" in {
        UtilFunctions.maximum(-5, -2) should be (-2)
      }
    }
    "average" should {
      "return average of two ints when both are positive" in {
        UtilFunctions.average(3, 1) should be (2)
      }
      "return average of two ints when both are negative" in {
        UtilFunctions.average(-15, -5) should be (-10)
      }
      "return average of two ints when one is negative and one is positive" in {
        UtilFunctions.average(-4, 50) should be (23)
      }
      "return average when result is not a whole number" in {
        UtilFunctions.average(1, 2) should be (1.5)
      }
    }


  }
}
