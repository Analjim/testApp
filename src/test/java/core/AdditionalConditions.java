package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class AdditionalConditions {

	 /**
     * This let you figure out whether a website using jQuery has finished making AJAX calls.
     * Usage: wait.until(AdditionalConditions.jQueryAJAXCallsHaveCompleted()));
     */
    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver seleniumDriver) {
                return (Boolean) ((JavascriptExecutor) seleniumDriver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
    }
}
