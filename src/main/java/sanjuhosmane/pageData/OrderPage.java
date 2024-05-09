package sanjuhosmane.pageData;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sanjuhosmane.AbstractComponents.AbstractComponent;


public class OrderPage extends AbstractComponent{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutButton;
	
	@FindBy(css="tr td:nth-child(3)") 
	List<WebElement> productNames;
	

	
	public boolean verifytOrderDisplay(String productName) {
		
		
		boolean match = productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	

	

}
