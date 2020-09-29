package PageObjectModel.Dreft;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver = null;
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(xpath = "//a[@title='REGISTER'] | (//a[@title='REGISTRARSE'])[1]")
	private static WebElement ClickRegisterButton;
	
	@FindBy(xpath = "//h1[text()='Create Your Dreft profile'] | //h1[text()='Crear su cuenta Dreft ']")
	private static WebElement CreateProfile;
	
	public void ClickRegisterButton(String siteRegion) {
		
		ClickRegisterButton.isDisplayed();
		ClickRegisterButton.click();
		if(!CreateProfile.isDisplayed())
			Assert.fail("Create Profile Page is Not Opened");
	}
	
}
