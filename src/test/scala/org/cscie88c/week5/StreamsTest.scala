package org.cscie88c.week5

import org.cscie88c.testutils.{ StandardTest }
import Streams.Dog

class StreamsTest extends StandardTest {

  "calling healthyDogs" should {
    "return a sample of healthy dogs" in {
      val actualDogs: List[Dog] =
        Streams.healthyDogs(Streams.dogs).take(5).toList;
      actualDogs.forall(d => d.hasCurrentShots) should be(true)
    }

    "return a correct sample of healthy dogs" in {
      val testDogStream = LazyList.continually(Dog("dog", 3, true))
      val testDogAverageHealthyAge: Double =
        Streams.averageHealthyAge(testDogStream, 5);
      testDogAverageHealthyAge should be(3)
    }
  }
}
