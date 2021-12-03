package dope.nathan.scala.rockthejvm.essentials.part1basics.tailrec

import scala.annotation.tailrec

object Tailrec {
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)
    }

    isPrimeUntil(n / 2)
  }

  println(isPrime(4))

  def isPrime2(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean = true): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) isStillPrime
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)
    }

    isPrimeTailrec(n / 2)
  }

  println(isPrime2(4))

  def factorial(n: Int): Int = {
    @tailrec
    def factorialHelp(x: Int, accumulator: Int = 1): Int = {
      if (x <= 1) accumulator
      else factorialHelp(x - 1, x * accumulator)
    }

    factorialHelp(n)
  }

  println(factorial(5))

  def factorial2(n: Int): Int = (1 to n).product

  println(factorial2(5))

  def fibo(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)
    }

    if (n == 1) 0
    else if (n <= 3) 1
    else fiboTailrec(3, 1, 1)
  }

  println(fibo(6))

}
