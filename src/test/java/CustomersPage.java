import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomersPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchField = By.xpath("//input[@placeholder='Search']");
    //private final By customerCart = By.xpath("//div[contains(@class, 'MuiTypography-root MuiTypography-body2')]");

    public CustomersPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public CustomersPage search(String customer) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        driver.findElement(searchField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(searchField).sendKeys(customer);
        driver.findElement(searchField).sendKeys(Keys.ENTER);
        return this;
    }

    public CustomersPage clickCustomerCart(String getCustomerName, String getCustomerSurename) {
        WebElement customerCart = driver.findElement(By.xpath("//div[text()=\"" + getCustomerName + "\" and text()=\"" + getCustomerSurename + "\"]"));
        wait.until(ExpectedConditions.visibilityOf(customerCart));
        customerCart.click();
        return this;
    }

    public CustomersPage clickSecondCustomerCart(String getCustomerName, String getCustomerSurename) {
        WebElement secondCustomerCart = driver.findElement(By.xpath("//div[text()=\"" + getCustomerName + "\" and text()=\"" + getCustomerSurename + "\"]"));
        wait.until(ExpectedConditions.visibilityOf(secondCustomerCart));
        secondCustomerCart.click();
        return this;
    }

}
