/**
A structural design pattern that lets 
incompatible interfaces work in harmony
 by providing a wrapper to make the existing
 classes work without modifying the source code
 using one of the existing interfaces.
**/
interface LaptopCable {
    fun onConnectedToPowerPort()
}

interface PowerBrick {
    fun onConnectedToSocket()
}

class HpPowerBrick : PowerBrick {
    override fun onConnectedToSocket() {
        println("PowerBrick Receiving Power Supply")
    }
}

open class StockCable : LaptopCable {
    override fun onConnectedToPowerPort() {
        println("Cable Connected To Laptop")
    }
}

class StockCableAdapter(private val hpPowerBrick: HpPowerBrick) : StockCable() {
    override fun onConnectedToPowerPort() {
        super.onConnectedToPowerPort()
        hpPowerBrick.onConnectedToSocket()
        println("AC/DC Conversion happening")
    }
}
fun main(){
    val hpPowerBrick = HpPowerBrick()
    val stockCableAdapter = StockCableAdapter(hpPowerBrick)

    stockCableAdapter.onConnectedToPowerPort()
}
