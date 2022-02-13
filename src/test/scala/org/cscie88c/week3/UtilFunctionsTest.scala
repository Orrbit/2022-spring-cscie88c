package org.cscie88c.week3

import org.cscie88c.testutils.{ StandardTest }

class UtilFunctionsTest extends StandardTest {
  "UtilFunctions" when {
    "with pythTriplesUpto100" should {
      "verify first element in list is a pythagorean triple" in {
        val e1 = UtilFunctions.pythTriplesUpto100.head
        UtilFunctions.pythTest(e1._1, e1._2, e1._3) should be (true)
      }

      "verify second element in list is a pythagorean triple" in {
        val e2 = UtilFunctions.pythTriplesUpto100.tail.head
        UtilFunctions.pythTest(e2._1, e2._2, e2._3) should be (true)
      }

      "verify third element in list is a pythagorean triple" in {
        val e3 = UtilFunctions.pythTriplesUpto100.tail.tail.head
        UtilFunctions.pythTest(e3._1, e3._2, e3._3) should be (true)
      }
    }
  }
}
