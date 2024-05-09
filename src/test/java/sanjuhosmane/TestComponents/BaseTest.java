package sanjuhosmane.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import sanjuhosmane.pageData.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\sanjuhosmane\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		
		
		if(browserName.contains("chrome")) {
			ChromeOptions option =new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
		option.addArguments("headless");
		}
		driver = new ChromeDriver(option);
		driver.manage().window().setSize(new Dimension(1440,900));	
		
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.edge.driver", "C:\\Users\\Hp\\Documents\\Browserdriver\\msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1440,900));		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
	    landingpage =new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(){
		 driver.close();
	}
	
	public List<HashMap<String,String>> getJsonToHashMap(String filePath) throws IOException {
		//reading and converting Json to String
		String JsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//convert String to hashmap  jacksonDatabind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
	}
	
	public String getScreenShot(String testCaseNAme,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File path=new File(System.getProperty("user.dir")+"//reports//"+testCaseNAme+".png");
		FileUtils.copyFile(source, path);
		return System.getProperty("user.dir")+"//reports//"+testCaseNAme+".png";
	}
}
