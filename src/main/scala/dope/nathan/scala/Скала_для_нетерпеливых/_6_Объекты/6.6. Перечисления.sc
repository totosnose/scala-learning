object TrafficLightColor extends Enumeration {
  type TrafficLightColor = Value // псевдоним типа

  val Red, Yellow = Value          // инициализация методом Value (числовое значение (count) и имя поля по-умолчанию)
  val Green       = Value(0, "Go") // инициализация c передачей строкового имени и числового значения
}

// использование (словим ошибку, потому что для Red автоматом установилось значение 0, а для Green - 0 установлен явно)
val value0: TrafficLightColor.Value = TrafficLightColor.Red    // без псевдонима
val value1: TrafficLightColor.TrafficLightColor = TrafficLightColor.Yellow // с псевдонимом

// получение числового значения
value0.id

// получение строкового имени
value1.toString

// множество всех значений
TrafficLightColor.values.foreach(println)

TrafficLightColor(1)
TrafficLightColor.withName("Green") // тоже ошибка, потому что установлено имя "Go"