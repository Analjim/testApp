package pages;

import core.BaseTestClass;
import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.Wait;
import java.lang.Thread;

import java.util.concurrent.TimeUnit;

public class RegisterPage extends Driver {
	@FindBy(id = "loginPanel")
    public WebElement loginForm;
    @FindBy(className = "captionone")
    public WebElement atmServicesList;
    @FindBy(className = "captiontwo")
    public WebElement onlineServicesList;
    @FindBy(className = "events")
    public WebElement events;
    @FindBy(linkText = "Register")
    public WebElement registerLink;
    @FindBy(id = "customerForm")
    public WebElement registerForm;
    
  //register fields
  	@FindBy(id = "customer.firstName")
      public WebElement firstName;
  	@FindBy(id = "customer.lastName")
      public WebElement lastName;
  	@FindBy(id = "customer.address.street")
      public WebElement address;
  	@FindBy(id = "customer.address.city")
      public WebElement city;
  	@FindBy(id = "customer.address.state")
      public WebElement state;
  	@FindBy(id = "customer.address.zipCode")
      public WebElement zipCode;
  	@FindBy(id = "customer.phoneNumber")
      public WebElement phoneNumber;
  	@FindBy(id = "customer.ssn")
      public WebElement ssn;
  	@FindBy(id = "customer.username")
      public WebElement username;
  	@FindBy(id = "customer.password")
      public WebElement password;
  	@FindBy(id = "repeatedPassword")
      public WebElement confirmPassword;
  	@FindBy(css = "input[value='Register']")
      public WebElement registerBtn;
  	
  	//register fields' errors
  	@FindBy(id = "customer.firstName.errors")
      public WebElement firstNameRequired;
  	@FindBy(id = "customer.lastName.errors")
      public WebElement lastNameRequired;
  	@FindBy(id = "customer.address.street.errors")
      public WebElement addressRequired;
  	@FindBy(id = "customer.address.city.errors")
      public WebElement cityRequired;
  	@FindBy(id = "customer.address.state.errors")
      public WebElement stateRequired;
  	@FindBy(id = "customer.address.zipCode.errors")
      public WebElement zipCodeRequired;
  	@FindBy(id = "customer.ssn.errors")
      public WebElement ssnRequired;
  	@FindBy(id = "customer.username.errors")
      public WebElement usernameRequired;
  	@FindBy(id = "customer.password.errors")
      public WebElement passwordRequired;
  	@FindBy(id = "repeatedPassword.errors")
      public WebElement confirmPasswordRequired;
  	@FindBy(id = "repeatedPassword.errors")
  	  public WebElement confirmPasswordNoMatch;
  	//@FindBy(css = "h1[class='title']")
  	@FindBy(css = ".title")
    public WebElement welcomeUserTitle;
   

    public boolean homepageOpened() {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(seleniumDriver)
                    .withTimeout(Duration.ofSeconds(BaseTestClass.explicitWait))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginPanel")));
            wait.until(ExpectedConditions.visibilityOf(loginForm));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("captionone")));
            wait.until(ExpectedConditions.visibilityOf(atmServicesList));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("events")));
            wait.until(ExpectedConditions.visibilityOf(events));
            return true;

        } catch (TimeoutException e) {
            log.error("Homepage failed to open!");
            return false;
        }
    }
    
    public boolean registerPageOpened() {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(seleniumDriver)
                    .withTimeout(Duration.ofSeconds(BaseTestClass.explicitWait))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.firstName")));
            wait.until(ExpectedConditions.visibilityOf(firstName));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.lastName")));
            wait.until(ExpectedConditions.visibilityOf(lastName));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.address.street")));
            wait.until(ExpectedConditions.visibilityOf(address));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.address.city")));
            wait.until(ExpectedConditions.visibilityOf(city));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.address.state")));
            wait.until(ExpectedConditions.visibilityOf(state));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.address.zipCode")));
            wait.until(ExpectedConditions.visibilityOf(zipCode));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.phoneNumber")));
            wait.until(ExpectedConditions.visibilityOf(phoneNumber));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.ssn")));
            wait.until(ExpectedConditions.visibilityOf(ssn));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.username")));
            wait.until(ExpectedConditions.visibilityOf(username));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.password")));
            wait.until(ExpectedConditions.visibilityOf(password));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("repeatedPassword")));
            wait.until(ExpectedConditions.visibilityOf(confirmPassword));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Register']")));
            wait.until(ExpectedConditions.visibilityOf(registerBtn));
            return true;

        } catch (TimeoutException e) {
            log.error("Register page failed to open!");
            return false;
        }
    }


    public void homepageLoaded(){
        if(homepageOpened()){
            log.info("Homepage has loaded.");
        }
    }
    
    public void navigateToRegister(){
        if(registerLink.isEnabled()) {
        	registerLink.click();
        	if(registerPageOpened()) {
        		log.info("Register page has loaded.");
        	} else {
        		log.error("Register page failed to open!");
        	}
        }
    }
    
    public void sendInvalidInputToRequiredFields(String invalidInput){
    	firstName.clear();
    	firstName.sendKeys(invalidInput);
    	lastName.clear();
    	lastName.sendKeys(invalidInput);
    	address.clear();
    	address.sendKeys(invalidInput);
    	city.clear();
    	city.sendKeys(invalidInput);
    	state.clear();
    	state.sendKeys(invalidInput);
    	zipCode.clear();
    	zipCode.sendKeys(invalidInput);
    	phoneNumber.clear();
    	phoneNumber.sendKeys(invalidInput);
    	ssn.clear();
    	ssn.sendKeys(invalidInput);
    	username.clear();
    	username.sendKeys(invalidInput);
    	password.clear();
    	password.sendKeys(invalidInput);
    	confirmPassword.clear();
    	confirmPassword.sendKeys(invalidInput);
    	registerBtn.click();
    }
 
    public void tryRegisterWithoutPopulatingFields(){
    	firstName.clear();
    	lastName.clear();
    	address.clear();
    	city.clear();
    	state.clear();
    	zipCode.clear();
    	phoneNumber.clear();
    	ssn.clear();
    	username.clear();
    	password.clear();
    	confirmPassword.clear();
    	registerBtn.click();
    }
    
    public boolean errorMessagesForEmptyFieldsArePresent() {
    	try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(seleniumDriver)
                    .withTimeout(Duration.ofSeconds(BaseTestClass.explicitWait))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.firstName")));
            wait.until(ExpectedConditions.visibilityOf(firstNameRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.lastName")));
            wait.until(ExpectedConditions.visibilityOf(lastNameRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.address.street")));
            wait.until(ExpectedConditions.visibilityOf(addressRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.address.city")));
            wait.until(ExpectedConditions.visibilityOf(cityRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.address.state")));
            wait.until(ExpectedConditions.visibilityOf(stateRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.address.zipCode")));
            wait.until(ExpectedConditions.visibilityOf(zipCodeRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.ssn")));
            wait.until(ExpectedConditions.visibilityOf(ssnRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.username")));
            wait.until(ExpectedConditions.visibilityOf(usernameRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer.password")));
            wait.until(ExpectedConditions.visibilityOf(passwordRequired));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("repeatedPassword")));
            wait.until(ExpectedConditions.visibilityOf(confirmPasswordRequired));
            return true;

        } catch (TimeoutException e) {
            log.error("Error messages are missing!");
            return false;
        }
    }
    
    public void sendIncorrectConfirmationPassword(String randomCharaters){
    	firstName.clear();
    	firstName.sendKeys(randomCharaters);
    	lastName.clear();
    	lastName.sendKeys(randomCharaters);
    	address.clear();
    	address.sendKeys(randomCharaters);
    	city.clear();
    	city.sendKeys("AlabamaGA");
    	state.clear();
    	state.sendKeys("GA");
    	zipCode.clear();
    	zipCode.sendKeys("00001");
    	phoneNumber.clear();
    	phoneNumber.sendKeys("123456789");
    	ssn.clear();
    	ssn.sendKeys("123456789");
    	username.clear();
    	username.sendKeys(randomCharaters);
    	password.clear();
    	password.sendKeys(randomCharaters);
    	confirmPassword.clear();
    	confirmPassword.sendKeys(randomCharaters + "12");
    	registerBtn.click();
    }
    
    
    public void registerSuccessfully(String randomCharaters){
    	firstName.clear();
    	firstName.sendKeys(randomCharaters);
    	lastName.clear();
    	lastName.sendKeys(randomCharaters);
    	address.clear();
    	address.sendKeys(randomCharaters);
    	city.clear();
    	city.sendKeys("AlabamaGA");
    	state.clear();
    	state.sendKeys("GA");
    	zipCode.clear();
    	zipCode.sendKeys("00001");
    	phoneNumber.clear();
    	phoneNumber.sendKeys("123456789");
    	ssn.clear();
    	ssn.sendKeys(randomCharaters);
    	username.clear();
    	username.sendKeys(randomCharaters);
    	password.clear();
    	password.sendKeys(BaseTestClass.password);
    	confirmPassword.clear();
    	confirmPassword.sendKeys(BaseTestClass.password);
    	registerBtn.click();
    }
}
