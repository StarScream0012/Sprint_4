import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    By cookieAcceptButton = By.id("rcc-confirm-button");
    By orderButtonFirst = By.xpath("//button[@class='Button_Button__ra12g']");
    By orderButtonSecond = By.xpath("//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");
    By firtsNameInput = By.xpath("//input[@placeholder='* Имя']");
    By secondNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    By orderAdressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    By metroDropDown = By.xpath("//input[@placeholder='* Станция метро']");
    By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    By nextButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    By datePicker = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    By timeDropDown = By.xpath("//span[@class='Dropdown-arrow']");
    By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    By orderButton=By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    By yesButton = By.xpath("//button[contains(text(),'Да')]");
    By showStatusButton = By.xpath("//button[contains(text(),'Посмотреть статус')]");



    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    public void acceptCookie(){
        driver.findElement(cookieAcceptButton).click();
    }
    public void clickButtonFirst(){
        driver.findElement(orderButtonFirst).click();
    }
    public void clickButtonSecond(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButtonSecond));
        buttonElement.click();
    }
    //Проверка на отображение хедера форма заказа
    public void checkOrderFormHeader(String header){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+header+"']")));
        boolean isDisplayed = headerElement.isDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    public void setFirstName(String firstName) {
        driver.findElement(firtsNameInput).sendKeys(firstName);
    }

    public void setSecondName(String secondName) {
        driver.findElement(secondNameInput).sendKeys(secondName);
    }

    public void setOrderAddress(String address) {
        driver.findElement(orderAdressInput).sendKeys(address);
    }

    public void selectMetroStation(String metroStation) {
        WebElement metroInput = driver.findElement(metroDropDown);
        metroInput.click();
        metroInput.sendKeys(metroStation);
        driver.findElement(By.xpath("//div[contains(text(),'" + metroStation + "')]")).click(); // Для выбора станции метро
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneInput).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement buttonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nextButton));
        buttonElement.click();
    }

    public void setDate(String date) {
        driver.findElement(datePicker).sendKeys(date);
    }

    public void selectTime(String time) {
        WebElement timeDropdown = driver.findElement(timeDropDown);
        timeDropdown.click();
        driver.findElement(By.xpath("//div[contains(text(),'" + time + "')]")).click();
    }

    public void selectColor(String color) {
        driver.findElement(By.xpath("//label[contains(text(),'" + color + "')]")).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {
        driver.findElement(orderButton).click();
        driver.findElement(By.xpath("//div[contains(text(),'Хотите оформить заказ?')]")).click();
        driver.findElement(yesButton).click();
    }

    public void checkCreatedOrder(){
        driver.findElement(By.xpath("//div[contains(text(),'Заказ оформлен')]")).isDisplayed();
    }
    public void clickShowStatusButton() {
        driver.findElement(showStatusButton).click();
    }

}
