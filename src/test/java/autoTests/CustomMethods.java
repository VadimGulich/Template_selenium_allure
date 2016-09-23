package autoTests;



import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;



public class CustomMethods

{
    public WebDriver driver;
    public CustomMethods(WebDriver driver) {
        this.driver = driver;
    }

    public int getRandomNumber(int n) {
        Random random = new Random();
        int RandomNumber = random.nextInt(n);
        return RandomNumber;
    }

	public void waitForElementPresent(WebDriver driver, By locator, int timeoutInSeconds, int pollingInterval)
			throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
	}

	public void waitForElementPresent(WebDriver driver, WebElement webElement, int timeoutInSeconds, int pollingInterval)
			throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds, pollingInterval);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	//Проверяем что элемент присутствует и видем
	public void CheckElementPresent(WebElement element) throws InterruptedException
	{
		Assert.assertEquals(true, element.isDisplayed());
		Assert.assertEquals(true, element.isEnabled());
	}


	// Method for file attachment
	public void attachDocument(WebElement locator, String document, WebDriver driver) {
		String script = "var element = arguments[0];" + "element.style.display='inline';";
		((JavascriptExecutor) driver).executeScript(script, locator);

		File file = new File(document);
		locator.sendKeys(file.getAbsolutePath());

		// Wait attach upload
		//TODO: add counter condition to avoid infinite loop
		while (!locator.isEnabled()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void pause(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

@Step("Тест степа")
	public void click(WebElement webElement){
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
	}

	public void openURLservice( String url){
		driver.get(url);
	}


	public void assertThis( WebElement webElement, String textAssert){
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(webElement));
		Assert.assertEquals(webElement.getText(), textAssert);
	}


    public void fillInFieldValue(WebElement webElement, String value){
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void setFieldFile(WebElement webElement, String sPathFile){
         String sScript = "var element = arguments[0];" + "element.style.display='inline';";
        ((JavascriptExecutor) driver).executeScript(sScript, webElement);
        
        File oFile = new File(sPathFile);
        webElement.sendKeys(oFile.getAbsolutePath());

        // Wait attach upload
        //TODO: add counter condition to avoid infinite loop
        while (!webElement.isEnabled()) {
                try {
                        Thread.sleep(2000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
        }        
    }
    //public void attachDocument(WebElement locator, String document, WebDriver driver) {
    //}    

    public String getText(WebElement webElement) throws Exception {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(webElement));
        String answer = null;
        try {
            answer = webElement.getText();
        }catch (Exception e){
            throw new Exception(e);
        }
        return answer;
    }

    public void setFieldSelectByText(WebElement webElement, String text) {
        Select select = new Select(webElement);
        select.selectByVisibleText(text);
    }


    /** Java анотация
     *
     * @param webElement
     * @param value
     */
    public void setFieldSelectByValue(WebElement webElement, String value) {
        Select select = new Select(webElement);
        select.selectByValue(value);
    }


    public String getDateAfterNDaysInFormat(int day, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(calendar.getTime());
    }

    /**
     *
     * @return
     */
    public String getRandomEmailServiceName() {
        String domains[] = new String[]{"@gmail.com", "@mail.ru", "@yandex.com", "@hotmail.com", "@yahoo.com", "@aol.com", "@rambler.ru", "@email.ua", "@ukr.net"};
        return domains[(int) (Math.random() * domains.length)];
    }

    public static void logger(String message) {
        System.out.println(message);
    }

    public String getRandomNumberFromDiapason(int min, int max) {
        Random random = new Random();
        return String.valueOf(min + random.nextInt(max));
    }

    public String getTodayDateInFormat(String format) {
        // http://cybern.ru/java-datetime.html
        // format for example: "dd.MM.yyyy", "dd.MM.yyyy hh:mm", "yyyy-MM-dd hh:mm","dd.MM.yyyy HH:MM:SS"
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(calendar.getTime());
    }

    public static String GeneratorRandomLetter(){
//        char rc1 = (char)('a'+ new Random().nextInt(26));
//        char rc2 = (char)('a'+ new Random().nextInt(26));
//        char rc3 = (char)('a'+ new Random().nextInt(26));
//        char rc4 = (char)('a'+ new Random().nextInt(26));
//        char rc5 = (char)('a'+ new Random().nextInt(26));
        //----- через UUID ---//
//        UUID u = UUID.randomUUID();
//        String uuid = u.toString().replace("-", "").replaceAll(
//                "[0-9]+",
//                "");
//        System.out.println(uuid);

        SecureRandom random = new SecureRandom();
        String res = new BigInteger(100, random).toString(32).replaceAll("([0-9])+", "");
        System.out.println(res);
        String url = "https://"+res+".com";
        System.out.println("Рандомный URL " + url);
        return String.valueOf(url);
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public  void write(String fileName, String text)throws IOException {
        //Определяем файл
        File file = new File(fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if(!file.exists()){
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
//            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            FileWriter sw = new FileWriter(file.getAbsoluteFile(),true);

            try {
                //Записываем текст у файл
                sw.write(text + "\n");

//                System.getProperty("line.separator");
//                System.lineSeparator();
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                sw.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  String read(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        exists(fileName);
        File file = new File(fileName);

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();

    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }


    public  void assertList (List<WebElement> elements ,String text) throws Exception {
        elements.size();
        for (WebElement element : elements) {
            Assert.assertTrue(element.getText().contains(text));
        }
    }

    public  void scrollToElement (WebElement element) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , element);
    }

//    public void switchCase(WebElement element, String valid, String text1,String text2) throws Exception {
//        switch (valid) {
//            case "param1":
//                Assert.assertTrue(element.getText().contains(text1));
//                break;
//
//            case "param2":
//                Assert.assertTrue(element.getText().contains(text2));
//                break;
//
//            default:
//                throw new Exception("ERROR VALIDATION");
//        }
//    }

    public String verifyTextPresent(String text) {
        if (driver.getPageSource().contains(text)) return "";
        else System.out.println("ERROR: NOT FOUND TEXT: \"" + text + "\"");
        return "\n" + "ERROR: NOT FOUND TEXT: \"" + text + "\"";
    }

    public void tryCatch(WebElement element,String text) {
        Boolean status;
        try {
            Assert.assertTrue(element.getText().contains(text));
            status = true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            status = false;
        }
        Assert.assertTrue(status, "ERROR MESSAGE");
    }

    public void attachFile (WebElement element,String path) {
        File file = new File(path);
        element.sendKeys(file.getAbsolutePath());
    }

    public void navigationTiming() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Получаем время Load Event End (окончание загрузки страниы)
        long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        // Получаем Navigation Event Start (начало перехода)
        long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        // Разница между Load Event End и Navigation Event Start - это время загрузки страницы
        System.out.println("Page Load Time is " + (loadEventEnd - navigationStart)/1000 + " seconds.");
    }

    public void assertCSSColor (WebElement element,String color) {
        Assert.assertEquals(element.getCssValue("color"),color);
    }

    public void setFieldCalendar (WebDriver driver,String serviceName, String cssSelector, String data) {

        WebElement webElement = driver.findElement(By.cssSelector("."+serviceName+"_--_"+cssSelector));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(webElement));
        ((JavascriptExecutor) driver).executeScript("angular.element(document.getElementsByName('"+cssSelector+"')[0]).removeAttr('readonly');");
        webElement.click();
        webElement.clear();
        webElement.sendKeys(data);
    }

}
