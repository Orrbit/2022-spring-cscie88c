package org.cscie88c.week2

// complete the definition of the Subject case class and companion object
final case class Subject(
    id: Int,
    name: String,
    isStem: Boolean
  )

object Subject {

  def apply(csvRow: String): Subject = {
    val fields = csvRow.split(",")
    Subject(
      id = fields(0).toInt,
      name = fields(1),
      isStem = fields(2).toBoolean
    )
  }

  val allSubjects: List[Subject] = List(
    Subject("1,Physics,true"),
    Subject("2,Chemistry,true"),
    Subject("3,Math,true"),
    Subject("4,English,false")
  )

  def stemSubjects: List[Subject] = allSubjects.filter(_.isStem)

}
