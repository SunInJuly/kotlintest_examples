import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

class LoginPage(private val driver: WebDriver) {

    private val pageUrl = "https://stepik.org/login"

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(css = "#id_login_email")
    lateinit var emailInput: WebElement

    @FindBy(css = "#id_login_password")
    lateinit var passwordInput: WebElement

    @FindBy(css = ".light-tabs__switch:nth-child(2)")
    lateinit var registerLink: WebElement

    fun open() = driver.get(pageUrl)

    fun verifyUrl() {
        WebDriverWait(driver, 10).until { it.currentUrl == pageUrl }
    }
}