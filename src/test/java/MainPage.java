import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    By cookieAcceptButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookie() {
        driver.findElement(cookieAcceptButton).click();
    }

    public void clickQuestion(String question) {
        driver.findElement(By.xpath("//div[text()='" + question + "']")).click();
    }

    public void checkAnswer(String answer) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement answerShown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='" + answer + "']")));
        boolean isDisplayed = answerShown.isDisplayed();
        Assert.assertTrue(isDisplayed);
    }


}

