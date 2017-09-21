package com.example.ekanban;

import com.example.ekanban.entity.Flow;
import com.example.ekanban.entity.Step;
import com.example.ekanban.entity.Test;
import com.example.ekanban.util.DbUtil;
import com.example.ekanban.util.MiscUtil;
import com.example.ekanban.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mdzahidraza on 03/06/17.
 */
public class Automation {

    private static final Logger logger = LoggerFactory.getLogger(Automation.class);
    private static final Logger LOG_REPORT = LoggerFactory.getLogger("report");


    private static WebDriver driver;



    public static void main(String[] args){
        /*Initialize database from excel file*/
        DbUtil.initialize();
        /*Set up Chrome WebDriver*/
        setUp();

        String baseUrl = PropertiesUtil.getProperty(Constants.BASE_URL);
        /* filter only flows that need to be run*/
        List<Flow> flowList = DbUtil.getAllFlow().stream()
                .filter(flow -> flow.getRun().trim().equalsIgnoreCase("Y"))
                .collect(Collectors.toList());



        flowList.forEach(flow -> {
            LOG_REPORT.info("FLOW: id = {}, description = {}", flow.getId(), flow.getDesc());

            List<Test> tests = DbUtil.getAllTestForFlow(flow).stream()
                    .filter(test -> test.getRun().trim().equalsIgnoreCase("Y"))
                    .collect(Collectors.toList());

            tests.forEach(test -> {
                LOG_REPORT.info("TEST: id = {}, description = {} ", test.getId(), test.getDesc());
                List<Step> steps = DbUtil.getAllStepsForTest(test);
                //Open url for the test
                driver.get(baseUrl+ test.getUrl());
                //Sort by step no in ascending order
                Collections.sort(steps, Comparator.comparing(Step::getStepNo));

                steps.forEach(step -> {
                    LOG_REPORT.debug("STEP: step no = {}, desc = {}",step.getStepNo(), step.getDesc());
                    switch (step.getAction()){
                        case WAIT:
                            switch (step.getCondition()){
                                case PRESENCE:  (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(step.getXpath())));
                                    break;
                                case INVISIBLE:   //(new WebDriverWait(driver, 10)).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(step.getXpath()))));
                                    (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(step.getXpath()))));
                                    break;
                                case MATCH_URL: (new WebDriverWait(driver, 10)).until(ExpectedConditions.urlContains(step.getValue()));
                                    break;
                            }
                            break;

                        case SENDKEY:   WebElement e = driver.findElement(By.xpath(step.getXpath()));
                                        e.clear();
                                        e.sendKeys(step.getValue().trim());
                            break;

                        case CLICK:
                            if (step.getOccurence() == Occurence.SINGLE){
                                driver.findElement(By.xpath(step.getXpath())).click();
                            }else if (step.getOccurence() == Occurence.MULTIPLE) {
                                List<WebElement> elements = driver.findElements(By.xpath(step.getXpath()));
                                elements.get(step.getElementNo()-1).click();
                            }
                            break;

                        case ASSERT:    String actualValue = driver.findElement(By.xpath(step.getXpath())).getText();
                            if (actualValue.equalsIgnoreCase(step.getValue())){
                                LOG_REPORT.info("ASSERTION: PASSED, Step: {}", step.getDesc());
                            }else {
                                LOG_REPORT.error("ASSERTION:  FAILED, Step: {}, . actual value = {}, expected value = {}", step.getDesc(), actualValue, step.getValue());
                            }
                            break;

                        case SEARCH:
                            List<WebElement> elements = driver.findElements(By.xpath(step.getXpath()));
                            boolean found = false;
                            for (WebElement element: elements) {
                                if (element.findElement(By.tagName("span")).getText().equalsIgnoreCase(step.getValue())) {
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                LOG_REPORT.info("SEARCH: Successful.");
                            }else {
                                LOG_REPORT.error("SEARCH: Unsuccessful.");
                            }
                            break;

                        case SELECT:
                            break;

                        case SUBMIT:    driver.findElement(By.xpath(step.getXpath())).click();
                            break;
                    }



                });
                logger.info("finished executing Test Case: {}", test.getDesc());
            });
            logger.info("finished executing flow: {}", flow.getDesc());
        });

        tearDown();
    }

    public static void setUp() {
        logger.info("launching chrome browser");
        String dName = null;
        String os = System.getProperty("os.name").toLowerCase();

        if (os.indexOf("win") >= 0) {
            dName = "chromedriver.exe";
        }else if (os.indexOf("mac") >= 0) {
            dName = "chromedriver";
        }else {
            logger.error("OS not recognised");
        }

        System.setProperty("webdriver.chrome.driver", MiscUtil.getEkanbanHome() + File.separator + dName);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    public static void tearDown() {
        if (driver != null) {
            logger.info("Closing chrome browser");
            driver.quit();
        }
    }
}

