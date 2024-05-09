package sanjuhosmane.pageData;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sanjuhosmane.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryInput;
	
	@FindBy(css="button[type=button]")
	List<WebElement> countryOtions;
	
	@FindBy(css="a[class*='action']")
	WebElement placeOrderButton;
	
	By reults=By.cssSelector(".ta-results");
	
	public void payment(String country) {
		
		countryInput.sendKeys(country);
		
		waitElementsToAppear(reults);
		countryOtions.stream().filter(s -> s.findElement(By.tagName("span")).getText().equalsIgnoreCase("India")).findFirst()
				.orElse(null).click();
		}
	
	public ConfirmationPage submitOrder() {
		placeOrderButton .click();
		return new ConfirmationPage(driver);
	}
}
