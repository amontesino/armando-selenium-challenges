package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class Level5POM {
    private WebDriver driver;

    @FindBy(tagName = "input")
    private WebElement textInput;
    @FindBy(xpath = "//button[2]")
    private WebElement submit;

    public Level5POM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLevel5() {
        driver.get("C:\\Users\\midas\\Documents\\armando-selenium-challenges\\Important Resources\\InFormed\\level-5.html");
    }

    public void enterText() {
        for (int i = 0; i < 101; i++) {
            textInput.sendKeys("a");
            driver.switchTo().alert().accept();
        }
        submit.click();
    }

    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/resources/screenshots/level-5.png"));
    }

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            Level5POM pom = new Level5POM(driver);
            pom.openLevel5();
            pom.enterText();
            driver.switchTo().alert().accept();
            pom.takeScreenshot();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(driver != null) driver.quit();
        }
    }
}