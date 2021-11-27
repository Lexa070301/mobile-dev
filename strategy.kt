/*
Стратегия — это поведенческий паттерн, определяющий семейство схожих алгоритмов и помещающий каждый из них в собственный класс, после чего алгоритмы можно взаимозаменять прямо во время исполнения программы.
 */

// Интерфейс бронирования
interface BookingStrategy {
    // Тариф
    val fare: Double
}

// Класс бронирования машин
class CarBookingStrategy: BookingStrategy {

    override val fare: Double = 12.5

    override fun toString(): String {
        return "CarBookingStrategy"
    }
}

// Класс бронирования поездов
class TrainBookingStrategy: BookingStrategy {

    override val fare: Double = 8.5

    override fun toString(): String {
        return "TrainBookingStrategy"
    }
}

// Класс клиента
class Customer(var bookingStrategy: BookingStrategy) {

    fun calculateFare(numOfPassangeres: Int): Double {
        val fare = numOfPassangeres * bookingStrategy.fare
        println("Calculating fares using " + bookingStrategy)
        return fare
    }
}


// Паттерн Стратегия позволяет нам "налету" менять объект бронирования клиента
fun main() {
    // Создаём клиента, которых хочет бранировать машины
    val cust = Customer(CarBookingStrategy())
    // Высчитываем тариф для 5 человек
    var fare = cust.calculateFare(5)
    println(fare)

    // Теперь клиент хочет бранировать поезд
    cust.bookingStrategy = TrainBookingStrategy()
    fare = cust.calculateFare(5)
    println(fare)
}
