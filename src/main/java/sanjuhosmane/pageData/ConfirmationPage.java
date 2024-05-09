package sanjuhosmane.pageData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sanjuhosmane.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//driver.findElement(By.tagName("h1")).getText();
	@FindBy(tagName="h1")
	WebElement confirmMessageElement;
	
	public String confirmOrder() {
		String confirmMessage=confirmMessageElement.getText();
		return confirmMessage;
	}

}
