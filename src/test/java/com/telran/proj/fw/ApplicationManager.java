package com.telran.proj.fw;

import com.google.common.io.Files;
import com.telran.proj.utils.PropertiesLoader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public static final String LOGIN_PAGE_PATH = "/accounts/login";
    private static final String ALL_PRODUCTS_CATALOGUE_PATH = "/catalogue";
    protected EventFiringWebDriver webDriver;
    protected String browser;

    protected String baseUrl;

    Recorder recorder;

    public static String defaultBaseURL = PropertiesLoader.loadProperty("defaultBaseURL");
    public static String defaultBrowser = PropertiesLoader.loadProperty("defaultBrowser");
    LoginPageHelper loginPageHelper;
    ItemListContainerHelper itemListContainerHelper;

    public ApplicationManager() {
        baseUrl = System.getProperty("baseUrl", defaultBaseURL);
        browser = System.getProperty("browser", defaultBrowser);
        initApp();
    }

    public LoginPageHelper getLoginPageHelper() {
        return loginPageHelper;
    }

    public ItemListContainerHelper getItemListContainerHelper() {
        return itemListContainerHelper;
    }

    public void initApp() {
        switch (browser) {
            case "CHROME":
                webDriver = new EventFiringWebDriver(new ChromeDriver());
                break;
            case "FIREFOX":
                webDriver = new EventFiringWebDriver(new FirefoxDriver());
                break;
            case "SAFARI":
                webDriver = new EventFiringWebDriver(new SafariDriver());
                break;
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.register(new MyListener());

        loginPageHelper = new LoginPageHelper(webDriver);
        itemListContainerHelper = new ItemListContainerHelper(webDriver);

        goToMainPage();
    }

    public void stopApp() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webDriver.quit();
    }

    //URL to the property loader
    public void goToMainPage() {
        webDriver.get(baseUrl);
    }

    public void goToRegistrationAndLoginPage() {
        webDriver.get(baseUrl+LOGIN_PAGE_PATH);

    }

    public void goToAllProductsPage() {
        webDriver.get(baseUrl + ALL_PRODUCTS_CATALOGUE_PATH);
    }

    public String takeScreenShot() {
        String pathName = "screenshots/" + System.currentTimeMillis() + "png";
        File tmpScreenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File screenShotFile = new File(pathName);
        try {
            Files.copy(tmpScreenshotFile, screenShotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathName;
    }

    public void startRecording() throws IOException, AWTException {
        String pathName = "records/recording";

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        recorder = new Recorder(gc, pathName);
        recorder.start();

    }

    public void stopRecording() throws IOException {
        recorder.stop();
    }
}
