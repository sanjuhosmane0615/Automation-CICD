package sanjuhosmane.pageData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sanjuhosmane.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
    public LandingPage(WebDriver driver) {
    super(driver);	
	this.driver=driver;
	PageFactory.initElements(driver, this);
     }
	
	@FindBy(id="userEmail")
	WebElement userMail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public String getErrorMessage() {
		waitWebElementsToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public ProductCatalogue loginPage(String Name,String password) {
		userMail.sendKeys(Name);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
