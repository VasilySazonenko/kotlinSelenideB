package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
import java.time.Duration

class HomePage {

    fun getHeaderPage(): HeaderPage {
        return HeaderPage()
    }

}
