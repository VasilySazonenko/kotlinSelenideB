package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`


class HeaderPage {

    private val pageHeader = `$`("header[id='header']")
    private val btnSignIn = pageHeader.`$`("div.user-info span.hidden-sm-down")
    private val lblAccountInfo = pageHeader.`$`("a.account")
    private val frmNavigationMenu = pageHeader.`$`("div.menu")
    private val btnAccessories = frmNavigationMenu.`$`("li#category-6")

    fun navigateToSignInPage(): SignInPage {
        btnSignIn.should(Condition.clickable)
        btnSignIn.click();
        return SignInPage();
    }

    fun openAccessoriesPage(): AccessoriesPage {
        btnAccessories.click()
        return AccessoriesPage()
    }

    fun getUserAccountText(): String {
        return lblAccountInfo.should(Condition.visible).text()
    }
}