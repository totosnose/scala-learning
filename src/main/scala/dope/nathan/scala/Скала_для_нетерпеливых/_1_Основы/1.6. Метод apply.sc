// def apply(n: Int): Char -
// возвращает новый объект

// краткая форма
"Hello"(4)
// полная форма
"Hello".apply(4)

// на примере BigInt (companion)

// краткая форма
BigInt("1234567890")
// полная форма
BigInt.apply("1234567890")

BigInt("1234567890") * BigInt("1234567890")

// на примере Array (companion)

// краткая форма
Array(1, 4, 9, 16)
// полная форма
Array.apply(1, 4, 9, 16)