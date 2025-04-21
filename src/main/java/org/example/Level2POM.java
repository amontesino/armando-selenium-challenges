package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Level2POM {
    private WebDriver driver;

    @FindBy(tagName = "dd")
    private List<WebElement> requirements;
    @FindBy(tagName = "select")
    private WebElement dropdown;
    @FindBy(xpath = "//input[@type='checkbox']")
    private List<WebElement> checkboxes;
    @FindBy(xpath = "//input[@value='2']")
    private WebElement radioTwo;
    @FindBy(name = "dateInput")
    private WebElement date;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public Level2POM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void openLevel2() {
        driver.get("C:\\Users\\midas\\Documents\\armando-selenium-challenges\\Important Resources\\InFormed\\level-2.html");
    }

    public void selectDropdown() {
        Select select = new Select(dropdown);
        List<WebElement> dropdownList = select.getOptions();
        select.selectByValue(requirements.get(0).getText());
    }

    public void checkAllBoxes() {
        for(WebElement checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public void clickRadioTwo() {
        radioTwo.click();
    }

    public void inputDate() {
        date.clear();
        date.sendKeys(requirements.get(3).getText());
    }

    public void submitForm() {
        submitButton.click();
    }

    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/resources/screenshots/level-2.png"));
    }

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            Level2POM pom = new Level2POM(driver);
            pom.openLevel2();
            pom.selectDropdown();
            pom.checkAllBoxes();
            pom.clickRadioTwo();
            pom.inputDate();
            pom.submitForm();
            driver.switchTo().alert().accept();
            pom.takeScreenshot();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(driver != null) driver.quit();
        }
    }
}
