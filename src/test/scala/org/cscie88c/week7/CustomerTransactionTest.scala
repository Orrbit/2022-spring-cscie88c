package org.cscie88c.week7

import org.cscie88c.testutils.{ StandardTest }
import scala.util.{Try, Success, Failure}
import org.cscie88c.week7.CustomerTransaction

class CustomerTransactionTest extends StandardTest {
  "CustomerTransaction" should {
    "load and clean raw CSV data file" in {
      CustomerTransaction.apply("id1,customer-A,84.5") should be(Some(CustomerTransaction("id1","customer-A",84.5)))
    }

    "loads with None option in case of incorrect format in csv string" in {
      CustomerTransaction.apply("id1,customer-A,84.5x") should be(None)
    }
  }

  "readFromCSVFile" should {
    "load and clean entire CSV file" in {
      CustomerTransaction.readFromCSVFile("data/dirty-retail-data-sample.csv").length should be(5)
    }
  }

}
