package com.telran.proj.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomListener implements WebDriverListener {
    //    https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/events/AbstractWebDriverEventListener.html
//    https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/events/EventFiringDecorator.html
//    https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/events/WebDriverListener.html#beforeQuit(org.openqa.selenium.WebDriver)
    protected Logger logger = LoggerFactory.getLogger(CustomListener.class);

    public CustomListener() {
        super();
    }

//    @Override
////    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//    public void beforeFindElement(WebElement element, By locator) {
//        logger.info("Trying to find element by locator:" + locator);
//        System.out.println("Trying to find element by locator:" + locator);
//    }
//
//    @Override
//    public void beforeFindElements(WebElement element, By locator) {
//        logger.info("Trying to find elements by locator:" + locator);
//    }
//
//    @Override
////    public void afterFindBy(By by, WebElement element, WebDriver driver) {
//    public void afterFindElement(WebElement element, By locator, WebElement result) {
//        logger.info("Found element by locator" + locator);
//    }
//
//    @Override
//    public void afterFindElements(WebElement element, By locator, java.util.List<WebElement> result) {
//        logger.info("Found elements by locator" + locator);
//    }
//
//    @Override
////    public void onError (Throwable throwable, WebDriver driver) {
//    public void onError(java.lang.Object target, java.lang.reflect.Method method, java.lang.Object[] args, java.lang.reflect.InvocationTargetException e) {
//        logger.error("Exception caught!" + e.getMessage());
//        logger.error(e.fillInStackTrace().toString());
//    }

    @Override
    public void beforeQuit(WebDriver driver) {
        logger.info("Closing/quitting the browser!");
    }
}
