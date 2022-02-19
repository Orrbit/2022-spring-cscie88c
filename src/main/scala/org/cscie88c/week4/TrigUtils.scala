package org.cscie88c.week4

object TrigUtils {

  // https://www.scala-lang.org/api/2.13.6/scala/math/index.html
  // use the function literal syntax for sin and cos
  val sinDegrees: Double => Double = (degreeIn: Double) =>
    (Math.toRadians _ andThen Math.sin)(degreeIn)
  val cosDegrees: Double => Double = (degreeIn: Double) =>
    (Math.toRadians _ andThen Math.cos)(degreeIn)

  // use the placeholder syntax for squared
  val squared: Double => Double = Math.pow(_, 2)

}
