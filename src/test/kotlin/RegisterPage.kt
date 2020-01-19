import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

class RegisterPage(private val driver: WebDriver) {

    public val pageUrl = "https://stepik.org/registration"

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(css = "#id_registration_email")
    lateinit var emailInput: WebElement

    @FindBy(css = "#id_registration_password")
    lateinit var passwordInput: WebElement

    @FindBy(css = "#id_registration_full-name")
    lateinit var nameInput: WebElement

    @FindBy(css = "#registration_form button")
    lateinit var registerButton: WebElement

    @FindBy(css = ".light-tabs__switch:nth-child(1)")
    lateinit var loginLink: WebElement

    fun open() = driver.get(pageUrl)

    fun verifyUrl() {
        WebDriverWait(driver, 10).until { it.currentUrl == pageUrl }
    }
}