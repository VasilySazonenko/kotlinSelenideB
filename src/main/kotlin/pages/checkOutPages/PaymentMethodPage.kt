package pages.checkOutPages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import java.time.Duration

class PaymentMethodPage {
    val pagePaymentMethodPage = `$`("section#checkout-payment-step div.content")
    private val cbxTermsAgreement = pagePaymentMethodPage.`$`("input[id*=approve]")
    private val btnSubmitOrder = pagePaymentMethodPage.`$`("div#payment-confirmation button[type=submit]")
    private val frmPaymentRadio = pagePaymentMethodPage.`$`("div.payment-options")
    private val radioPayByCheck = frmPaymentRadio.`$`("input[data-module-name*=checkpayment]")
    private val frmAdditionalInfoForCheckPayment = frmPaymentRadio.`$`("div[id*=\"3-additional-information\"]")
    private val lblTotalCheckPrice = frmAdditionalInfoForCheckPayment.`$$`("dd").first()

    fun approveTermsOfService(): PaymentMethodPage {
        cbxTermsAgreement.click()
        return PaymentMethodPage()
    }

    fun choosePayByCheck(): PaymentMethodPage {
        radioPayByCheck.click()
        return PaymentMethodPage()
    }

    fun submitOrder(): OrderConfirmationPage {
        btnSubmitOrder.should(Condition.editable, Duration.ofSeconds(15)).click()
        val orderConfirmationPage = OrderConfirmationPage()
        orderConfirmationPage.pageOrderConfirmation.should(Condition.visible)
        return orderConfirmationPage
    }

    fun getCheckTotalPrice(): String {
        return lblTotalCheckPrice.text()
    }
}