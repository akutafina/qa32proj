package com.telran.proj.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageHelper extends BaseHelper {

    public LoginPageHelper(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterEmail(String email) {
        type(By.id("id_login-username"), email);
    }

    public void enterPassword(String pwd) {
        type(By.id("id_login-password"), pwd);
    }

    public void clickLoginBtn() {
        click(By.cssSelector("button[name='login_submit']"));
    }

    public boolean hasErrorMsg() {
        return elementIsPresent(By.cssSelector(".alert.alert-danger"));
    }
}