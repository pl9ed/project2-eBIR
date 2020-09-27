package com.revature.selenium;

import static org.junit.Assert.assertEquals;
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
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.TestUtilities;
import com.revature.DAO.UserDAO;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class LoginTest {
	private static WebDriver driver;
	private WebDriverWait wait;
	private static ChromeOptions options;
	
	private static UserDAO ud;
	private static User u = new User();
	
	// in case we need to set env var
	private static final String base_url = System.getenv("base_url"); // = System.getenv("base_url");
	
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
		ud.deleteUser(u);
		TestUtilities.clearDB();
		HibernateUtil.reconfigureSchema(System.getenv("project2_schema"));
	} 	
	
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 2);
		ud = new UserDAO();
		u.setUsername("LOGINTESTUSER");
		u.setPasswordPlain("Wheels");
		ud.saveUser(u);
		
		driver.get(base_url + "login");
		wait.until(driver -> driver.findElement(By.id("username")));
	}

	@After
	public void tearDown() throws Exception {
		TestUtilities.clearDB();
		HibernateUtil.closeSession();
		Thread.sleep(1500);
		driver.quit();
	}

	@Test
	public void testSuccessfulLogin() {
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.name("login"));
		
		username.sendKeys("LOGINTESTUSER");
		password.sendKeys("Wheels");
		loginBtn.click();
		
		wait.until(driver -> driver.findElement(By.id("logout_btn")));
		
		assertEquals(base_url + "home", driver.getCurrentUrl());
	}
	
	@Test
	public void testFailedLoginWrongUsername() {
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.name("login"));
		
		username.sendKeys("NotHot");
		password.sendKeys("Wheels");
		loginBtn.click();
		
		wait.until(ExpectedConditions.alertIsPresent());

		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException e) {
			fail("No alert");
		}
		
		String url = driver.getCurrentUrl();
		assertEquals(base_url + "login",url);
	}
	
	@Test
	public void testFailedLoginWrongPassword() {
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.name("login"));
		
		username.sendKeys("LOGINTESTUSER");
		password.sendKeys("NotWheels");
		loginBtn.click();
		
		wait.until(ExpectedConditions.alertIsPresent());

		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException e) {
			fail("No alert");
		}
		
		String url = driver.getCurrentUrl();
		assertEquals(base_url + "login",url);
	}
}
