package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.actions

class AccessoriesFilteringPage {

    val pageFiltering = `$`("div#search_filters")
    private val frmPrice = pageFiltering.`$`("ul.faceted-slider")
    private val drdGenericSlider = frmPrice.`$$`("div.ui-slider a")
    private val drdLeftSlider = drdGenericSlider[0];
    private val drdRightSlider = drdGenericSlider[1];
    private val lblPriceValue = frmPrice.`$`("p")
    private val loadingSpinner = `$`("span.spinner")

    fun setPriceFilterTo(isMinSlider: Boolean, expectedAmount: Int): AccessoriesFilteringPage {
        var minOrMaxPriceValue = getMinOrMaxFilteredPrice(isMinSlider)
        var counter = 0
        val maxPriceAdjustmentTimes = 40

        // Click and hold before starting the movement
        val slider =
            if (isMinSlider) drdLeftSlider.shouldBe(Condition.visible) else drdRightSlider.shouldBe(Condition.visible)
        actions().clickAndHold(slider).perform()

        // Move slider without releasing
        while (expectedAmount != minOrMaxPriceValue) {
            val direction = if (expectedAmount > minOrMaxPriceValue) 10 else -10
            actions().moveByOffset(direction, 0).perform() // Move but don't release

            minOrMaxPriceValue = getMinOrMaxFilteredPrice(isMinSlider)
            counter++
            if (counter > maxPriceAdjustmentTimes) {
                actions().release().perform() // Ensure release even if max attempts reached
                throw Error("Counter max value exceeded")
            }
        }

        // Release slider after reaching the expected amount
        actions().release().perform()

        loadingSpinner.should(Condition.hidden)
        return AccessoriesFilteringPage()
    }

    private fun getMinOrMaxFilteredPrice(isMin: Boolean): Int {
        val minAndMaxPrice = lblPriceValue.should(Condition.visible).text();

        val price = minAndMaxPrice.split("-").map { it.replace("€", "").trim().split(".")[0].toInt() }

        return if (isMin) price[0] else price[1]
    }

    fun getProductsPage(): ProductsPage {
        return ProductsPage()
    }
}

