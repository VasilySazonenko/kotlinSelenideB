package pages.checkOutPages

import com.codeborne.selenide.Selenide.`$`

class OrderConfirmationPage {
    val pageOrderConfirmation = `$`("section#main:has(section#content-hook_order_confirmation)")
    private val orderDetails = pageOrderConfirmation.`$`("div#order-details")
    private val paymentMethod = orderDetails.`$`("li:not(#order-reference-value):not(:has(br))")

    fun getOrderDetailsPaymentMethod(): String {
        return paymentMethod.text()
    }
}