package org.cscie88c.week2

import org.cscie88c.testutils.{StandardTest}

// write unit tests for Student below

class StudentTest extends StandardTest {
  "Student" when {
      "apply" should {
          "return a student with all fields of student id 1" in {
            val stu = Student("1,Emmy,Conrart,econrart0@gizmodo.com,Male,China")
            stu.id should be (1)
            stu.firstName should be ("Emmy")
            stu.lastName should be ("Conrart")
            stu.email should be ("econrart0@gizmodo.com")
            stu.gender should be ("Male")
            stu.country should be ("China")
          }

          "return a student with all fields of student id 2" in {
            val stu = Student("2,Marin,Blasoni,mblasoni1@edublogs.org,Female,United States")
            stu.id should be (2)
            stu.firstName should be ("Marin")
            stu.lastName should be ("Blasoni")
            stu.email should be ("mblasoni1@edublogs.org")
            stu.gender should be ("Female")
            stu.country should be ("United States")
          }
      }

      "studentNamesByCountry" should {
          "return no students of Canada" in {
            val canNames = Student.studentNamesByCountry("Canada")
            canNames.size should be (0)
          }
          "return all students of United States" in {
            val usNames = Student.studentNamesByCountry("United States")
            usNames should contain ("Marin Blasoni")
            usNames should contain ("Delmore Scriver")
          }
          "return all students of China" in {
            val chinaNames = Student.studentNamesByCountry("China")
            chinaNames should contain ("Emmy Conrart")
            chinaNames should contain ("Jesse Chismon")
            chinaNames should contain ("Jocelyn Blaxlande")
          }
      }

      "studentTotalsByCountry" should {
          "return no students of Canada" in {
            val canNum = Student.studentTotalsByCountry("Canada")
            canNum should be (0)
          }
          "return all students of United States" in {
            val usNum = Student.studentTotalsByCountry("United States")
            usNum should be (2)
          }
          "return all students of China" in {
            val chinaNum = Student.studentTotalsByCountry("China")
            chinaNum should be (3)
          }
      }
  }
}
