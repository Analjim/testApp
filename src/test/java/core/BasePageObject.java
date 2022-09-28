package core;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
//import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import core.BaseTestClass;


public class BasePageObject {
	//protected WebDriver seleniumDriver = Driver.seleniumDriver;
    protected WebDriver seleniumDriver;
    public static Wait<WebDriver> wait;
    protected Actions actions;
    public static Logger log;
    
    public BasePageObject(WebDriver driver) {
        System.out.println("BASEPAGEOBJECT");
        seleniumDriver = driver;
        wait = new FluentWait<WebDriver>(seleniumDriver)
        		.withTimeout(Duration.ofSeconds(BaseTestClass.explicitWait))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        actions = new Actions(seleniumDriver);
        PageFactory.initElements(seleniumDriver, this);
        log = Logger.getLogger("Tests");
    }

    /**
     * Scrolls to the specified element.
     * @param element
     */
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) seleniumDriver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Checks for element presence on page
     * @param by     Element locator
     * @return true if element is present on page, false otherwise
     */
    public boolean isElementPresent(By by) {
        return seleniumDriver.findElements(by).size() > 0;
    }

    /**
     * Waits for specified element to become visible.
     * @param element
     * Returns visible element or null
     */
    public WebElement isElementDisplayed(WebElement element){
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return element;
        }
        catch(TimeoutException e){
            return null;
        }
    }

    public WebElement isElementClickable(WebElement element){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return element;
        }
        catch(TimeoutException e){
            return null;
        }
    }
    /**
     * Insert pause in miliseconds
     *
     * @param n pause in miliseconds
     */
    public void pause(int n) {
        Long milisec = (long) n;
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            
        }
    }

    /**
     * Waits predefined period of time for ajax calls to complete. Used if site is jQuery based.
     * Returns true or false
     */
    public boolean isjQueryAjaxCompleted(){
        try{
            wait.until(AdditionalConditions.jQueryAJAXCallsHaveCompleted());
            return true;
        }
        catch(TimeoutException e){
            return false;
        }
    }
    
    public String generateUniqueString(){
    	//return UUID.randomUUID().toString();
    	return UUID.randomUUID().toString().substring(0, 6).replace("-", "");
    }
}
