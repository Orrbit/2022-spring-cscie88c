package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Faculty below
class FacultyTest extends StandardTest {
  "Faculty" when {
    "instantiated" should {
      "have a course id property for Ms. Frizzle" in {
        val fac = new Faculty("Ms. Frizzle", "mfrizzle@gmail.com", "1-sci")
        fac.description.contains("1-sci") shouldBe true
      }
    }
  }
}
