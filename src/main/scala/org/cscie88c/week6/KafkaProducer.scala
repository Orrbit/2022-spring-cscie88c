package org.cscie88c.week6

trait KafkaProducer {
  def send(message: String): String;
}

case class SimpleKafkaProducer(topic: String) extends KafkaProducer {
  def send(message: String): String = s"$topic : $message"
}

object SimpleKafkaProducer {
  implicit val defaultKafkaProducer = SimpleKafkaProducer("default-topic")
}

object KafkaClient {
  def sendStatusEvent(status: String)(implicit kafkaProducer: KafkaProducer) =
    kafkaProducer.send(status)
}
