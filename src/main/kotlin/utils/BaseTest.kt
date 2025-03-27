package utils

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
import pages.AccessoriesFilteringPage
import pages.HeaderPage
import pages.HomePage
import java.time.Duration


object BaseTest {
    private val iframe = `$`("iframe#framelive") // Or use a more specific selector if needed

    private val loadingImage = `$`("div[id=loadingMessage]")

    fun navigateToHomePage(): HomePage {
        open("https://demo.prestashop.com/")  // Open the URL
        waitForLoadingMsgDisappear();
        switchToIframe()
        return HomePage()  // Return the HomePage object
    }

    private fun waitForLoadingMsgDisappear() {
        loadingImage.shouldNotBe(Condition.visible, Duration.ofSeconds(15))
    }

    fun getAccessoriesFilteringPage(): AccessoriesFilteringPage {
        return AccessoriesFilteringPage()
    }

    fun switchToIframe(){
        switchTo().frame(iframe)
    }
}
