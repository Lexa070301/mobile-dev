/*
 Мост - структурный шаблон проектирования, используемый в проектировании программного обеспечения чтобы разделять абстракцию и реализацию так, чтобы они могли изменяться независимо.
 */

// Интерфейс для цвета
interface Color {
    fun getColor()
}

// Класс желтого цвета, который наследуется от интерфейса цвета
class Yellow: Color {
    override fun getColor() {
        println("Yellow")
    }
}

// Класс красного цвета, который наследуется от интерфейса цвета
class Red: Color {
    override fun getColor() {
        println("Red")
    }
}

// Интерфейс для дома
interface House {
    val color: Color
    fun show()
}

// Класс деревянного дома, принимающий цвет, который наследуется от интерфейса дома
class WoodHouse(override val color: Color): House {
    override fun show() {
        print("The wood house color is ")
        color.getColor()
    }
}

// Класс каменного дома, принимающий цвет, который наследуется от интерфейса дома
class RockHouse(override val color: Color): House {
    override fun show() {
        print("The rock house color is ")
        color.getColor()
    }
}

// Здесь мы видим, что благодаря паттерну моста мы можем делать любые комбинации домов и их цветов.
fun main() {
    val yellowWoodHouse = WoodHouse(color = Yellow())
    yellowWoodHouse.show()
    val yellowRockHouse = RockHouse(color = Yellow())
    yellowRockHouse.show()
    val redWoodHouse = WoodHouse(color = Red())
    redWoodHouse.show()
    val redRockHouse = RockHouse(color = Red())
    redRockHouse.show()
}
