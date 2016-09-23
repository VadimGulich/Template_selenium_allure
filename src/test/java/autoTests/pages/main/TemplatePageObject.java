package autoTests.pages.main;


import autoTests.CustomMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

public class TemplatePageObject extends CustomMethods {
     WebDriver driver;

    public TemplatePageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "Email")
    public WebElement fieldEmail;

    @FindBy(id = "next")
    public WebElement buttonNext;

    @FindBy(id = "Passwd")
    public WebElement fieldPasswd;

    @FindBy(id = "signIn")
    public WebElement buttonSignIn;

    @FindBy(id = "errormsg_0_Passwd")
    public WebElement errorMSGPassword;

    @FindBy(id = "gs_htif0")
    public WebElement fieldSearch;

    @FindBy(xpath = ".//*[@id='sblsbb']")
    public WebElement iconSearch;

    @FindBy(xpath = ".//*[@id='sblsbb']")
    public List<WebElement> iconSearchList;

//    @FindBy(linkText = "Text.ru")
//    public WebElement iconSearch;

    /**
     * ********************** Методы  *************************
     */

    @Step("Ввод почты")
    public TemplatePageObject typeEmail(String email) {
    fillInFieldValue(fieldEmail, email);
    return this;
 }
    @Step("Клик по кнопке next")
    public TemplatePageObject clickNext() {
        click(buttonNext);
        return this;
    }

    @Step("Ввод пароля")
    public TemplatePageObject typePass(String pass) {
        fillInFieldValue(fieldPasswd, pass);
        click(buttonSignIn);
        return this;
    }

    @Step("Нажать на кнопку авторизации")
    public TemplatePageObject clickSignIn() {
        click(buttonSignIn);
             return this;
    }

    @Step("Проверка сообения о ошибке")
    public TemplatePageObject assertErrMSG(String err) {
        assertThis(errorMSGPassword, err);
        return this;
    }



}

