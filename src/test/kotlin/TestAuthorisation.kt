import io.kotlintest.Description
import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit





class TestAuthorisation : StringSpec(), TestListener {
    private val driver: WebDriver = ChromeDriver()
    private val loginPage = LoginPage(driver)
    private val registerPage = RegisterPage(driver)
    private val wait = WebDriverWait(driver, 10)

    override fun afterSpec(description: Description, spec: Spec) {
    super<StringSpec>.afterSpec(description, spec)
        driver.quit()
    }

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Guest can open login page" {
            loginPage.run {
                open()
                verifyUrl()
                wait.until {
                    emailInput.isDisplayed
                    passwordInput.isDisplayed
                }
            }
        }

        "Guest can navigate to register page" {
            loginPage.run {
                open()
                wait.until {
                    registerLink.isDisplayed
                    registerLink.getAttribute("href").shouldBe(registerPage.pageUrl)
                    registerLink.click()
                }
            }
            registerPage.run {
                wait.until {
                    emailInput.isDisplayed
                    passwordInput.isDisplayed
                    registerButton.isEnabled

                }
            }
        }
    }
}
