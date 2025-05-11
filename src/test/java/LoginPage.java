import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final String username = ConfigLoader.getProperty("username");
    private final String password = ConfigLoader.getProperty("password");

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameField = By.xpath("//input[@id=':r0:']");
    private final By passwordField = By.xpath("//input[@id=':r2:']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    public LoginPage login() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(passwordField).sendKeys(Keys.ENTER);
        return this;
    }

}
