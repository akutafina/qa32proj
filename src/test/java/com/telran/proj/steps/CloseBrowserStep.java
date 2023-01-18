package com.telran.proj.steps;

import com.telran.proj.fw.ApplicationManager;
import io.cucumber.java.After;

public class CloseBrowserStep {
    protected static ApplicationManager app;

    public CloseBrowserStep(ApplicationManager app) {
        CloseBrowserStep.app = app;
    }

    @After
    public void afterScenario() {
        app.stopApp();
        System.out.println("After is called!");
    }
}
