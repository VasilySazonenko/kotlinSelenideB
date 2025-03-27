package pages.checkOutPages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import pages.CreateAccountPage.FormField

class AddressesPage {
    val pageAddressesPage = `$`("section#content:has(section#checkout-personal-information-step)")
    private val drdState = pageAddressesPage.`$`("div.form-group select#field-id_state") //drd throws nullpointer if i make "div.form-group" as separate parent element for select
    private val drdCountry = pageAddressesPage.`$`("div.form-group select#field-id_country") //drd throws nullpointer if i make "div.form-group" as separate parent element for select
    private val btnContinue = pageAddressesPage.`$`("button[name=confirm-addresses]")

    enum class PersonalInfo(val input: String) {
        ALIAS("alias"),
        COMPANY("company"),
        MAIN_ADDRESS("address1"),
        ADDITIONAL_ADDRESS("address2"),
        CITY("city"),
        POSTCODE("postcode"),
        PHONE("phone"),
    }

    private fun getFormField(inputName: PersonalInfo): FormField {
        val frmAddressInputRow = pageAddressesPage.`$`("div.form-group:has(input#field-${inputName.input})")
        val input = frmAddressInputRow.`$`("input#field-${inputName.input}")
        return FormField(frmAddressInputRow, input)
    }

    fun fillPersonalInfoInput(formName: PersonalInfo, value: String): AddressesPage {
        getFormField(formName).input.setValue(value)
        return AddressesPage();
    }

    fun selectState(stateName: String): AddressesPage {
        drdState.selectOption(stateName)
        return AddressesPage()
    }

    fun selectCountry(countryName: String): AddressesPage {
        drdCountry.selectOption(countryName)
        return AddressesPage()
    }

    fun performAddressContinue(): ShippingMethodPage {
        btnContinue.click()
        val shippingMethodPage = ShippingMethodPage()
        shippingMethodPage.pageShippingMethod.should(Condition.visible)
        return shippingMethodPage
    }
}