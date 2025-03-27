package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import utils.BaseTest

class SelectedProductPage {
    val pageSelectedProduct = `$`("div.product-container")
    private val lblProductPrice = pageSelectedProduct.`$`("span.current-price-value")
    private val txtProductAmount = pageSelectedProduct.`$`("input#quantity_wanted")
    private val btnAddToCart = pageSelectedProduct.`$`("button.add-to-cart")

    fun getProductPrice(): Double {
        return lblProductPrice.text().replace("€", "").trim().toDouble()
    }

    fun setProductAmount(value: Int): SelectedProductPage {
        txtProductAmount.should(Condition.value("1"));
        txtProductAmount.sendKeys("Backspace") // clear works from time to time only
        txtProductAmount.setValue(value.toString())
        return SelectedProductPage()
    }

    fun addItemsToCart(): QuickCartReviewPage {
        btnAddToCart.click();
        val quickCartReviewPage = QuickCartReviewPage()
        quickCartReviewPage.pageQuickCartReview.should(Condition.visible)
        return quickCartReviewPage
    }

    fun navigateBackToFilteredProductPage(): ProductsPage {
        Selenide.back()
        // workaround around bug
        Selenide.back()
        BaseTest.switchToIframe()
        return ProductsPage()
    }
}