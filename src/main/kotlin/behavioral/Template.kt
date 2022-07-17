package behavioral

// Template Pattern uses an abstract class or interface
// which has defined template(s) and executes its methods
// from there. Then, those methods get overridden as needed.

// Real life example of TP is payments. We can pay with credit
// cards, debit cards, checks, cash...

interface IPaymentHelper {
    // Primitive Method
    fun pay()
}

open class PaymentHelper : IPaymentHelper {
    // Template Method
    fun createInvoice(){
        scanBarcode()
        pay()
    }

    private fun scanBarcode(){
        println("Scanned barcode of the product.")
    }

    override fun pay() {
        //...
    }
}

class Cash : PaymentHelper() {
    // Chart -> Report Helper

    override fun pay() {
        println("Paid with cash.")
    }
}

class CreditCard : PaymentHelper() {
    // Pivot -> Report Helper

    override fun pay() {
        println("Paid with credit card.")
    }
}

// TEST
fun invoice(ph: PaymentHelper){
    ph.createInvoice()
}

fun main(){
    invoice(Cash())
    println("-----------------------------------")
    invoice(CreditCard())
}