package utils;

import org.openqa.selenium.By;

public class SeleniumUtils {

    public By returnLocatorType(String value) {
        String[] strarray = value.split(":");
        By locator = null;
        if (strarray[0].equalsIgnoreCase("xpath")) {
            locator = By.xpath(strarray[1]);
        } else if (strarray[0].equalsIgnoreCase("css")) {
            locator = By.cssSelector(strarray[1]);
        } else if (strarray[0].equalsIgnoreCase("name")) {
            locator = By.name(strarray[1]);
        } else if (strarray[0].equalsIgnoreCase("classname")) {
            locator = By.className(strarray[1]);
        } else if (strarray[0].equalsIgnoreCase("tagname")) {
            locator = By.tagName(strarray[1]);
        } else if (strarray[0].equalsIgnoreCase("linktext")) {
            locator = By.linkText(strarray[1]);
        } else if (strarray[0].equalsIgnoreCase("partialLinkText")) {
            locator = By.partialLinkText(strarray[1]);
        }
        return locator;
    }
}

