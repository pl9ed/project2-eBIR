package com.revature.selenium;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.TestUtilities;
import com.revature.DAO.UserDAO;
import com.revature.models.User;

public class LoginTest {
	private static WebDriver driver;
	
	// in case we need to set env var
	private static final String base_url = System.getenv("base_url"); // = System.getenv("base_url");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
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
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--disable-extensions");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		System.out.println("Before Class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After Class");
	} 	
	
	@Before
	public void setUp() throws Exception {
		UserDAO ud = new UserDAO();
		User u = new User();
		u.setUsername("Hot");
		u.setPasswordPlain("Wheels");
		ud.saveUser(u);
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
		TestUtilities.clearDB();
		
		try {
			driver.switchTo().alert().accept();

		}catch(Exception e) {
			
		}finally {
			driver.get(base_url + "login");
			WebElement usr = driver.findElement(By.id("username"));
			WebElement pass = driver.findElement(By.id("password"));
			usr.clear();
			usr.sendKeys("");
			pass.clear();
			pass.sendKeys("");
		}
		
	}

	@Test
	public void testSuccessfulLogin() {
		//driver.switchTo().alert().accept();

		driver.get(base_url + "login");
		WebElement username = driver.findElement(By.id("username"));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement password = driver.findElement(By.id("password"));
		
		WebElement loginBtn = driver.findElement(By.name("login"));
		
		username.sendKeys("Hot");
		password.sendKeys("Wheels");
		loginBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"homeDiv\"]")));
		
		assertEquals(base_url + "home", driver.getCurrentUrl());
	}
	
	@Test
	public void testFailedLoginWrongUsername() {
		driver.get(base_url + "login");
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.name("login"));
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		username.sendKeys("NotHot");
		password.sendKeys("Wheels");
		loginBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"loginDiv\"]")));
		
		String url = driver.getCurrentUrl();
		assertEquals(base_url + "login",url);
	}
	
	@Test
	public void testFailedLoginWrongPassword() {
		driver.get(base_url + "login");
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.name("login"));
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		username.sendKeys("Hot");
		password.sendKeys("NotWheels");
		loginBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"loginDiv\"]")));
		
		String url = driver.getCurrentUrl();
		assertEquals(base_url + "login",url);
	}
}
