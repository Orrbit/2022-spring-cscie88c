package org.cscie88c.week6

// Write a generic trait AddableTypeclass parameterized by type A
trait AddableTypeclass[A] {
  def addTwoValues(a: A, b: A): A
}

object AddableTypeclass {

  implicit val intAddableTypeclass: AddableTypeclass[Int] =
    new AddableTypeclass[Int] {
      def addTwoValues(a: Int, b: Int): Int = a + b
    }

  implicit val boolAddableTypeclass: AddableTypeclass[Boolean] =
    new AddableTypeclass[Boolean] {
      def addTwoValues(a: Boolean, b: Boolean): Boolean = a || b
    }

  implicit val strAddableTypeclass: AddableTypeclass[String] =
    new AddableTypeclass[String] {
      def addTwoValues(a: String, b: String): String = s"$a, $b"
    }

  implicit val empAddableTypeclass: AddableTypeclass[Employee] =
    new AddableTypeclass[Employee] {
      def addTwoValues(a: Employee, b: Employee): Employee = Employee(
        strAddableTypeclass.addTwoValues(a.name, b.name),
        intAddableTypeclass.addTwoValues(a.age, b.age),
        intAddableTypeclass.addTwoValues(a.salary, b.salary)
      )
    }

}

object AddableAggregator {
  def sumWithAddable[A](
      list: List[A]
    )(implicit
      addable: AddableTypeclass[A]
    ): A = list.reduce((agg, next) => addable.addTwoValues(agg, next))
}
