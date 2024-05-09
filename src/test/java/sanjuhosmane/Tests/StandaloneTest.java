package sanjuhosmane.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;

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

public class StandaloneTest {

	public static void main(String[] args) {

		String productName = "IPHONE 13 PRO";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("sanjuhosmane60135@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sanju@0615");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class*=mb-3]")));
		List<WebElement> ProductElemnts = driver.findElements(By.cssSelector("div[class*=mb-3]"));
		WebElement selected = ProductElemnts.stream()
				.filter(s -> s.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		selected.findElement(By.cssSelector("div[class*=mb-3] div div button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();
		List<WebElement> myCartProducts = driver.findElements(By.cssSelector("div[class='cart']"));
		Boolean match = myCartProducts.stream().map(s -> s.findElement(By.tagName("h3")).getText())
				.anyMatch(s -> s.equals(productName));
		Assert.assertTrue(match);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> options = driver.findElements(By.cssSelector("button[type=button]"));
		options.stream().filter(s -> s.findElement(By.tagName("span")).getText().equalsIgnoreCase("India")).findFirst()
				.orElse(null).click();
        driver.findElement(By.cssSelector("a[class*='action']")).click();
        String confirmMessage=driver.findElement(By.tagName("h1")).getText();
        System.out.println(confirmMessage);
        
	}

}
