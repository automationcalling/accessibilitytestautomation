package com.automationcalling.stepdefinition;

import com.automationcalling.pageObjects.HomePage;
import com.deque.axe.AXE;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import utils.AccessibiliyTestUtils;
import utils.SeleniumUtils;

import java.util.List;
import java.util.Map;

public class ExampleTestDefinition {
    private WebDriver driver;
    private HomePage homePage;
    private AccessibiliyTestUtils accessibiliyTestUtils;
    private JSONObject responseJSON;
    private JSONArray violations;
    private SeleniumUtils seleniumUtils;

    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        seleniumUtils = new SeleniumUtils();
        homePage = new HomePage(driver);
        accessibiliyTestUtils = new AccessibiliyTestUtils(driver);
    }

    @Given("Launch chrome {string} with the given url {string}")
    public void launch_chrome_with_the_given_url(String browserName, String url) {
        // Write code here that turns the phrase above into concrete actions
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver.get(url);
                driver.manage().window().maximize();
            }
        } catch (Exception e) {
            Assert.fail("Failed in launching browser" + e.getMessage());
        }
    }

    @When("User navigate to {string} page.")
    public void user_navigate_to_page(String pageLink) {
        try {
            switch (pageLink) {
                case "CONTACT":
                    homePage.clickContactMenu();
                    break;

                default:
                    break;
            }
        } catch (Exception | AssertionError e) {
            Assert.fail("Assertion error" + e.getMessage());
        }
    }

    @And("Run accessibility test with the following param")
    public void run_accessibility_test_with_the_following_param(DataTable dataTable) {
        try {
            List<Map<String, String>> datable = dataTable.asMaps(String.class, String.class);
            for (int i = 0; i < datable.size(); i++) {
                if (datable.get(i).get("FullTest").equalsIgnoreCase("Yes")) {
                    responseJSON = accessibiliyTestUtils.runAnalyze();
                    violations = responseJSON.getJSONArray("violations");
                    if (!datable.get(i).get("SkipFrames").equalsIgnoreCase("No"))
                        accessibiliyTestUtils.skipFrame();
                } else if (!datable.get(i).get("WithElement").equalsIgnoreCase("No")) {
                    if (!datable.get(i).get("SkipFrames").equalsIgnoreCase("No"))
                        accessibiliyTestUtils.skipFrame();
                    responseJSON = accessibiliyTestUtils.runAnalyzeWithSpecificWebElement(seleniumUtils.returnLocatorType(datable.get(i).get("WithElement")));
                    violations = responseJSON.getJSONArray("violations");
                } else {
                    if (!datable.get(i).get("includeSelector").equalsIgnoreCase("No")) {
                        accessibiliyTestUtils.includeSelectors(datable.get(i).get("includeSelector"));
                        if (!datable.get(i).get("excludeSelector").equalsIgnoreCase("No"))
                            accessibiliyTestUtils.excludeSelectors(datable.get(i).get("excludeSelector"));
                        if (!datable.get(i).get("SkipFrames").equalsIgnoreCase("No"))
                            accessibiliyTestUtils.skipFrame();
                    }
                    responseJSON = accessibiliyTestUtils.runAnalyze();
                    violations = responseJSON.getJSONArray("violations");
                }
            }
        } catch (Exception | AssertionError e) {
            Assert.fail("Error in running accessibility test" + e.getMessage());
        }
    }

    @Then("No Accessibiiity violation must be returned")
    public void no_Accessibiiity_violation_must_be_returned() {
        try {
            if (violations.length() == 0) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("Accessibility Errors" + AXE.report(violations));
            }
        } catch (Exception | AssertionError e) {
            Assert.fail("Assertion error" + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
