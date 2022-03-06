package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class AddableTypeclassTest extends StandardTest {

  "AddableAggregator" should {
    "sum a list of integers" in {
      val testList = 2 :: 5 :: 10 :: 3 :: Nil
      AddableAggregator.sumWithAddable(testList) should be(20)
    }
    "sum a list of booleans" in {
      val testList = false :: false :: true :: false :: Nil
      AddableAggregator.sumWithAddable(testList) should be(true)
    }
    "sum a list of employees" in {
      val testList =
        Employee("ken", 25, 80000) :: Employee("burns", 35, 90000) :: Nil
      AddableAggregator.sumWithAddable(testList) should be(
        Employee("ken, burns", 60, 170000)
      )
    }
  }
}
