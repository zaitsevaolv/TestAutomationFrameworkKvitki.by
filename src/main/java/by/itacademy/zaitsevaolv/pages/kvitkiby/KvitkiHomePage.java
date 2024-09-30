package by.itacademy.zaitsevaolv.pages.kvitkiby;

import by.itacademy.zaitsevaolv.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KvitkiHomePage extends BasePage {
    public KvitkiHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id=\'CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll\']")
    private WebElement cookieDialogButtonAllowAll;
    @FindBy(xpath = "//app-user-account-link[@class='account_icon']")
    private WebElement accountIcon;
    @FindBy(xpath = "//div[@class='popupbanner_close']")
    private WebElement popupBannerClose;
    @FindBy(xpath = "//div[@class='scrollbanners_close']")
    private WebElement scrollBannersClose;

    //Метод для закрытия всплывающего окна с рекламой
    public KvitkiHomePage clickPopupBannerClose() {
        clickElementWhenClickable(popupBannerClose);
        return this;
    }

    //Метод для закрытия всплывающего окна с куками
    public KvitkiHomePage clickAllowAllCookies() {
        clickElementWhenClickable(cookieDialogButtonAllowAll);
        return this;
    }

    //Метод для клика по иконке аккаунт
    public KvitkiHomePage clickAccountIcon() {
        accountIcon.click();
        return this;
    }

    //Метод для закрытия всплывающего окна скролл с рекламой
    public KvitkiHomePage clickScrollBannersClose() {
        clickElementWhenClickable(scrollBannersClose);
        return this;
    }

    //Метод-прекондишн, объединяет все шаги выше
    public void prepareForLogin() {
        clickPopupBannerClose();
        clickAllowAllCookies();
        clickAccountIcon();
        clickScrollBannersClose();
    }
}



