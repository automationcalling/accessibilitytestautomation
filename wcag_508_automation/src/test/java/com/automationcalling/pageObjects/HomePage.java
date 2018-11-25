package com.automationcalling.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public By contactMenu = By.linkText("CONTACT");

    public void clickContactMenu() {
        driver.findElement(contactMenu).click();
    }
}
