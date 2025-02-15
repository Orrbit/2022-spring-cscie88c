package org.cscie88c.week6

import org.cscie88c.testutils.{ StandardTest }

class KafkaProducerTest extends StandardTest {
  "KafkaClient" should {
    "send a message to the default topic" in {
      import SimpleKafkaProducer._
      KafkaClient.sendStatusEvent("Message") should include("default-topic")
    }
  }
}
