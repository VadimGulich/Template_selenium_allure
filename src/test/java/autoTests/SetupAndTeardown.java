package autoTests;

import autoTests.WorkWithFields.FieldsPageGoogle;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.TestException;
import org.testng.annotations.*;
import ru.stqa.selenium.factory.WebDriverFactory;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SetupAndTeardown {

    public WebDriver driver;
    DesiredCapabilities capabilities;
    public ConfigurationVariables CV = ConfigurationVariables.getInstance();



    @BeforeMethod(alwaysRun = true)
    public void SetUp() throws IOException {
        if (null == driver) {
            /********* Закоментить для  для запуска на своем профиле и откоментить для запуска на дефолтном ***********/
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setEnableNativeEvents(false);
//        profile.setAcceptUntrustedCertificates(true);

            /********* Раскомментить для запуска на своем профиле и закоментить для дефолтного ***********/
            ProfilesIni allProfiles = new ProfilesIni();
            FirefoxProfile profile = allProfiles.getProfile("default");

            profile.setEnableNativeEvents(false);
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);
            profile.setPreference("javascript.enabled", true);
            profile.setPreference("geo.enabled", false);

            capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            capabilities.setCapability("unexpectedAlertBehaviour", "ignore");

            System.out.println("Tests will be run (or rerun) in Firefox with custom profile...");
                driver = WebDriverFactory.getDriver(capabilities);

            this.driver.manage().timeouts().implicitlyWait(CV.implicitTimeWait, TimeUnit.SECONDS);
            this.driver.manage().window().maximize();
            this.driver.manage().deleteAllCookies();
        }


    }


    @AfterMethod()
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() != ITestResult.SUCCESS) {
            attachScreenshotToAllure();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] attachScreenshotToAllure() {
        if (null != driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        else throw new TestException("Something wrong with driver initialization...");
    }

    @AfterSuite(alwaysRun = true)
    public void deleteFiles() throws Exception {
        if (!WebDriverFactory.isEmpty()) WebDriverFactory.dismissAll();
    }

}
