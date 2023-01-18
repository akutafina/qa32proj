package com.telran.proj.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class BaseHelper {
    private static final Duration FIVE_SECONDS_WAIT = Duration.ofSeconds(5);
    protected WebDriver webDriver;

    public BaseHelper(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void type(By locator, String text) {
        webDriver.findElement(locator).click();
        webDriver.findElement(locator).clear();
        webDriver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        webDriver.findElement(locator).click();
    }

    protected boolean elementIsPresent(By locator) {
        List<WebElement> webElements = webDriver.findElements(locator);
        return (webElements.size() > 0);
    }

    protected void clearAndType(By locator, String text) {
        waitForElementToBeClickable(locator);
        webDriver.findElement(locator).click();
        webDriver.findElement(locator).clear();
        webDriver.findElement(locator).sendKeys(text);
    }

    protected void scrollToElementAndClick(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,4000)");
        waitForElementToBeClickable(locator);
        webDriver.findElement(locator).click();
    }

    protected void scrollToElement(By locator, int px) {
        // todo: rewrite to optimise the scroll for all screen
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0," + px + ")");
        // "window.scrollBy(0, 1000)"

//        String xpath11 = "//div[@aria-label='Choose Friday, May 11th, 1990']";
//        String xpath12 = "//div[@aria-label='Choose Friday, May 12th, 1990']";
//        String xpath13 = "//div[@aria-label='Choose Friday, May 13th, 1990']";
//
//        String dateInMayXpath = "//div[@aria-label='Choose Friday, May $idxth, 1990']";
//
//        String eleventhOfMayXpath = dateInMayXpath.replaceAll('$idx',11);
//
//        String s1 = "aaa";
//        String s2 = "bbb";
//        String s3 = "ccc";
//
//        String concatenated1 = s1 + s2 + s3;
//
//        System.out.println("My s1" + s1 );
//        System.out.println("My s1 " + s1 + " " + s2); // > My s1 aaa s2 bbb

        waitForElementToBeClickable(locator);
    }

    protected void scrollToElementAndClick(By locator, int px) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0," + px + ")");
        waitForElementToBeClickable(locator);
        webDriver.findElement(locator).click();

    }

    protected WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver,FIVE_SECONDS_WAIT );
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return webDriver.findElement(locator);
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, FIVE_SECONDS_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return webDriver.findElement(locator);
    }

    protected void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(webDriver, FIVE_SECONDS_WAIT);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    protected void waitForAlert(int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
