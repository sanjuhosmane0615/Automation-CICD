package sanjuhosmane.pageData;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sanjuhosmane.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> ProductElemnts = driver.findElements(By.cssSelector("div[class*=mb-3]"));
	
	@FindBy(css="div[class*=mb-3]")
	List<WebElement> ProductElements;
	
	//driver.findElement(By.className("ng-animating"))
	@FindBy(className="ng-animating")
	WebElement spinner;
	
	By productPageLocator=By.cssSelector("div[class*=mb-3]");
	By cartButtonLocator=By.cssSelector("div[class*=mb-3] div div button:last-of-type");
	By toastMessageLocator=By.id("toast-container");
	
	public List<WebElement> getMeTheProductList() {
		waitElementsToAppear(productPageLocator);
		return ProductElements;
	}
	
	public WebElement getProductByCart(String productName) {
		WebElement selectedProd =  getMeTheProductList().stream()
				.filter(s -> s.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return selectedProd;
		}
	
	public void addToCart(String productName) throws InterruptedException {
		getProductByCart(productName).findElement(cartButtonLocator).click();
		waitElementsToAppear(toastMessageLocator);
		waitElementsToDisappear(spinner);
		
		
	}
}
