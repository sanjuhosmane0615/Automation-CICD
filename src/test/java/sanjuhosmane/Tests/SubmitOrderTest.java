package sanjuhosmane.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import sanjuhosmane.TestComponents.BaseTest;
import sanjuhosmane.pageData.ConfirmationPage;
import sanjuhosmane.pageData.LandingPage;
import sanjuhosmane.pageData.MyCartPage;
import sanjuhosmane.pageData.OrderPage;
import sanjuhosmane.pageData.PaymentPage;
import sanjuhosmane.pageData.ProductCatalogue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {

	String productName = "IPHONE 13 PRO";
	String country = "India";

	@Test(dataProvider="getData",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {

		ProductCatalogue productcatalogue = landingpage.loginPage(input.get("email"), input.get("password"));

		List<WebElement> ProductElemnts = productcatalogue.getMeTheProductList();
		productcatalogue.addToCart(input.get("product"));
		MyCartPage mycart = productcatalogue.navigateToMyCartPage();
		Boolean match = mycart.verifytProductInMyCart(input.get("product"));
		Assert.assertTrue(match);
		PaymentPage paymentpage = mycart.navigateToPaymentPage();
		paymentpage.payment(country);
		ConfirmationPage confirmationpage = paymentpage.submitOrder();
		String confirmMessage = confirmationpage.confirmOrder();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		System.out.println(confirmMessage);

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productcatalogue = landingpage.loginPage("sanjuhosmane60135@gmail.com", "Sanju@0615");
		OrderPage orderpage = productcatalogue.navigateOrdersPage();
		Assert.assertTrue(orderpage.verifytOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String,String>> data=getJsonToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\sanjuhosmane\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
}

/*
@DataProvider
public Object[][] getData() {
	HashMap<String,String> map=new HashMap<String,String>(); 
	map.put("email", "sanjuhosmane60135@gmail.com");
	map.put("password", "Sanju@0615");
	map.put("product", "IPHONE 13 PRO");
	
	HashMap<String,String> map1=new HashMap<String,String>();
	map1.put("email", "sanjuhosmane60135@gmail.com");
	map1.put("password", "Sanju@0615");
	map1.put("product", "ZARA COAT 3");
	
	return new Object[][] { {map},{map1} };
}
*/

/*
@DataProvider
	
	return new Object[][] { { "sanjuhosmane60135@gmail.com", "Sanju@0615", "IPHONE 13 PRO" },
			{ "sanjuhosmane60135@gmail.com", "Sanju@0615", "ZARA COAT 3" } };
}
*/
