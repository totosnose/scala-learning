// 1
def signum(i: Int) = {
  if (i > 0) 1
  else if(i < 0) -1
  else 0
}

signum(0)

// 2
// {} вернет: () типа Unit
{}

// 3
var x = ()
var y = 0

x = y = 1

// 4
for (i <- 10 to 0 by -1) println(i)

// 5
def countdown(n: Int) {
  for (i<- n to 0 by -1) println(i)
}

countdown(10)

// 6
def multi(s: String) = {
  var res = 1
  for (c <- s) {
    res *= c
  }
  res
}

multi("Hello")

// 7
import collection.immutable._
"Hello".product