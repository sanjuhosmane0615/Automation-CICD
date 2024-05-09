package sanjuhosmane.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import sanjuhosmane.TestComponents.BaseTest;
import sanjuhosmane.pageData.ConfirmationPage;
import sanjuhosmane.pageData.LandingPage;
import sanjuhosmane.pageData.MyCartPage;
import sanjuhosmane.pageData.PaymentPage;
import sanjuhosmane.pageData.ProductCatalogue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=sanjuhosmane.TestComponents.Retry.class)
	
	public void loginErrorValidation() throws InterruptedException, IOException {
		landingpage.loginPage("ranjuhosmane60135@gmail.com","Sanju@0615");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
 
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {

		String productName = "IPHONE 13 PRO";
		String country ="India";
		
		

		ProductCatalogue productcatalogue=landingpage.loginPage("sanjuhosmane60135@gmail.com","Sanju@0615");
		
		List<WebElement> ProductElemnts= productcatalogue.getMeTheProductList();
		productcatalogue.addToCart(productName);
		MyCartPage mycart=productcatalogue.navigateToMyCartPage();
		Boolean match=mycart.verifytProductInMyCart("Zara coat 33");
		Assert.assertFalse(match);
		
		
        
	}

}
