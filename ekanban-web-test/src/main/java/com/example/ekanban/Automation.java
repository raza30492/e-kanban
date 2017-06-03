package com.example.ekanban;

import com.example.ekanban.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by mtalam on 5/21/2017.
 */
public class Automation extends BaseClass {
    private final Logger logger = LoggerFactory.getLogger(Automation.class);

    @Test
    public void testLogin() {
        String baseUrl = PropertiesUtil.getProperty("base.url");
        String email = PropertiesUtil.getProperty("user.email");
        String password = PropertiesUtil.getProperty("user.password");

        driver.get(baseUrl);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //*[@id="content"]/div/div[2]/div[1]/div/div/div/div/form/div/div[1]/span/input
        driver.findElement(By.xpath("//span/input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//span/input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//form/footer/button")).click();

//        try {
//            driver.manage().wait(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String userName = null;
        if (!driver.findElement(By.xpath("//header/nav/a[@href='/profile']")).equals(null)) {
            userName = driver.findElement(By.xpath("//header/nav/a[@href='/profile']")).getText();
        }
        Assert.assertEquals(userName, PropertiesUtil.getProperty("user.name"));
    }



}
