package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for University employee below
class UniversityEmployeeTest extends StandardTest {
  "UniversityEmployee" when {
    "instantiated" should {
      "have a name property for Bruce Lee" in {
        val emp = new UniversityEmployee("Bruce Lee", "brucel@gmail.com")
        emp.description.contains("Bruce Lee") shouldBe true
      }

      "have an email property for Bruce Lee" in {
        val emp = new UniversityEmployee("Bruce Lee", "brucel@gmail.com")
        emp.description.contains("brucel@gmail.com") shouldBe true
      }

      "have a name property for Tom Cruise" in {
        val emp = new UniversityEmployee("Tom Cruise", "tomc@gmail.com")
        emp.description.contains("Tom Cruise") shouldBe true
      }

      "have an email property for Tom Cruise" in {
        val emp = new UniversityEmployee("Tom Cruise", "tomc@gmail.com")
        emp.description.contains("tomc@gmail.com") shouldBe true
      }
    }
  }
}
