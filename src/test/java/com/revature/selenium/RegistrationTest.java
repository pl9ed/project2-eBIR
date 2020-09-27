package com.revature.selenium;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.TestUtilities;

class RegistrationTest {
	
	private static WebDriver driver;
	private static final String base_url = System.getenv("base_url");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String os = System.getProperty("os.name").toLowerCase();
		
		// use windows
		if (os.contains("win")) {
			File f = new File("src/test/resources/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
			System.out.println("Using Windows Driver");
			
		// use linux
		} else {
			File f = new File("src/test/resources/chromedriver");
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		}
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless", "--disable-gpu", "--disable-extensions");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		TestUtilities.clearDB();
	}

	@Test
	void registerTestPass() {
		System.out.println("registerTestPass");
		driver.get("http://localhost:4200/eBIRProject/#/login");
		WebElement toRegisterBtn = driver.findElement(By.name("toRegister"));
		toRegisterBtn.click();
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement passwordConfirm = driver.findElement(By.id("confirmpassword"));
		WebElement firstname = driver.findElement(By.id("firstname"));
		WebElement lastname = driver.findElement(By.id("lastname"));
		WebElement email = driver.findElement(By.id("email"));
		
		username.sendKeys("Hot");
		password.sendKeys("Wheels");
		passwordConfirm.sendKeys("Wheels");
		firstname.sendKeys("Mario");
		lastname.sendKeys("Mario");
		email.sendKeys("nintendo@gmail.com");
		
		WebElement registerBtn = driver.findElement(By.id("register"));
		
		boolean test = registerBtn.isEnabled();
		assertEquals(test,true);
		registerBtn.click(); 
				
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"homeDiv\"]")));
		
		String url = driver.getCurrentUrl();
		assertEquals(base_url + "home",url);
	}
	
	@Test
	void registerTestNoUsername() {
		System.out.println("registerTestNoUsername");
		//fail if user does not include username
		driver.get("http://localhost:4200/eBIRProject/#/login");
		WebElement toRegisterBtn = driver.findElement(By.name("toRegister"));
		toRegisterBtn.click();
		
		WebElement password = driver.findElement(By.id("password"));
		WebElement passwordConfirm = driver.findElement(By.id("confirmpassword"));
		WebElement firstname = driver.findElement(By.id("firstname"));
		WebElement lastname = driver.findElement(By.id("lastname"));
		WebElement email = driver.findElement(By.id("email"));
		
		password.sendKeys("Wheels");
		passwordConfirm.sendKeys("Wheels");
		firstname.sendKeys("Mario");
		lastname.sendKeys("Mario");
		email.sendKeys("nintendo@gmail.com");
		
		WebElement registerBtn = driver.findElement(By.id("register"));
		
		boolean test = registerBtn.isEnabled();
		assertEquals(test,true);
		registerBtn.click(); 
		
		String url = driver.getCurrentUrl();
		//if register fails, user will still be in the register page
		assertEquals("http://localhost:4200/eBIRProject#/register", url);
	}

}
