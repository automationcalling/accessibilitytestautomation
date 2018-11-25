package utils;

import com.deque.axe.AXE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class AccessibiliyTestUtils {
    private WebDriver driver;
    private AXE.Builder builder;
    private JSONObject responseJSON;

    private URL scriptUrl = AccessibiliyTestUtils.class.getResource("/axe.js");

    public AccessibiliyTestUtils(WebDriver driver) {
        this.driver = driver;
        builder = new AXE.Builder(driver, scriptUrl);
    }


    public JSONObject runAnalyze() {
        responseJSON = builder.analyze();
        return responseJSON;
    }

    public JSONObject runAnalyzeWithSpecificWebElement(By locator) {
        responseJSON = builder.analyze(driver.findElement(locator));
        return responseJSON;
    }

    public AccessibiliyTestUtils skipFrame() {
        builder.skipFrames();
        return this;
    }

    public AccessibiliyTestUtils includeSelectors(String selector) {
        builder.include(selector);
        return this;
    }

    public AccessibiliyTestUtils excludeSelectors(String selector) {
        builder.exclude(selector);
        return this;
    }

}
