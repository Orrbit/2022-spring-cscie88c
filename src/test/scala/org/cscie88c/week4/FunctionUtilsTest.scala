package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class FunctionUtilsTest extends StandardTest {

  "FunctionUtils" when {

    def add5(x: Int) = x + 5

    "calling applyNtimes" should {

      "return the correct value on init value 0, 3 times" in {
        FunctionUtils.applyNtimes(3)(0)(add5) should be(15)
      }

      "return the correct value on init value 20, 3 times" in {
        FunctionUtils.applyNtimes(3)(20)(add5) should be(35)
      }

      "return the correct value on init value 5, 10 times" in {
        FunctionUtils.applyNtimes(10)(5)(add5) should be(55)
      }

    }

    "calling deferredExecutor" should {

      "return the correct value" in {
        FunctionUtils.deferredExecutor("CPU Pool")(add5)(4) should be(9)
      }

    }
  }

}
