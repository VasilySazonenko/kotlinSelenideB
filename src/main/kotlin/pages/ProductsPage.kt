package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`

open class ProductsPage {
    private val pageProducts = `$`("section#products")
    private val allProductPrices = pageProducts.`$$`("span.price")
    private val allProducts = pageProducts.`$$`("div.product")
    private val frmActiveProductsFilters = pageProducts.`$`("section.active_filters")
    private val allActiveFilterItems = frmActiveProductsFilters.`$$`("li.filter-block")

    fun getAllProductPrices(): List<Double> {
        return allProductPrices.texts().map { price ->
            price.replace("€", "").trim().toDouble()
        }
    }

    fun pickProductByIndex(index: Int): SelectedProductPage {
        allProducts[index].click()
        val selectedProductPage = SelectedProductPage()
        selectedProductPage.pageSelectedProduct.should(Condition.visible)
        return selectedProductPage
    }

    fun getAllFilterItemsTexts(): List<String> {
        allActiveFilterItems.first().should(Condition.visible)
        return allActiveFilterItems.texts().map { it.trim() }
    }
}