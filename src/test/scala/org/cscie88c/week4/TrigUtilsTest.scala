package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class TrigUtilsTest extends StandardTest {

  "TrigUtils" when {
    "calling sin" should {
      "return the correct value for 90" in {
        TrigUtils.sinDegrees(90) should be(1.0 +- .000001)
      }

      "return the correct value for 0" in {
        TrigUtils.sinDegrees(0) should be(0.0 +- .000001)
      }
    }

    "calling cos" should {
      "return the correct value for 90" in {
        TrigUtils.cosDegrees(90) should be(0.0 +- .000001)
      }

      "return the correct value for 0" in {
        TrigUtils.cosDegrees(0) should be(1.0 +- .000001)
      }
    }

    "calling squared" should {
      "return the correct value for a positive number" in {
        TrigUtils.squared(25) should be(625)
      }

      "return the correct value for a negative number" in {
        TrigUtils.squared(-4) should be(16)
      }
    }
  }
}
