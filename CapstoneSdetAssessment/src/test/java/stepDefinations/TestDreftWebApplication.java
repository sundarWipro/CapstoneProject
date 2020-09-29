package stepDefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import PageObjectModel.Dreft.AccountPage;
import PageObjectModel.Dreft.HomePage;
import ReusableMethods.Utilities;

@RunWith(Cucumber.class)
public class TestDreftWebApplication extends Utilities{
	
	WebDriver driver = null;
	String siteRegion = null, strEmailId = null;

    @Given("^Initialize the Webdriver$")
    public void initialize_the_webdriver(){
    	driver = setBrowserDriver();
    	//Implicit Wait
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^launch the Application URL (.+) and (.+)$")
    public void launch_the_application_url_and(String siteUrl, String siteRegion) {
    	launchApplication(driver, siteUrl);
    	this.siteRegion = siteRegion;
    }

    @Then("^Click Register Button in HomePage$")
    public void click_register_button_in_homepage(){
    	new HomePage(driver).ClickRegisterButton(siteRegion);
    }

    @Then("^Enter the Registration details$")
    public void enter_the_registration_details(){
    	strEmailId = getEmailId();
    	//Canada French Application is Not Showing Registration and Login So Skipping the Automation development for Canada French Site
    	new AccountPage(driver).enterAccountDetails(siteRegion, strEmailId);
    }

    @And("^Login To Account$")
    public void login_to_account(){
    	System.out.println(strEmailId);
    	new AccountPage(driver).loginToAccount(strEmailId);
    }
    
    @And("^LogOut of the Account$")
    public void logout_of_the_account() {
    	new AccountPage(driver).logOutAccount();
    }
    
    @Then("^Verify Password Reset Function$")
    public void verify_password_reset_function(){
    	new AccountPage(driver).verifyForgotPassword(siteRegion, strEmailId);
    }

    @And("^Quite The Webdriver$")
    public void quite_the_webdriver() {
    	closeBrowser(driver);
    }
    
}