package PageObjectModel.Dreft;

import static ReusableMethods.Utilities.TestData;
import static ReusableMethods.Utilities.jsonObject;

import org.apache.commons.exec.ExecuteException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ReusableMethods.Utilities;

public class AccountPage {
	
	WebDriver driver = null;
	public AccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_grs_consumer[firstname]']")
	private static WebElement FirstName;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_grs_account[emails][0][address]']")
	private static WebElement EnterEmail;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_grs_consumer[lastname]']")
	private static WebElement LastName;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_grs_account[password][password]']")
	private static WebElement EnterPassword;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_grs_account[password][confirm]']")
	private static WebElement ConfirmEnterPassword;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_grs_account[addresses][0][postalarea]']")
	private static WebElement EnterPostalCode;
	
	@FindBy(xpath = "//input[@type='checkbox' and @id='phdesktopbody_0_ctl46']")
	private static WebElement ClickNoThanksYes;

	@FindBy(xpath = "//select[@id='phdesktopbody_0_grs_consumer[birthdate][month]']")
	private static WebElement SelectMonth;
	
	@FindBy(xpath = "//select[@id='phdesktopbody_0_grs_consumer[birthdate][year]']")
	private static WebElement SelectYear;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_submit']")
	private static WebElement CreateProfile;
	
	@FindBy(xpath = "//h1[@id='phdesktopbody_0_Header' and contains(text(),'Congratulations')] | //h1[@id='phdesktopbody_0_Header' and contains(text(),'Enhorabuena')]")
	private static WebElement VerifyRegistrationSuccess;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_username']")
	private static WebElement EnterSignInEmail;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_password']")
	private static WebElement EnterSignInPassword;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_submit']")
	private static WebElement ClickSignIn;
	
	@FindBy(xpath = "//h1[@id='phdesktopbody_0_TitleText' and text()='Your Profile'] | //h1[@id='phdesktopbody_0_TitleText' and text()='Tu perfil']")
	private static WebElement LoginSuccess;
	
	@FindBy(xpath = "//a[@id='phdesktopheader_0_phdesktopheadertop_2_LogOffLink']")
	private static WebElement ClickLogOutButton;
	
	@FindBy(xpath = "//a[@id='phdesktopheader_0_phdesktopheadertop_2_anchrContinue']")
	private static WebElement ConfirmLogOut;
	
	@FindBy(xpath = "//a[@id='phdesktopbody_0_forgotpassword']")
	private static WebElement ClickForgotPassword;
	
	@FindBy(xpath = "//h1[text()='Reset my password'] | //h1[text()='Restablece tu contraseña']")
	private static WebElement VerifyForgotPasswordPage;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_username']")
	private static WebElement EnterResetEmailId;
	
	@FindBy(xpath = "//input[@id='phdesktopbody_0_Create Your New Password'] | //input[@id='phdesktopbody_0_RESTABLECE TU CONTRASEÑA']")
	private static WebElement CreateNewPassword;
	
	@FindBy(xpath = "//h2[text()='You will receive an e-mail very shortly containing a link to reset your password.'] | //h2[text()='En breve recibirás un email para cambiar la contraseña.']")
	private static WebElement ResetPasswordMailSent;
	
	public void enterAccountDetails(String siteRegion, String strEmail) {
			Select select = null;
			FirstName.sendKeys(TestData.get("FirstName"));
			LastName.sendKeys(TestData.get("LastName"));
			EnterEmail.sendKeys(strEmail);
			EnterPassword.sendKeys(TestData.get("Password"));
			ConfirmEnterPassword.sendKeys(TestData.get("Password"));
			if(siteRegion.equalsIgnoreCase("USEnglish"))
				EnterPostalCode.sendKeys(TestData.get("ZipCodeUS"));
			else if(siteRegion.equalsIgnoreCase("USSpanish"))
				EnterPostalCode.sendKeys(TestData.get("ZipCodeSpanish"));
			
			//Select Birth Date
			select = new Select(SelectMonth);
			select.selectByValue(TestData.get("BirthMonth"));
			select = new Select(SelectYear);
			select.selectByValue(TestData.get("BirthYear"));
			
			ClickNoThanksYes.click();
			CreateProfile.isDisplayed();
			CreateProfile.click();
			Utilities.sleepMedium();
			try {
				VerifyRegistrationSuccess.isDisplayed();
			}catch(Exception e) {
				Assert.fail("Account is Not Created Successfully");
			}
	}
	
	public void loginToAccount(String strEmail) {
		
		Utilities.sleepMedium();
		EnterSignInEmail.sendKeys(strEmail);
		EnterSignInPassword.sendKeys(TestData.get("Password"));
		ClickSignIn.click();
		try {
			LoginSuccess.isDisplayed();
		}catch(Exception e) {
			Assert.fail("Login to Account is Not Successful");
		}
	}
	
	public void logOutAccount() {
		Utilities.sleepMedium();
		ClickLogOutButton.isDisplayed();
		ClickLogOutButton.click();
		Utilities.sleepMedium();
		ConfirmLogOut.isDisplayed();
		ConfirmLogOut.click();
	}

	public void verifyForgotPassword(String siteRegion, String strEmail) {
		ClickForgotPassword.isDisplayed();
		ClickForgotPassword.click();
		Utilities.sleepMedium();
		VerifyForgotPasswordPage.isDisplayed();
		EnterResetEmailId.sendKeys(strEmail);
		CreateNewPassword.click();
		Utilities.sleepMedium();
		try {
			ResetPasswordMailSent.isDisplayed();
		}catch(Exception e) {
			Assert.fail("Password Reset Mail Sent Confirmation Message is Not Displayed");
		}
	}

}
