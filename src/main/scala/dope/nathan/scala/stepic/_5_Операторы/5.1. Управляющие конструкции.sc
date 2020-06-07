// if - выражение
val x = 1
if (x == 1) {
  println("one")
  println("uno")
}

val str: Unit =
  if (x == 1)
    println("one")
  else if (x == 2)
    println("two")
  else
    println("???")

println(str)

println(
  if (x == 1)
    println("one")
  else if (x == 2)
    println("two")
  else
    println("???")
)

// while

// Нет: break & continue
var i = 0
while (i <= 10) {
  println(i)
  i += 1
}

// for
for(i ← 1 to 10)
  println(i)

for (i ← 1 to 10; j ← i to 10) {
  println("$i $j")
}

// или
for {
  i ← 1 to 10
  j ← i to 10
} println("$i $j")

// дополнительные условия
for {
  i ← 1 to 10
  j ← i to 10 if i > j
} println("$i $j")