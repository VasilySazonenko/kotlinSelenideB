package uiTests

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import pages.CreateAccountPage.CheckBoxType
import pages.CreateAccountPage.CreateAccInputs
import pages.checkOutPages.AddressesPage.PersonalInfo
import utils.BaseTest
import kotlin.random.Random

class PrestaShopTest {  // Inherit utils.BaseTest

    @BeforeEach
    fun settingDriver() {
        WebDriverManager.chromedriver().setup()
    }

    @Test
    @Tag("shop")
    fun testNavigateToPrestaShopHomePage() {
        val lowestPriceFilter = 18
        val highestPriceFilter = 23
        val firstRandomInt = Random.nextInt(0, 3)
        var secondRandomInt: Int
        do {
            secondRandomInt = Random.nextInt(0, 3)  // Randomly picks 0, 1, or 2
        } while (secondRandomInt == firstRandomInt)
        val numberOfAddedProducts = 4
        val filtersCount = 1
        val firstName = "Anton"
        val lastName = "Gorodecky"
        val email = "silasveta@dozor.com"
        val password = "N0DarkPowerOnMe@"
        val birthDate = "05/05/2001"

        val headerPage = BaseTest
            .navigateToHomePage()
            .getHeaderPage()
            .navigateToSignInPage()
            .confirmAccountCreation()
            .pickGenderRadio(true)
            .fillNewAccountField(CreateAccInputs.FIRSTNAME, firstName)
            .fillNewAccountField(CreateAccInputs.LASTNAME, lastName)
            .fillNewAccountField(CreateAccInputs.EMAIL, email)
            .fillNewAccountField(CreateAccInputs.PASSWORD, password)
            .fillNewAccountField(CreateAccInputs.BIRTHDATE, birthDate)
            .pickCheckBox(CheckBoxType.POLICY)
            .pickCheckBox(CheckBoxType.PRIVACY)
            .saveNewUSerCreation()
            .getHeaderPage()

        assertTrue(headerPage.getUserAccountText().contains("$firstName $lastName"))

        val filteredProductsPage = headerPage
            .openAccessoriesPage()
            .getFilterPage()
            .setPriceFilterTo(true, lowestPriceFilter)
            .setPriceFilterTo(false, highestPriceFilter)
            .getProductsPage()

        val allFilteredProductPrices = filteredProductsPage
            .getAllProductPrices()
        assertTrue(
            allFilteredProductPrices.all { it in lowestPriceFilter.toDouble()..highestPriceFilter.toDouble() },
            "Not all prices are between $lowestPriceFilter and $highestPriceFilter"
        )
        val selectedProductPage = filteredProductsPage
            .pickProductByIndex(firstRandomInt)

        val firstSelectedItemPrice = selectedProductPage.getProductPrice()

        val quickCartReviewPage = selectedProductPage.setProductAmount(numberOfAddedProducts).addItemsToCart()
        val totalPriceForPickedItems = numberOfAddedProducts * firstSelectedItemPrice

        assertEquals(totalPriceForPickedItems, quickCartReviewPage.getProductTotalPrice(), "Total price is not correct")

        quickCartReviewPage
            .closeQuickCartReview()
            .navigateBackToFilteredProductPage()

        assertTrue(
            filteredProductsPage.getAllFilterItemsTexts().size == filtersCount,
            "Filters count is incorrect after navigating back"
        )
        assertTrue(
            filteredProductsPage.getAllFilterItemsTexts()
                .any { it.contains("€$lowestPriceFilter.00 - €$highestPriceFilter.00") },
            "Filters value is not correct after navigating back"
        )


        val shoppingCartPage = filteredProductsPage
            .pickProductByIndex(secondRandomInt)
            .addItemsToCart()
            .navigateToCart()

        assertEquals(
            shoppingCartPage.getTotalPrice(),
            shoppingCartPage.getAllCartProductsPrice().sum(),
            "Total price sum of all items in the cart is not correct"
        )

        shoppingCartPage
            .performCheckout()
            .fillPersonalInfoInput(PersonalInfo.ALIAS, "Geser")
            .fillPersonalInfoInput(PersonalInfo.COMPANY, "Nochnoy Dozor")
            .fillPersonalInfoInput(PersonalInfo.MAIN_ADDRESS, "Rozovaya street 1/2")
            .fillPersonalInfoInput(PersonalInfo.ADDITIONAL_ADDRESS, "Zelenaya Street 2/3")
            .fillPersonalInfoInput(PersonalInfo.CITY, "Zelenaya Street 2/3")
            .fillPersonalInfoInput(PersonalInfo.POSTCODE, "10581")
            .fillPersonalInfoInput(PersonalInfo.PHONE, "2896488633")
            .selectState("Arkansas")
            .selectCountry("United States")
            .performAddressContinue()
            .performShippingMethodContinue()
            .approveTermsOfService()
            .submitOrder()
//TODO finish test with checking order details and verified logout
    }
}
