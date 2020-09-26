package com.revature.selenium;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
	private static WebDriver driver;
	
	// in case we need to set env var
	private static final String base_url = System.getenv("base_url"); // = System.getenv("base_url");
	
	@BeforeAll
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
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSuccessfulLogin() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(base_url + "login");
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.name("login"));
		
		username.sendKeys("Hot");
		password.sendKeys("Wheels");
		loginBtn.click();
		
		assertEquals(base_url + "home", driver.getCurrentUrl());
	}
	
	@Test
	public void testFailedLoginWrongUsername() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(base_url + "login");
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.name("login"));
		
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(base_url + "login");
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginBtn = driver.findElement(By.name("login"));
		
		username.sendKeys("Hot");
		password.sendKeys("NotWheels");
		loginBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"loginDiv\"]")));
		
		String url = driver.getCurrentUrl();
		assertEquals(base_url + "login",url);
	}
}
