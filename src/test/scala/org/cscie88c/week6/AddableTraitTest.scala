package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class AddableTraitTest extends StandardTest {

  "plus" should {

    "add two MyInt values correctly" in {
      MyInt(5).plus(MyInt(3)).value should be(8)
    }

    "add two MyBool values correctly" in {
      MyBool(true).plus(MyBool(false)).value should be(true)
      MyBool(true).plus(MyBool(true)).value should be(true)
      MyBool(false).plus(MyBool(false)).value should be(false)
    }
  }
}
