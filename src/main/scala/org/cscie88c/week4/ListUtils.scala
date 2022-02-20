package org.cscie88c.week4

import scala.collection.immutable.ListMap

object ListUtils {
  // complete the function below
  def initDoubleList(initValue: Double)(size: Int): List[Double] =
    List.fill(size)(initValue)

  // complete the functions below using currying
  def ones: Int => List[Double] = initDoubleList(1.0) _
  def zeros: Int => List[Double] = initDoubleList(0.0) _

  // complete the functions below
  def charCounts(sentence: String): Map[Char, Int] = sentence
    .toCharArray()
    .filter(!_.equals(' '))
    .groupBy(identity(_))
    .map(kvPair => kvPair._1 -> kvPair._2.length)

  def topN(n: Int)(frequencies: Map[Char, Int]): Map[Char, Int] = frequencies
    .toList
    .sortBy(_._2)
    .map(_._1)
    .takeRight(n)
    .map(char => (char, frequencies.getOrElse(char, 0)))
    .toMap

}
