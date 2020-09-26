package com.revature.selenium;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
	private static WebDriver driver;
	
	// in case we need to set env var
	private static final String base_url = "http://52.205.93.132:8006/eBIRProject"; // = System.getenv("base_url");
	
	private LoginPage page;

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
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.page = new LoginPage(driver);
		this.page.navigateTo();
	}

	@After
	public void tearDown() throws Exception {
		this.page = null;
	}
	
	@Test
	public void testDriver() {
		driver.get(base_url);
	}
	
	/*
	@Test
	public void testSuccessfulLogin() {
		this.page.setUsername("jandrew");
		this.page.setPassword("qwerty");
		this.page.submit();
		assertEquals("http://localhost:4200/eBIRProject#/home", driver.getCurrentUrl());
	}
	
	@Test
	public void testFailedLogin() {
		this.page.setUsername("jandrew");
		this.page.setPassword("notqwerty");
		this.page.submit();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		if(wait.until(ExpectedConditions.alertIsPresent()) == null) {
			
		}
	} */
}
