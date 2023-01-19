package com.telran.proj.fw;

import com.google.common.io.Files;
import com.telran.proj.utils.PropertiesLoader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public static final String LOGIN_PAGE_PATH = "/accounts/login";
    private static final String ALL_PRODUCTS_CATALOGUE_PATH = "/catalogue";

    private static final String SCREENSHOT_FILE_NAME = "target/screenshots/$timestamp_screenshot.png";
    public static String defaultBaseURL = PropertiesLoader.loadProperty("defaultBaseURL");
    public static String defaultBrowser = PropertiesLoader.loadProperty("defaultBrowser");
    //    protected EventFiringWebDriver webDriver;
    protected WebDriver webDriver;
    protected String browser;
    protected String baseUrl;
    Recorder recorder;
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
        WebDriverListener customListener = new CustomListener();

        switch (browser) {
            case "CHROME":
//                webDriver = new EventFiringWebDriver(new ChromeDriver());
                webDriver = new ChromeDriver();
                break;
            case "FIREFOX":
//                webDriver = new EventFiringWebDriver(new FirefoxDriver());
                webDriver = new FirefoxDriver();
                break;
            case "SAFARI":
//                webDriver = new EventFiringWebDriver(new SafariDriver());
                webDriver = new SafariDriver();
                break;
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver = new EventFiringDecorator(customListener).decorate(webDriver);

//        webDriver.register(new MyListener());

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
        webDriver.get(baseUrl + LOGIN_PAGE_PATH);
    }

    public void goToAllProductsPage() {
        webDriver.get(baseUrl + ALL_PRODUCTS_CATALOGUE_PATH);
    }

    public String takeScreenShot() {
        String pathName = SCREENSHOT_FILE_NAME.replace("$timestamp", "" + System.currentTimeMillis());
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

    public Screenshot takeScreenshotWithScrollDown() {

        Screenshot screenshot = new AShot()
                .shootingStrategy(
                        ShootingStrategies
                        .viewportPasting(ShootingStrategies
                                .scaling(2.0f), 1000))
                // Will scale down image according to dpr specified.
                // Params:
                // dpr – device pixel ratio
                // This ratio is the number of physical device pixels corresponding to every CSS pixel.
                //Returns:
                //ShootingStrategy that will scale image according to dpr
                .takeScreenshot(webDriver);

        try {
            ImageIO.write(screenshot.getImage(), "png",
                    new File(SCREENSHOT_FILE_NAME.replace("$timestamp", "" + System.currentTimeMillis())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshot;
    }
}
