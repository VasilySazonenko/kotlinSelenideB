package pages.checkOutPages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`

class ShippingMethodPage {
    val pageShippingMethod = `$`("section#checkout-delivery-step")
    private val btnContinue = pageShippingMethod.`$`("button[name=confirmDeliveryOption]")

    fun performShippingMethodContinue(): PaymentMethodPage {
        btnContinue.click()
        val paymentMethodPage = PaymentMethodPage()
        paymentMethodPage.pagePaymentMethodPage.should(Condition.visible)
        return paymentMethodPage
    }

}