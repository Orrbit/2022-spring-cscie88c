package org.cscie88c.week4

import org.cscie88c.testutils.{ StandardTest }

class ListUtilsTest extends StandardTest {
  "ListUtils" when {
    "calling ones" should {
      "return the correct value" in {
        val ones = ListUtils.ones(100)
        val onesActuallyOne = ones.filter(_ == 1.0)
        ones should equal(onesActuallyOne)
        ones.size should be(100)
      }
    }

    "calling zeros" should {
      "return the correct value" in {
        val zeros = ListUtils.zeros(100)
        val zerosActuallyOne = zeros.filter(_ == 0.0)
        zeros should equal(zerosActuallyOne)
        zeros.size should be(100)
      }
    }

    "calling charCounts" should {
      "return correct mapping counts for 'Hello world'" in {
        val expected: Map[Char, Int] = Map(
          'e' -> 1,
          'l' -> 3,
          'H' -> 1,
          'r' -> 1,
          'w' -> 1,
          'o' -> 2,
          'd' -> 1
        )
        ListUtils.charCounts("Hello world") should be(expected)
      }

      "return correct mapping counts for a panagram" in {
        val actualPanagramCounts =
          ListUtils.charCounts("The quick brown fox jumps over the lazy dog");
        val actualChars = actualPanagramCounts.keySet.map(_.toLower)
        actualChars should be(('a' to 'z').toSet)
      }
    }

    "calling topN" should {
      "return correct top 2 frequencies for 'Hello world'" in {
        val helloWorldMap = Map(
          'e' -> 1,
          'l' -> 3,
          'H' -> 1,
          'r' -> 1,
          'w' -> 1,
          'o' -> 2,
          'd' -> 1
        )
        val expected = Map(
          'l' -> 3,
          'o' -> 2
        )
        ListUtils.topN(2)(helloWorldMap) should be(expected)
      }
    }

  }

}
