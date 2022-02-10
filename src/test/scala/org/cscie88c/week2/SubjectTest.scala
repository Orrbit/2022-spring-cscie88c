package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Subject below

class SubjectTest extends StandardTest {
  "Subject" when {
      "apply" should {
          "return a subject with all fields of subject id 1" in {
              val sub = Subject("1,Physics,true")
              sub.id should be (1)
              sub.name should be ("Physics")
              sub.isStem should be (true)
          }

          "return a subject with all fields of subject id 2" in {
              val sub = Subject("2,Chemistry,true")
              sub.id should be (2)
              sub.name should be ("Chemistry")
              sub.isStem should be (true)
          }
      }
  }
}
