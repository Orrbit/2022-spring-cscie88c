package org.cscie88c.week3

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._
import scala.annotation.meta.param

class UtilFunctionsPropertyTest
    extends AnyFunSuite
       with Matchers
       with ScalaCheckPropertyChecks {

  val triplesGen: Gen[(Int, Int, Int)] =
    Gen.oneOf(UtilFunctions.pythTriplesUpto100)

  test("mult2 result test") {
    forAll { (x: Int, y: Int) =>
      UtilFunctions.mult2(x, y) shouldBe x * y
    }
  }

  test("mult2 maintains distributive property") {
    forAll { (x: Int, y: Int, z: Int) =>
      UtilFunctions.mult2(x, y + z) shouldBe UtilFunctions.mult2(
        x,
        y
      ) + UtilFunctions.mult2(x, z)
    }
  }

  test("pythTriplesUpto100 of (x,y,z) is also a pythTriple of (y,x,z)") {
    forAll(triplesGen) { (pair: (Int, Int, Int)) =>
      UtilFunctions.pythTest(pair._2, pair._1, pair._3) shouldBe true
    }
  }

  test("pythTriplesUpto100 of (x,y,z) is also a pythTriple of (2y,2x,2z)") {
    forAll(triplesGen) { (pair: (Int, Int, Int)) =>
      UtilFunctions.pythTest(
        pair._2 * 2,
        pair._1 * 2,
        pair._3 * 2
      ) shouldBe true
    }
  }
}
