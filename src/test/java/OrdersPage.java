import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrdersPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    protected WebElement waitForElement(By locator) {
        return new WebDriverWait(driver, 15) // 10 секунд
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private final By deliveredTab = By.xpath("//span[text()='delivered']");
    private final By checkboxList = By.xpath("//input[@type='checkbox']");
    private final By checkboxClickResult = By.xpath("//h6[text()='3 items selected']");
    private final By returnedTab = By.xpath("//span[text()=\"Returned\"]");

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public OrdersPage clickDeliveredTab() {
        waitForElement(deliveredTab);
        driver.findElement(deliveredTab).click();
        return this;
    }

    public OrdersPage clickFirstThreeCheckboxes() {
        wait.until(ExpectedConditions.presenceOfElementLocated(returnedTab));
        List<WebElement> checkboxes = driver.findElements(checkboxList);
        for(int i = 1; i < 4 && i < checkboxes.size(); i++) {
            checkboxes.get(i).click();
        }
    return this;
    }

    public OrdersPage checkCheckboxClickResult() {
        waitForElement(checkboxClickResult);
        boolean result = driver.findElement(checkboxClickResult).isDisplayed();
        if (result) {
            System.out.println("Выбрано 3 чекбокса");
        } else {
            throw new RuntimeException("Не выбраны 3 чекбокса");
        }
        return this;

    }
}