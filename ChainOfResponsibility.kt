/**
A behavioral design pattern consisting of 
a chain of receivers known as handlers,
each handler will process a request and pass it
 on to the next for further processing.
**/

interface OrderHandler {
    val nextHandler: OrderHandler?
    fun handleRequest(order: Order)
}

interface Order {
    val items: Map<String, Int>
}

class IceCreamOrder(override val items: Map<String, Int>) : Order

class IceCreamVendor(override val nextHandler: OrderHandler?) : OrderHandler {
    override fun handleRequest(order: Order) {
        val orderSize = order.items.values.sum()
        if (orderSize > 5) {
            println("Vendor: I'll have to get some")
            nextHandler?.handleRequest(order) ?: return
        } else {
            println("Order of ${order.items.keys} coming right up")
        }
    }
}

class IceCreamRetailer(override val nextHandler: OrderHandler?) : OrderHandler {
    override fun handleRequest(order: Order) {
        val orderSize = order.items.values.sum()
        if (orderSize > 10) {
            println("Retailer: Hmm guess I'll have to order more too")
            nextHandler?.handleRequest(order) ?: return
        } else {
            println("Order of ${order.items.keys} enroute to vendor")
        }
    }
}

class IceCreamFactory(override val nextHandler: OrderHandler?) : OrderHandler {
    override fun handleRequest(order: Order) {
        val orderSize = order.items.values.sum()
        if (orderSize > 100) {
            nextHandler?.handleRequest(order) ?: return
        } else {
            println("Factory: Order of ${order.items.keys} coming enroute to retailer")
        }
    }
}

fun main() {
    val iceCreamFactory = IceCreamFactory(nextHandler = null)
    val iceCreamRetailer = IceCreamRetailer(nextHandler = iceCreamFactory)
    val iceCreamVendor = IceCreamVendor(nextHandler = iceCreamRetailer)

    val order = IceCreamOrder(mapOf("Vanilla" to 10, "Chocolate" to 9, "Pistachio" to 5))
    iceCreamVendor.handleRequest(order)
}
