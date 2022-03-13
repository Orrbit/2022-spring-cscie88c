package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import scala.util.{ Failure, Success, Try }

class OptionUtilsTest extends StandardTest {
  "OptionUtils" when {
    "calling fileCharCount" should {
      "return the correct number of characters in a valid file" in {
        OptionUtils
          .fileCharCount("data/dirty-retail-data-sample.csv")
          .get should be(204)
      }
    }

    "calling charCountAsString" should {
      "return the correct string in a valid file" in {
        OptionUtils.charCountAsString(
          "data/dirty-retail-data-sample.csv"
        ) should be("number of characters: 204")
      }

      "return the correct string in an invalid file" in {
        OptionUtils.charCountAsString(
          "data/dirty-retail-data-sample.csv-x"
        ) should be("error opening file")
      }
    }
  }

}
