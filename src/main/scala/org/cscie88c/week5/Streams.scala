package org.cscie88c.week5

import java.util.UUID
import scala.util.Random

object Streams {
  val rnd = new Random()
  def uuid: String = UUID.randomUUID.toString.replaceAll("-", "")

  case class Dog(
      name: String,
      age: Int,
      hasCurrentShots: Boolean
    )

  val mult5: LazyList[Int] = LazyList.range(0, 100, 5)

  val randIntStream: LazyList[Int] = LazyList.continually(rnd.nextInt(15))

  val dogs: LazyList[Dog] =
    LazyList.iterate(Dog(s"dog-$uuid", rnd.nextInt(15), rnd.nextBoolean()))(_ =>
      Dog(s"dog-$uuid", rnd.nextInt(15), rnd.nextBoolean())
    )

  def healthyDogs(dogs: LazyList[Dog]): LazyList[Dog] =
    dogs.filter(d => d.hasCurrentShots)

  def averageHealthyAge(allDogs: LazyList[Dog], sampleSize: Int): Double =
    healthyDogs(allDogs)
      .map(d => d.age)
      .take(sampleSize)
      .sum
      .toDouble / sampleSize.toDouble

}
