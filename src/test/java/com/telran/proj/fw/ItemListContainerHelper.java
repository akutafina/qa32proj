package com.telran.proj.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemListContainerHelper extends BaseHelper {

    public ItemListContainerHelper(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isContainerPresent() {
        return elementIsPresent(By.id("top_page"));
    }


}
