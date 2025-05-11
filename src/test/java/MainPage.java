import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By ordersLink = By.xpath("//a[@href='#/orders']");
    private final By invoicesLink = By.xpath("//a[@href='#/invoices']");
    private final By customersLink = By.xpath("//a[@href='#/customers' and text()='Customers']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    public MainPage ordersLinkClick() {
        wait.until(ExpectedConditions.elementToBeClickable(ordersLink));
        driver.findElement(ordersLink).click();
        return this;
    }

    public MainPage invoicesLinkClick() {
        wait.until(ExpectedConditions.elementToBeClickable(invoicesLink));
        driver.findElement(invoicesLink).click();
        return this;
    }

    public MainPage customersLinkClick() {
        wait.until(ExpectedConditions.elementToBeClickable(customersLink));
        driver.findElement(customersLink).click();
        return this;
    }

}
