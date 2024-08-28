import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    OrderData orderData;

    public OrderTest(OrderData orderData) {
        this.orderData = orderData;
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        //Run теста без запуска браузера
        //  options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
       // options.setBinary("C:/chrome2/chrome-win64/chrome.exe");
        driver = new ChromeDriver(options);
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    /*  @Before
    public void setUp(){
        FirefoxOptions options = new FirefoxOptions();
        //Run теста без запуска браузера
         // options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        driver = new FirefoxDriver(options);
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
*/
    @Parameterized.Parameters
    public static Object[][] orders() {
        //Вопросы и ответы, проверка на правильность ответа
        OrderData order1 = new OrderData();
        order1.firstName = "Иван";
        order1.secondName = "Пупкин";
        order1.address = "Гагарина 111";
        order1.color = "чёрный жемчуг";
        order1.date = "26.08.2024";
        order1.metroStation = "Сокольники";
        order1.phone = "877777777777";
        order1.comment = "Иван";
        order1.time = "сутки";
        order1.entry = 0;
        OrderData order2 = new OrderData();
        order2.firstName = "Вася";
        order2.secondName = "Пупкин";
        order2.address = "Гагарина 222";
        order2.color = "серая безысходность";
        order2.date = "29.08.2024";
        order2.metroStation = "Сокольники";
        order2.phone = "877777777733";
        order2.comment = "Вася";
        order2.time = "сутки";
        order2.entry = 1;
        return new Object[][]{
                {order1},
                {order2}
        };
    }

    @Test
    //Проверяем верхнюю кнопку заказа
    public void checkFirstEntry() {
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.acceptCookie();
        objOrderPage.clickButtonFirst();
        objOrderPage.checkOrderFormHeader("Для кого самокат");

    }

    @Test
    //Проверяем нижную кнопку заказа
    public void checkSecondEntry() {
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.acceptCookie();
        objOrderPage.clickButtonSecond();
        objOrderPage.checkOrderFormHeader("Для кого самокат");
    }


    @Test
    public void createOrder() {
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.acceptCookie();
        if (orderData.entry == 0) {
            objOrderPage.clickButtonFirst();
        } else {
            objOrderPage.clickButtonSecond();
        }
        objOrderPage.setFirstName(orderData.firstName);
        objOrderPage.setSecondName(orderData.secondName);
        objOrderPage.setOrderAddress(orderData.address);
        objOrderPage.selectMetroStation(orderData.metroStation);
        objOrderPage.setPhoneNumber(orderData.phone);
        objOrderPage.clickNextButton();
        driver.findElement(By.xpath("//div[contains(text(),'Про аренду')]")).isDisplayed();
        objOrderPage.setDate(orderData.date);
        objOrderPage.selectTime(orderData.time);
        objOrderPage.selectColor(orderData.color);
        objOrderPage.setComment(orderData.comment);
        objOrderPage.confirmOrder();
        objOrderPage.checkCreatedOrder();

    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
