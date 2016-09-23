package autoTests.WorkWithFields;

import autoTests.pages.main.TemplatePageObject;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Privat24 on 21.09.2016.
 */
public class FieldsPageGoogle extends TemplatePageObject {
    public FieldsPageGoogle(WebDriver driver) {
        super(driver);
    }

    @Step("Авторизация")
    public FieldsPageGoogle author(String email,String pass) {
        typeEmail(email);
        clickNext();
        typePass(pass);
        clickSignIn();
        return this;
    }
}

