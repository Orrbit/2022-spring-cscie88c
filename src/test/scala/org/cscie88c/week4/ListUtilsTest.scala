package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class ListUtilsTest extends StandardTest {
  "ListUtils" when {
    "calling ones" should {
      "return the correct value" in {
        val ones = ListUtils.ones(100)
        val onesActuallyOne = ones.filter(_ == 1.0)
        ones should equal(onesActuallyOne)
        ones.size should be(100)
      }
    }

    "calling zeros" should {
      "return the correct value" in {
        val zeros = ListUtils.zeros(100)
        val zerosActuallyOne = zeros.filter(_ == 0.0)
        zeros should equal(zerosActuallyOne)
        zeros.size should be(100)
      }
    }
    
  }

}
