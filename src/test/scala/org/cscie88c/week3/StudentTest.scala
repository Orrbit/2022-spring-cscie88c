package org.cscie88c.week3

import org.cscie88c.testutils.{ StandardTest }

class StudentTest extends StandardTest {
  "Student Management System" when {
    "creating a student" should {
      "have properties - name, email, subject and score" in {
        val testStudent = new Student("Liam Orr", "lio165@g.harvard.edu", "SE", 100)
        assert(testStudent.name.equals("Liam Orr"))
        assert(testStudent.email.equals("lio165@g.harvard.edu"))
        assert(testStudent.subject.equals("SE"))
        assert(testStudent.score == 100)
      }
    }

    "validating student email" should {
      "detect invalid email with no @ character" in {
        val testStudent = new Student("Michael Scofield", "noatsymbol.com", "Architecture", 100)
        assert(Student.validateEmail(testStudent) == false)
      }

      "detect a valid email with @ character" in {
        val testStudent = new Student("Lincoln Burrows", "lburrows@friver.com", "Criminal Justice", 20)
        assert(Student.validateEmail(testStudent) == true)
      }
    }

    "calculating average of a subject" should {
      "calculate correct average with empty list" in {
        val testStudents: List[Student] = List.empty
        assert(Student.averageScoreBySubject("", testStudents) == 0)
      }

      "calculate correct average with only one subject" in {
        val testStudents: List[Student] = 
           new Student("Michael Scofield", "mscofield@friver.com", "Architecture", 90) ::
           new Student("Lincoln Burrows", "lburrows@friver.com", "Architecture", 80) ::
           new Student("Alex Mahomes", "amahomes@friver.com", "Architecture", 70) ::
           new Student("James Whistler", "mscofield@friver.com", "Architecture", 50) :: Nil
        assert(Student.averageScoreBySubject("Architecture", testStudents) == 72.5)
      }

      "calculate correct average with many subjects" in {
        val testStudents: List[Student] = 
           new Student("Michael Scofield", "mscofield@friver.com", "Architecture", 90) ::
           new Student("Lincoln Burrows", "lburrows@friver.com", "Criminal Justice", 80) ::
           new Student("Alex Mahomes", "amahomes@friver.com", "Criminal Justice", 70) ::
           new Student("James Whistler", "mscofield@friver.com", "Criminal Justice", 60) :: Nil
        assert(Student.averageScoreBySubject("Criminal Justice", testStudents) == 70)
      }
    }

    "calculating average of a student" should {
      "calculate correct average with empty list" in {
        val testStudents: List[Student] = List.empty
        assert(Student.averageScoreByStudent(new Student("", "","",0), testStudents) == 0)
      }

      "calculate correct average with only one student" in {
        val testStudents: List[Student] = 
           new Student("Lincoln Burrows", "lburrows@friver.com", "Architecture", 80) ::
           new Student("Lincoln Burrows", "lburrows@friver.com", "History", 25) ::
           new Student("Lincoln Burrows", "lburrows@friver.com", "Engineering", 92) ::
           new Student("Lincoln Burrows", "lburrows@friver.com", "Mathematics", 70) :: Nil
        assert(Student.averageScoreByStudent(testStudents.head, testStudents) == 66.75)
      }

      "calculate correct average with many students" in {
        val testStudents: List[Student] = 
           new Student("Michael Scofield", "mscofield@friver.com", "Architecture", 90) ::
           new Student("Lincoln Burrows", "lburrows@friver.com", "Architecture", 80) ::
           new Student("Michael Scofield", "mscofield@friver.com", "Mathematics", 20) ::
           new Student("Lincoln Burrows", "lburrows@friver.com", "Mathematics", 70) :: Nil
        assert(Student.averageScoreByStudent(testStudents.head, testStudents) == 55)
      }
    }
  }

}
