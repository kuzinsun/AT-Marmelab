import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class InvoicesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dateGte = By.xpath("//input[@name='date_gte']");
    private final By dateLte = By.xpath("//input[@name='date_lte']");
    private final By expandButton = By.xpath("//div[@aria-label='Expand']");
    private final By customer = By.xpath("//div[contains(@class, 'MuiTypography-root MuiTypography-body')]");//MuiTypography-root MuiTypography-body2 css-4prify
    private final By addFilter = By.xpath("//button[@aria-label='Add filter']");
    private final By chooseFilterType = By.xpath("//li[@data-key='customer_id']");
    private final By sendCustomer = By.xpath("//input[@role='combobox']");
    private final By changeAddressCheck = By.xpath("//p[text()='Groove street']");

    public InvoicesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    protected WebElement waitForElement(By locator) {
        return new WebDriverWait(driver, 30) // 10 секунд
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public InvoicesPage inputDateGte() {
        String formattedDate = ("01012024").replaceAll("(\\d{2})(\\d{2})(\\d{4})", "$3-$2-$1");
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];", driver.findElement(dateGte), formattedDate
        );
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('change'));", driver.findElement(dateGte)
        );
        return this;
    }

    public InvoicesPage inputDateLte() {
        String formattedDate = ("01082025").replaceAll("(\\d{2})(\\d{2})(\\d{4})", "$3-$2-$1");
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1];", driver.findElement(dateLte), formattedDate
        );
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('change'));", driver.findElement(dateLte)
        );
        return this;
    }

    public InvoicesPage clickExpandButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(expandButton));
        driver.findElement(expandButton).click();
        return this;
    }

    public String[] customer() {
        wait.until(ExpectedConditions.presenceOfElementLocated(customer));
        String actualText = driver.findElement(customer).getText();
        boolean containsText = actualText.contains("Korey Mohr");
        System.out.println("Проверка текста на 'Korey Mohr': " + (containsText ? "PASSED" : "FAILED"));
        System.out.println("Актуальный текст: '" + actualText + "'");
        String[] parts = actualText.split("\\s+", 3);
        return parts;
    }

    public InvoicesPage clickAddFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addFilter));
        driver.findElement(addFilter).click();
        return this;
    }

    public InvoicesPage clickChooseFilterType() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addFilter));
        driver.findElement(chooseFilterType).click();
        return this;
    }

    public InvoicesPage sendCustomer(String customerName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sendCustomer));
        driver.findElement(sendCustomer).sendKeys(customerName);
        driver.findElement(sendCustomer).sendKeys(Keys.ENTER);
        return this;
    }

    public InvoicesPage chooseCustomerInFilterList(String customerName) {
        By listbox = By.xpath("//ul[@role=\"listbox\"]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(listbox));
        By customerInList = By.xpath("//li[text()=\"" + customerName + "\"]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(customerInList));
        driver.findElement(customerInList).click();
        return this;
    }

    public InvoicesPage changeAddressCheck() {
        waitForElement(changeAddressCheck);
        //assertTrue(driver.findElement(changeAddressCheck).isDisplayed());
        if (driver.findElement(changeAddressCheck).isDisplayed()) {
            System.out.println("Адрес изменился на новый");
        } else {
            System.out.println("Адрес НЕ изменился");
        }
        return this;
    }

    public InvoicesPage checkOldAddressRevert(String oldAddress) {
        By check = By.xpath("//p[text()=\"" + oldAddress + "\"]");
        //driver.findElement(By.xpath("//p[text()=\"" + oldAddress + "\"]"));
        //assertTrue(check.isDisplayed());
        waitForElement(check);
        if (driver.findElement(check).isDisplayed()) {
            System.out.println("Адрес изменен на первоначальный");
        } else {
            System.out.println("Адрес НЕ изменился на первоначальный");
        }
        return this;
    }

}
