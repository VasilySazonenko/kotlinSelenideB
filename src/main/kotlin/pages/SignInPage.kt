package pages

import com.codeborne.selenide.Selenide.`$`

class SignInPage {

    private val btnCreateAccount = `$`("div.no-account");

    fun confirmAccountCreation(): CreateAccountPage {
        btnCreateAccount.click();
        return CreateAccountPage();
    }
}