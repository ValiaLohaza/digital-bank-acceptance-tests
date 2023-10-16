package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserHelper {

    public static WebElement waitForTheVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));


    }

    public static  WebElement waitUntilElementIsClickable(WebDriver driver, WebElement element, int timeToWaitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSeconds));
        WebElement clickableElement =  wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
        return clickableElement;

    }
}
