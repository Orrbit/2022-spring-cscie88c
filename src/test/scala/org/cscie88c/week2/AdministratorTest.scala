package org.cscie88c.week2

import org.cscie88c.testutils.{ StandardTest }

// write unit tests for Administrator below
class AdministratorTest extends StandardTest {
  "Administrator" when {
    "instantiated" should {
      "have a huge budget for Tom Brady" in {
        val admin = new Administrator("Tom Brady", "tbrady@gmail.com", 23000000)
        admin.description.contains("$23000000") shouldBe true
      }
    }
  }
}
