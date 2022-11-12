/**
A structural design pattern that decouples
 abstractions from their implementation.
It has a very similar structure to the 
adapter pattern.
**/

interface FurnitureTheme {
    fun getTheme(): String
}

class VintageFurniture : FurnitureTheme {
    override fun getTheme(): String = "Vintage"
}

abstract class Furniture(val furnitureTheme: FurnitureTheme) {
    abstract fun getType(): String
}

class Chair(private val theme: FurnitureTheme) : Furniture(theme) {
    override fun getType(): String = "${theme.getTheme()} Chair."
}

fun main(){
    val theme = VintageFurniture()
    val chair = Chair(theme)

    assert(chair.getType().contains("Vintage Chair"))
}
