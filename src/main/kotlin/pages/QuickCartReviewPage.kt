package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`

class QuickCartReviewPage {
    val pageQuickCartReview = `$`("div.modal-dialog:has(div.cart-content)")
    private val frmProductTotal = pageQuickCartReview.`$`("p.product-total")
    private val frmProductPrice = frmProductTotal.`$`("span.value")
    private val btnClose = pageQuickCartReview.`$`("button.close")
    private val frmActionButtons = pageQuickCartReview.`$`("div.cart-content-btn")
    private val btnProceedToCheckout = frmActionButtons.`$`("a.btn")

    fun getProductTotalPrice(): Double {
       return frmProductPrice.text().replace("€", "").trim().toDouble()
    }

    fun closeQuickCartReview(): SelectedProductPage {
        btnClose.click()
        pageQuickCartReview.should(Condition.hidden)
        return SelectedProductPage()
    }

    fun navigateToCart(): ShoppingCartPage {
        btnProceedToCheckout.click()
        val shoppingCartPage = ShoppingCartPage()
        shoppingCartPage.pageShoppingCart.should(Condition.visible)
        return shoppingCartPage
    }
}