Feature: This test about 508_WCAG Compliance Automation test with possible Scenarios covered by Selenium_Java_AXE

  Scenario: To Run full accessibility test using AXE API for the given page
    Given Launch chrome "chrome" with the given url "https://automationcalling.com/"
    When User navigate to "CONTACT" page.
    And Run accessibility test with the following param
      | FullTest | SkipFrames | WithOptions | includeSelector | excludeSelector | WithElement |
      | Yes      | No         | No          | No              | No              | No          |
    Then No Accessibiiity violation must be returned

  Scenario: To Run accessibility test using AXE API with include and exclude selector
    Given Launch chrome "chrome" with the given url "https://automationcalling.com/"
    When User navigate to "CONTACT" page.
    And Run accessibility test with the following param
      | FullTest | SkipFrames | WithOptions | includeSelector | excludeSelector | WithElement |
      | No       | No         | No          | title           | h1              | No          |
    Then No Accessibiiity violation must be returned

  Scenario: To Run accessibility test using AXE API with specific element
    Given Launch chrome "chrome" with the given url "https://automationcalling.com/"
    When User navigate to "CONTACT" page.
    And Run accessibility test with the following param
      | FullTest | SkipFrames | WithOptions | includeSelector | excludeSelector | WithElement  |
      | No       | No         | No          | No              | No              | name:g2-name |
    Then No Accessibiiity violation must be returned