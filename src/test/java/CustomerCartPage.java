import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerCartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By address = By.xpath("//textarea[@name='address']");
    private final By saveButton = By.xpath("//button[@aria-label='Save']");
    private final By backupAddress = By.xpath("//textarea[@name='address']");

    public CustomerCartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    /*public CustomerCartPage clickAddress() {
        driver.findElement(address).click();
        return this;
    }*/

    public String getOldAddress() {
        return driver.findElement(address).getText();
    }

    public CustomerCartPage enterAddress() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(address));
        driver.findElement(address).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(address).sendKeys("Groove street");
        return this;
    }

    public CustomerCartPage clickSaveButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        driver.findElement(saveButton).click();
        return this;
    }

    public CustomerCartPage revertAddress(String oldAddress) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(backupAddress));
        driver.findElement(backupAddress).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(backupAddress).sendKeys(oldAddress);
        return this;
    }

}
