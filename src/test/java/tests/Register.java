package tests;

import core.BasePageObject;
import core.BaseTestClass;
import core.Driver;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class Register extends BaseTestClass {
	
	private RegisterPage pageObject;

	@Test
    public void verifyHomepageIsLoaded(){
    	pageObject = new RegisterPage();
        //call a page method
        pageObject.homepageLoaded();
        //assert something
        Assert.assertTrue(pageObject.onlineServicesList.isDisplayed());
    }
    
   
    
    @Test
    public void navigateToRegister(){
    	pageObject = new RegisterPage();
    	pageObject.navigateToRegister();
    	Assert.assertTrue(Driver.seleniumDriver.getCurrentUrl().contains("https://parabank.parasoft.com/parabank/register.htm"));
    }
    
    
    @Test
    public void verifyRequiredFieldsErrorMessages(){
    	pageObject = new RegisterPage();
    	pageObject.navigateToRegister();
    	pageObject.tryRegisterWithoutPopulatingFields();
    	Assert.assertTrue(pageObject.errorMessagesForEmptyFieldsArePresent());
    }
    
    @Test
    public void passwordConfirmationNotMatching(){
    	pageObject = new RegisterPage();
    	pageObject.navigateToRegister();
    	BasePageObject newBPObject1 = new BasePageObject(Driver.seleniumDriver);
    	String username = newBPObject1.generateUniqueString();
    	pageObject.sendIncorrectConfirmationPassword(username);
    	Assert.assertTrue(pageObject.confirmPasswordNoMatch.getText().equalsIgnoreCase("Passwords did not match."));
    }
    
    @Test
    public void verifyErrorsForSpacesInRequiredFields(){
    	pageObject = new RegisterPage();
    	pageObject.navigateToRegister();
    	pageObject.sendInvalidInputToRequiredFields("   ");
    	Assert.assertTrue(pageObject.errorMessagesForEmptyFieldsArePresent());
    }

    @Test
    public void registerUser(){
    	pageObject = new RegisterPage();
    	pageObject.navigateToRegister();
    	BasePageObject newBPObject2 = new BasePageObject(Driver.seleniumDriver);
    	String username = newBPObject2.generateUniqueString();
    	pageObject.registerSuccessfully(username);
    	Assert.assertTrue(pageObject.welcomeUserTitle.getText().equalsIgnoreCase("Welcome "+username));
    }
  
}
