package sanjuhosmane.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sanjuhosmane.pageData.MyCartPage;
import sanjuhosmane.pageData.OrderPage;

public class AbstractComponent {
 WebDriver driver;
 
 public AbstractComponent(WebDriver driver) {
	 this.driver=driver;
	 PageFactory.initElements(driver, this);
 }
 
 @FindBy(css="button[routerlink*='cart']")
	WebElement myCartButton;
 
 @FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
 
	public MyCartPage navigateToMyCartPage() {
		myCartButton.click();
		return new MyCartPage(driver);
	}
	
	public OrderPage navigateOrdersPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}
	
	public void waitElementsToAppear(By locator) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void waitWebElementsToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
		}
	
	public void waitElementsToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
