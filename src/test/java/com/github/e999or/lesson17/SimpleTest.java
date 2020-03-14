package com.github.e999or.lesson17;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SimpleTest{
    private static final Logger LOG = LoggerFactory.getLogger(SimpleTest.class);
    private WebDriver webDriver;

    @BeforeMethod
    public void beforeTestMethod() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        LOG.info("Before ComplexTest method");
    }



    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        webDriver.get("https://savkk.github.io/selenium-practice/");

        webDriver.findElement(By.id("select")).click();
        WebElement select = webDriver.findElement(By.name("hero"));
        Select selectHero = new Select(select);
        selectHero.selectByVisibleText("Donald Ervin Knuth");
        WebElement selectL = webDriver.findElement(By.name("languages"));
        Select selectLanguages = new Select(selectL);
        selectLanguages.selectByVisibleText("Java");
        selectLanguages.selectByVisibleText("C++");
        WebElement inspectedFild = webDriver.findElement(By.xpath("//label[4]"));
        webDriver.findElement(By.id("go")).click();
        Assert.assertEquals(inspectedFild.getAttribute("innerText"),"Java, C++");

        webDriver.findElement(By.linkText("Great! Return to menu")).click();


        webDriver.findElement(By.id("form")).click();
        webDriver.findElement(By.xpath("//label[.='First Name:']/following::input")).sendKeys("Егор");
        webDriver.findElement(By.xpath("//label[.='Last Name:']/following::input")).sendKeys("Трофимов");
        webDriver.findElement(By.cssSelector("input[type=email]")).sendKeys("e999or@bk.ru");
        webDriver.findElement(By.xpath("//label[.='Sex:']/following::input")).click();
        webDriver.findElement(By.xpath("//label[.='Address:']/following::input")).sendKeys("Москва");
        webDriver.findElement(By.xpath("//label[.='Avatar:']/following::input")).sendKeys("C:\\1\\aiteko.homework1\\tastseleniumlesson16\\src\\test\\resources\\s1200.jpg");
        webDriver.findElement(By.xpath("//label[.='Tell me something about yourself']/following::textarea")).sendKeys("Рокен-Ролл");
        webDriver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

        WebElement returnToMenu = webDriver.findElement(By.linkText("Great! Return to menu"));
        Assert.assertEquals(returnToMenu.getAttribute("innerText"),"Great! Return to menu");
        returnToMenu.click();

        webDriver.findElement(By.id("iframe")).click();
        webDriver.switchTo().frame(webDriver.findElement(By.id("code-frame")));
        String [] str = webDriver.findElement(By.id("code")).getText().split(":");
        String noSpace = str[1].replaceAll(" ", "");
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.name("code")).sendKeys(noSpace);
        webDriver.findElement(By.name("ok")).click();
        WebElement returnToMenuIframe = webDriver.findElement(By.linkText("Great! Return to menu"));
        Assert.assertEquals(returnToMenuIframe.getAttribute("innerText"),"Great! Return to menu");
        returnToMenuIframe.click();





        Thread.sleep(10000);
        webDriver.quit();


    }


}
