package autoTests.TestSiute;

import autoTests.SetupAndTeardown;
import autoTests.WorkWithFields.FieldsPageGoogle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Google_acc extends SetupAndTeardown {

    public static FieldsPageGoogle googlePage;

    @BeforeMethod
    public void openURLgoogle(){
        googlePage = new FieldsPageGoogle(driver);
        googlePage.openURLservice(CV.baseUrl);

    }

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, priority = 1)
    public void Test_Example_One() throws Exception {

        String badPass = "password";
        String email = CV.email;

        googlePage.typeEmail(email)
                  .clickNext()
                  .typePass(badPass)
                  .clickSignIn()
                  .assertErrMSG("Введите адрес электронной почты");
    }
    //</editor-fold>

    @Test(enabled = true, priority = 1)
    public void Test_Example_Two() throws Exception {

        String badPass = "password";
        String email = "v-i-d-o-k@mail.ru";

        googlePage.author(email,badPass)
                  .assertErrMSG("Введите адрес электронной почты");
    }
}
