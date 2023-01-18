package com.telran.proj.steps;

import com.telran.proj.fw.ApplicationManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class ItemListContainerSteps {

    protected static ApplicationManager app;

    public ItemListContainerSteps(ApplicationManager app) {
        ItemListContainerSteps.app = app;
    }

    @When("All products page is opened")
    public void allProductsPageIsOpened() {
        app.goToAllProductsPage();
    }

    @Then("All products page is loaded")
    public void allProductsPageIsLoaded() {
        Assert.assertTrue(app.getItemListContainerHelper().isContainerPresent());
    }

}
