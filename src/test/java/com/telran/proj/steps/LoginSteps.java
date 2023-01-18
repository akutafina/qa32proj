package com.telran.proj.steps;

import com.telran.proj.fw.ApplicationManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    protected static ApplicationManager app;

    public LoginSteps(ApplicationManager app) {
        LoginSteps.app = app;
    }

    @Given("Login page is opened")
    public void loginPageIsOpened() {
        app.goToRegistrationAndLoginPage();
    }


    @And("Login button is clicked")
    public void loginButtonIsClicked() {
        app.getLoginPageHelper().clickLoginBtn();
    }

    //    https://cucumber.io/docs/cucumber/cucumber-expressions/
    @When("login credentials \\({string} and {string}) are entered")
    public void loginCredentialsAndAreEntered(String email, String pwd) {
        app.getLoginPageHelper().enterEmail(email);
        app.getLoginPageHelper().enterPassword(pwd);
    }

    @Then("error message appears")
    public void errorMessageAppears() {
        Assert.assertTrue("Error messages appear", app.getLoginPageHelper().hasErrorMsg());
    }

}
