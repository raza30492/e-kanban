package com.example.ekanban;

import com.example.ekanban.util.PropertiesUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;

/**
 * Created by Md Jawed Akhtar on 02-06-2017.
 */
public class BaseClass {

    private final Logger logger = LoggerFactory.getLogger(BaseClass.class);
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        logger.info("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", PropertiesUtil.getEkanbanHome() + File.separator + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing chrome browser");
            driver.quit();
        }
    }

}
