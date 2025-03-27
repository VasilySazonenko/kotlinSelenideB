package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import pages.checkOutPages.AddressesPage

class ShoppingCartPage {
    val pageShoppingCart = `$`("div.cart-grid")
    private val frmProductContainer = pageShoppingCart.`$`("div.cart-container")
    private val allProductContainerPrices = frmProductContainer.`$$`("span.product-price")

    private val frmSummaryContainer = pageShoppingCart.`$`("div.cart-summary")
    private val btnProceedToCheckout = frmSummaryContainer.`$`("div.checkout div")
    private val frmCartTotalRow = frmSummaryContainer.`$`("div.cart-total")
    private val lblTotalPrice = frmCartTotalRow.`$`("span.value")

    fun getTotalPrice(): Double {
        return lblTotalPrice.text().replace("€", "").trim().toDouble()
    }

    fun getAllCartProductsPrice(): List<Double> {
        return allProductContainerPrices.texts().map { it.replace("€", "").trim().toDouble() }
    }

    fun performCheckout(): AddressesPage {
        btnProceedToCheckout.click()
        val personalInformationPage = AddressesPage()
        personalInformationPage.pageAddressesPage.should(Condition.visible)
        return personalInformationPage
    }
}