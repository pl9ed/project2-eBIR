package com.revature.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.TestUtilities;
import com.revature.util.HibernateUtil;

public class RegistrationTest {
	
	private static final String base_url = System.getenv("base_url"); // Structure example: http://localhost:4200/eBIRProject#/
	private static WebDriver driver;
	private WebDriverWait wait;
	private static ChromeOptions options;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HibernateUtil.reconfigureSchema("public");
		TestUtilities.clearDB();
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

		options = new ChromeOptions();
		options.addArguments("headless", "disable-gpu", "disable-extensions");

	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		TestUtilities.clearDB();
		HibernateUtil.reconfigureSchema(System.getenv("project2_schema"));
	}
	
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 2);
		driver.get(base_url + "login");
		wait.until(driver -> driver.findElement(By.name("toRegister")));
	}

	@After
	public void tearDown() throws Exception {
		TestUtilities.clearDB();
		HibernateUtil.closeSession();
		Thread.sleep(1000);
		driver.close();
		driver = null;
	}

	@Test

	public void registerTestPass() {
		WebElement toRegisterBtn = driver.findElement(By.name("toRegister"));
		wait.until(ExpectedConditions.elementToBeClickable(toRegisterBtn));
		
		toRegisterBtn.click();
		
		wait.until(driver -> driver.findElement(By.id("username")));
		
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
		
		registerBtn.click(); 
		
		wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("logout_btn"))));
		
		String url = driver.getCurrentUrl();
		assertEquals(base_url + "home",url);
	}
	
	@Test
	public void registerTestNoUsername() {
		WebElement toRegisterBtn = driver.findElement(By.name("toRegister"));
		
		wait.until(ExpectedConditions.elementToBeClickable(toRegisterBtn));
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
				
		registerBtn.click(); 
		
		wait.until(ExpectedConditions.alertIsPresent());
		
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException e) {
			fail("No alert");
		}
		
		String url = driver.getCurrentUrl();
		//if register fails, user will still be in the register page
		assertEquals(base_url + "register", url);
	}

}
