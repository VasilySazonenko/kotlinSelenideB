package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement


class HeaderPage {

    private val pageHeader = `$`("header[id='header']")
    private val btnSignIn = pageHeader.`$`("div.user-info a[title*=\"Log in\"] span.hidden-sm-down")
    private val btnSignOut = pageHeader.`$`("div.user-info a.logout")
    private val lblAccountInfo = pageHeader.`$`("a.account")
    private val frmNavigationMenu = pageHeader.`$`("div.menu")
    private val btnAccessories = frmNavigationMenu.`$`("li#category-6")

    fun navigateToSignInPage(): SignInPage {
        btnSignIn.should(Condition.clickable)
        btnSignIn.click()
        val signInPage = SignInPage()
        signInPage.pageSignInPage.should(Condition.visible)
        return signInPage
    }

    fun openAccessoriesPage(): AccessoriesPage {
        btnAccessories.click()
        return AccessoriesPage()
    }

    fun getUserAccount(): SelenideElement {
        return lblAccountInfo
    }

    fun getUserAccountText(): String {
        return lblAccountInfo.should(Condition.visible).text()
    }

    fun signOutUser() {
        btnSignOut.click()
        val headerPage = HeaderPage()
        headerPage.getUserAccount().should(Condition.hidden)
    }

    fun getBtnSignIn(): SelenideElement {
        return btnSignIn
    }
}