package by.itacademy.zaitsevaolv.tests.base;

import by.itacademy.zaitsevaolv.common.CommonActions;
import by.itacademy.zaitsevaolv.constants.Constant;
import by.itacademy.zaitsevaolv.pages.base.BasePage;
import by.itacademy.zaitsevaolv.pages.kvitkiby.KvitkiHomePage;
import by.itacademy.zaitsevaolv.pages.kvitkiby.LoginPage;
import by.itacademy.zaitsevaolv.pages.kvitkiby.RegistrationPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static by.itacademy.zaitsevaolv.common.Config.CLEAR_COOKIES_AND_STORAGE;

public class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;
    protected KvitkiHomePage kvitkiHomePage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;

    @BeforeMethod
    public void setup() {
        driver = CommonActions.createDriver();
        basePage = new BasePage(driver);
        kvitkiHomePage = new KvitkiHomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        // Открытие главной страницы и выполнение предустановок
        kvitkiHomePage.open(Constant.Urls.KVITKIBY_HOME_PAGE);
        kvitkiHomePage.prepareForLogin();
    }

    @AfterMethod
    public void tearDown() {
        // Очистка cookies и local storage после каждого теста
        if (driver != null) {
            if (CLEAR_COOKIES_AND_STORAGE) {
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                driver.manage().deleteAllCookies();
                javascriptExecutor.executeScript("window.sessionStorage.clear()");
            }
            driver.quit();
        }
    }
}
