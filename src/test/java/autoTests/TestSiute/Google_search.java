package autoTests.TestSiute;

import autoTests.SetupAndTeardown;
import autoTests.pages.main.TemplatePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;



public class Google_search extends SetupAndTeardown {
        public static TemplatePageObject googlePage;

    @BeforeMethod
    public void openURLgoogle(){
        googlePage = new TemplatePageObject(driver);
        googlePage.openURLservice(CV.baseUrl);
    }

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {"password","vidok@mail.ru"},
                {"password","vidok@mail.ru"}
        };
    }


    //<editor-fold desc="Тестовый пример заполнение полей">
    @Title("Вход в Google аккаунт")
    @Description("Ввод не верного пароля")
    @Severity(SeverityLevel.CRITICAL)
    @Features("Особенности")
    @Stories("Истории")
    @Test(dataProvider = "dataProvider",enabled = true, priority = 1)
    public void Test_Example_One(String pass,String email) throws Exception {

      googlePage.typeEmail(email)
                .clickNext()
                .typePass(pass)
                .clickSignIn()
                .assertErrMSG("Введите адрес электронной почты");
    }
    //</editor-fold>

}
