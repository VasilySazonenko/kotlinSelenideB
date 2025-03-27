package pages.checkOutPages

import com.codeborne.selenide.Selenide.`$`

class PaymentMethodPage {
    val pagePaymentMethodPage = `$`("section#checkout-payment-step")
    private val cbxTermsAgreement = pagePaymentMethodPage.`$`("input[id*=approve]")
    private val btnSubmitOrder = pagePaymentMethodPage.`$`("button[type=submit]")

    fun approveTermsOfService(): PaymentMethodPage {
        cbxTermsAgreement.click()
        return PaymentMethodPage()
    }

    fun submitOrder() {
        btnSubmitOrder.click()
        //TODO return page that appears after submitting. currently submit is permanently disabled due to a bug
    }
}