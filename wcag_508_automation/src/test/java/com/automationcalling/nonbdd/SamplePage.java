package com.automationcalling.nonbdd;

import com.deque.axe.AXE;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

public class SamplePage {

    public WebDriver driver;
    private URL scriptUrl = SamplePage.class.getResource("/axe.js");

    @BeforeClass
    public void init() {
        WebDriverManager.chromedriver().setup();
        System.out.println(scriptUrl);
        driver = new ChromeDriver();
        driver.get("https://automationcalling.com/");
        System.out.println(driver);
    }

    @Test
    public void testAccessibility() {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray violations = responseJSON.getJSONArray("violations");
        if (violations.length() == 0) {
            Assert.assertTrue(true, "No violations found");
        } else {
            Assert.assertTrue(false, AXE.report(violations));
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

