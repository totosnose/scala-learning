// function as parameter
def totalPrices(prices: List[Int], selector: Int => Boolean) = {
  var total = 0
  for(price <- prices) {
    if(selector(price)) total += price
  }

  total
}

val prices = List(10, 15, 20, 25, 30, 35, 40)
println(totalPrices(prices, {price => true}))
println(totalPrices(prices, {price => price > 25}))
println(totalPrices(prices, {price => price < 25}))


