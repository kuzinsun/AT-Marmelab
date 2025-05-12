import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.Test;


public class TestX5 {

    private WebDriver driver;

    private WebDriver initChromeDriver() {
        String driver_path = ConfigLoader.getProperty("chrome_driver_path");
        System.setProperty("webdriver.chrome.driver", driver_path);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--password-store=basic");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    private WebDriver initFirefoxDriver() {
        String driver_path = ConfigLoader.getProperty("firefox_driver_path");
        System.setProperty("webdriver.gecko.driver", driver_path);

        return new FirefoxDriver();
    }

    private OrdersPage ordersPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private InvoicesPage invoicesPage;
    private CustomersPage customersPage;
    private CustomerCartPage customerCartPage;
    public String getCustomer;
    public String getCustomerName;
    public String getCustomerSurename;
    public String oldAddress;

    @Test
    public void testSearch() {
        testOnBrowser("chrome");
        testOnBrowser("firefox");
    }

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void testOnBrowser(String browserType) {

        if ("firefox".equalsIgnoreCase(browserType)) {
            driver = initFirefoxDriver();
            System.out.println("Running test on Firefox");
        } else {
            driver = initChromeDriver();
            System.out.println("Running test on Chrome");
        }

        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            loginPage = new LoginPage(driver);
            mainPage = new MainPage(driver);
            ordersPage = new OrdersPage(driver);
            invoicesPage = new InvoicesPage(driver);
            customersPage = new CustomersPage(driver);
            customerCartPage = new CustomerCartPage(driver);

            String base_url = ConfigLoader.getProperty("base_url");
            driver.get(base_url);

            loginPage.login();

            mainPage.ordersLinkClick();

            ordersPage.clickDeliveredTab();

            ordersPage.clickFirstThreeCheckboxes();

            ordersPage.checkCheckboxClickResult();

            mainPage.invoicesLinkClick();

            invoicesPage.inputDateGte();

            invoicesPage.inputDateLte();

            invoicesPage.clickExpandButton();

            String[] parts = invoicesPage.customer();

            getCustomer = parts[0] + " " + parts[1];
            getCustomerName = parts[0];
            getCustomerSurename = parts[1];

            mainPage.customersLinkClick();

            //pause(2);

            customersPage.search(getCustomer);

            customersPage.clickCustomerCart(getCustomerName, getCustomerSurename);

            oldAddress = customerCartPage.getOldAddress();

            customerCartPage.enterAddress();

            customerCartPage.clickSaveButton();

            mainPage.invoicesLinkClick();

            invoicesPage.clickAddFilter();

            invoicesPage.clickChooseFilterType();

            invoicesPage.sendCustomer(getCustomer);

            //invoicesPage.clickExpandButton();

            invoicesPage.changeAddressCheck();

            mainPage.customersLinkClick();

            customersPage.search(getCustomer);

            customersPage.clickSecondCustomerCart(getCustomerName, getCustomerSurename);

            customerCartPage.revertAddress(oldAddress);

            customerCartPage.clickSaveButton();

            mainPage.invoicesLinkClick();

            invoicesPage.checkOldAddressRevert(oldAddress);

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}