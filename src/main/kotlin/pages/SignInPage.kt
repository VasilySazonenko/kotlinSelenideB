package pages

import com.codeborne.selenide.Selenide.`$`

class SignInPage {
    val pageSignInPage = `$`("section#main:has(section.login-form)")
    private val btnCreateAccount = `$`("div.no-account")

    fun confirmAccountCreation(): CreateAccountPage {
        btnCreateAccount.click()
        return CreateAccountPage()
    }
}