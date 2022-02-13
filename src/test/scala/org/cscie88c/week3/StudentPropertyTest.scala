package org.cscie88c.week3
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._

class StudentPropertyTest
    extends AnyFunSuite
       with Matchers
       with ScalaCheckPropertyChecks {

  val studentGen: Gen[Student] = for {
    name <- Gen.alphaStr
    email <- Gen.alphaStr
    subject <- Gen.oneOf("Math", "English", "Science")
    score <- Gen.chooseNum(0, 100)
  } yield Student(name, email, subject, score)

  val studentListGen: Gen[List[Student]] = Gen.listOf(studentGen)

  test("description contains name and email") {
    forAll(studentGen) { (s: Student) =>
      s.description.contains(s.name) && s
        .description
        .contains(s.email) shouldBe true
    }
  }

  test("average score by subject does not exceed 100") {
    forAll("Math", studentListGen) {
      (subject: String, students: List[Student]) =>
        Student.averageScoreBySubject(subject, students) <= 100
    }
  }
}
