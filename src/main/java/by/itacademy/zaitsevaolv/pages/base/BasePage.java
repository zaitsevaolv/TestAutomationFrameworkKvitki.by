package by.itacademy.zaitsevaolv.pages.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static by.itacademy.zaitsevaolv.constants.Constant.TimeoutVariable.EXPLICIT_WAIT;


public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для открытия страницы по URL
    public void open(String url) {
        driver.get(url);
    }

    // Метод для ожидания видимости элемента
    public WebElement waitElementIsVisible(WebElement element) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            System.err.println("Элемент не стал видимым: " + element.toString());
            return null;
        }
    }

    //Метод для ожидания кликабельности и клика по элементу
    public boolean clickElementWhenClickable(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                    .until(ExpectedConditions.elementToBeClickable(element)).click();
            return true;
        } catch (TimeoutException e) {
            System.err.println("Элемент не стал кликабельным: " + element.toString());
            return false;
        }
    }

    //Метод для получения цвета рамки
    public String getFieldBorderColor(WebElement field) {
        return field.getCssValue("border-color");
    }

    //Метод для получения сообщения об ошибке
    public String getFieldErrorMessage(WebElement errorMessage) {
        return errorMessage.getText();
    }

    //Метод для симуляции ухода из поля
    public BasePage leaveField(WebElement field) {
        field.sendKeys(Keys.TAB);
        return this;
    }

    //Метод для заполнения полей
    public BasePage enterText(WebElement field, String text) {
        waitElementIsVisible(field);
        field.clear();
        field.sendKeys(text);
        return this;
    }
}
