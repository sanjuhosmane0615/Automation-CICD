package sanjuhosmane.pageData;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sanjuhosmane.AbstractComponents.AbstractComponent;


public class MyCartPage extends AbstractComponent{
	WebDriver driver;
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutButton;
	
	@FindBy(css="div[class='cart']") 
	List<WebElement> myCartProducts;
	

	
	public boolean verifytProductInMyCart(String productName) {
		
		
		boolean match = myCartProducts.stream().map(s -> s.findElement(By.tagName("h3")).getText())
				.anyMatch(s -> s.equals(productName));
		return match;
		
	}
	
	public PaymentPage navigateToPaymentPage() {
		checkOutButton.click();
		return new PaymentPage(driver);
	}
	

}
