/**
A creational design pattern that encapsulates
 a group of individual factories that have a
 common theme without specifying their
 concrete classes
**/

interface Truck {
    val color: String
    fun noOfWheels(): Int
}

class Isuzu(override val color: String) : Truck {
    override fun noOfWheels(): Int = 18
}

class Benz(override val color: String) : Truck {
    override fun noOfWheels(): Int = 22
}

class JapanTruckFactory : CarFactory() {
    override fun getTruck(): Truck = Isuzu(color = "White")
}

class GermanTruckFactory : CarFactory() {
    override fun getTruck(): Truck = Benz(color = "Black")
}

//Abstract Factory
abstract class CarFactory {

    abstract fun getTruck(): Truck

    companion object {

        @JvmStatic
        inline fun <reified T> createTruckFactory(): CarFactory {
            return when (T::class) {
                JapanTruckFactory::class -> JapanTruckFactory()
                GermanTruckFactory::class -> GermanTruckFactory()
                else -> throw IllegalStateException("Unidentified truck factory")
            }
        }
    }
}

//Usage:The generic interface of the factory is used to create the concrete objects
fun main(){
    val japanTruckFactory = CarFactory.createTruckFactory<JapanTruckFactory>()
    val germanTruckFactory = CarFactory.createTruckFactory<GermanTruckFactory>()

    assert(germanTruckFactory.getTruck() is Benz)//True
    assert(japanTruckFactory.getTruck() is Isuzu)//True
}
