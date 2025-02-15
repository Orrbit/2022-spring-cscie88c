package org.cscie88c.week4

object FunctionUtils {

  // complete the implementation of the higher order functions below
  def applyNtimes(n: Int)(x: Int)(f: Int => Int): Int =
    (1 to n).fold(x)((acc: Int, _) => f(acc))

  def myPositivePower(x: Int, n: Int): Int =
    applyNtimes(n)(1)((num: Int) => num * x)

  def deferredExecutor(name: String)(f: Int => Int): Int => Int = (x: Int) => {
    print(s"running on deferred executor ${name} with value ${x}")
    f(x)
  }
}
