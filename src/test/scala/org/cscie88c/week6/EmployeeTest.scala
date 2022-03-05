package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class EmployeeTest extends StandardTest {
  "Employee" should {

    "have a default sort order" in {
      val testEmps = Employee("abc", 18, 90000) :: Employee(
        "ghi",
        20,
        40000
      ) :: Employee("def", 50, 20000) :: Employee("jkl", 70, 30000) :: Nil
      val expectedEmps = Employee("abc", 18, 90000) :: Employee(
        "def",
        50,
        20000
      ) :: Employee("ghi", 20, 40000) :: Employee("jkl", 70, 30000) :: Nil
      Employee.defaultSortEmployees(testEmps) should be(expectedEmps)
    }

    "sort employees by salary" in {
      val testEmps = Employee("abc", 18, 90000) :: Employee(
        "ghi",
        20,
        40000
      ) :: Employee("def", 50, 20000) :: Employee("jkl", 70, 30000) :: Nil
      val expectedEmps = Employee("abc", 18, 90000) :: Employee(
        "ghi",
        20,
        40000
      ) :: Employee("jkl", 70, 30000) :: Employee("def", 50, 20000) :: Nil
      Employee.sortEmployeesBySalary(testEmps) should be(expectedEmps)
    }
  }
}
