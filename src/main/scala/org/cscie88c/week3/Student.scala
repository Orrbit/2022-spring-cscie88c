package org.cscie88c.week3

final case class Student(
    name: String,
    email: String,
    subject: String,
    score: Int
  ) {
  def description: String =
    s"name: ${name}, email: ${email}, subject: ${subject}, score: ${score}"
}

object Student {

  def validateEmail(student: Student): Boolean = student.email.contains('@')

  def averageScoreBySubject(
      subject: String,
      studentList: List[Student]
    ): Double = {
      val studentScoresInSubject = studentList.filter(s => s.subject.equals(subject)).map(s => s.score)
      if (studentScoresInSubject.length != 0) 
        studentScoresInSubject.sum.toDouble / studentScoresInSubject.length.toDouble
      else
        0
    }

  def averageScoreByStudent(
      student: Student,
      studentList: List[Student]
    ): Double = {
      val scoresOfStudents = studentList.filter(s => s.name.equals(student.name)).map(s => s.score)
      scoresOfStudents.sum.toDouble / scoresOfStudents.length.toDouble
      if (scoresOfStudents.length != 0) 
        scoresOfStudents.sum.toDouble / scoresOfStudents.length.toDouble
      else
        0
    }
}
