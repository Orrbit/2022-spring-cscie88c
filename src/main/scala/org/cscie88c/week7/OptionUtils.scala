package org.cscie88c.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

object OptionUtils {
  
  def fileCharCount(fileName: String): Try[Long] = Try {
    scala.io.Source.fromResource(fileName).mkString.length()
  }

  def charCountAsString(fileName: String): String = {
    Try {
      fileCharCount(fileName).get
    } match {
      case Success(l) => s"number of characters: ${l.toString}"
      case Failure(_) => "error opening file"
    }
  }

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] = Try {
    val list = scala.io.Source.fromResource(fileName).getLines()
    LazyList.from(list)
  }.toOption
}
