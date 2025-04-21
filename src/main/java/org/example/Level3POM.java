package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Level3POM {
    private WebDriver driver;

    @FindBy(tagName = "input")
    private List<WebElement> textInputs;
    @FindBy(tagName = "button")
    private WebElement submitButton;

    public Level3POM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void openLevel3() {
        driver.get("C:\\Users\\midas\\Documents\\armando-selenium-challenges\\Important Resources\\InFormed\\level-3.html");
    }

    public void fillTextInputs() {
        JavascriptExecutor unhide = (JavascriptExecutor) driver;

        for (int i = 0; i < textInputs.size(); i++) {
            if (!textInputs.get(i).isDisplayed()) {
                unhide.executeScript(
                        "const inputs = document.getElementsByTagName(\"input\");" +
                                ""
                );
            }
        }
    }

    public void submit() {
        submitButton.click();
    }

    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/main/resources/screenshots/level-3.png"));
    }

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            Level3POM pom = new Level3POM(driver);
            pom.openLevel3();
            pom.fillTextInputs();
            pom.submit();
            driver.switchTo().alert().accept();
            pom.takeScreenshot();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(driver != null) driver.quit();
        }
    }
}
