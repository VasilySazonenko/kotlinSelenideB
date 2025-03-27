package pages

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement


class CreateAccountPage {

    enum class CreateAccInputs(val label: String) {
        FIRSTNAME("firstname"),
        LASTNAME("lastname"),
        EMAIL("email"),
        PASSWORD("password"),
        BIRTHDATE("birthday")
    }

    enum class CheckBoxType(val label: String) {
        PARTNEROFFERS("optin"),
        POLICY("psgdpr"),
        NEWSLETTER("newsletter"),
        PRIVACY("customer_privacy")
    }

    private val pageCreateAccount = `$`("#registration")

    // social title row
    private val frmSocialTitle = pageCreateAccount.`$`("div.form-group:has(label[for=field-id_gender])")
    private val radioMr = frmSocialTitle.`$`("input#field-id_gender-1")
    private val radioMrs = frmSocialTitle.`$`("input#field-id_gender-2")
    private val btnSave = pageCreateAccount.`$`("button[type=submit]")

    // generic getter for several input fields
    data class FormField(val formGroup: SelenideElement, val input: SelenideElement)

    private fun getFormField(labelFor: CreateAccInputs): FormField {
        val formGroup = pageCreateAccount.`$`("div.form-group:has(label[for=field-${labelFor.label}])")
        val input = formGroup.`$`("input#field-${labelFor.label}")
        return FormField(formGroup, input)
    }

    private fun getCheckBoxField(labelFor: CheckBoxType): FormField {
        val formGroup = pageCreateAccount.`$`("div.form-group:has(label[for=field-${labelFor.label}])")
        val input = formGroup.`$`("input")
        return FormField(formGroup, input)
    }

    fun fillNewAccountField(formName: CreateAccInputs, value: String): CreateAccountPage {
        getFormField(formName).input.setValue(value)
        return CreateAccountPage()
    }

    /** True for Male, false for Female */
    fun pickGenderRadio(gender: Boolean): CreateAccountPage {
        val pickedRadio = if (gender) radioMr else radioMrs
        pickedRadio.click()
        return CreateAccountPage()
    }

    fun pickCheckBox(formName: CheckBoxType): CreateAccountPage {
        getCheckBoxField(formName).input.click()
        return CreateAccountPage()
    }

    fun saveNewUSerCreation(): HomePage {
        btnSave.click()
        return HomePage()
    }
}