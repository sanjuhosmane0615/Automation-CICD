package sanjuhosmane.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sanjuhosmane.TestComponents.BaseTest;
import sanjuhosmane.pageData.ConfirmationPage;
import sanjuhosmane.pageData.LandingPage;
import sanjuhosmane.pageData.MyCartPage;
import sanjuhosmane.pageData.PaymentPage;
import sanjuhosmane.pageData.ProductCatalogue;

public class Stepdefinitionimpl extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	
	
	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingpage=launchApplication();
	}
	
	@Given ("^Logging in with username(.+) and password(.+)$")
	public void loggin_in_userName_and_Password(String userName,String password) {
		productcatalogue = landingpage.loginPage(userName, password);
	}
	
	@When("^Add product (.+) to the cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> ProductElemnts = productcatalogue.getMeTheProductList();
		productcatalogue.addToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void check_submit_order(String productName) {
		MyCartPage mycart = productcatalogue.navigateToMyCartPage();
		Boolean match = mycart.verifytProductInMyCart(productName);
		Assert.assertTrue(match);
		PaymentPage paymentpage = mycart.navigateToPaymentPage();
		paymentpage.payment("india");
	    confirmationpage = paymentpage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationpage.confirmOrder();
		Assert.assertEquals(confirmMessage, string);
	}
	
	@Then("{string}message is displayed")
	public void message_is_Displayed(String string) {
		Assert.assertEquals(string, landingpage.getErrorMessage());
	}
	
}

