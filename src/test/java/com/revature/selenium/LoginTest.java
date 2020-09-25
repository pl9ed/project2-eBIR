package com.revature.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.pages.LoginPage;

class LoginTest {

	private LoginPage page;
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
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
	void testLoginHeader() {
		assertEquals(this.page.getHeader(), "Login");
	}
	
	@Test
	void testSuccessfulLogin() {
		this.page.setUsername("jandrew");
		this.page.setPassword("qwerty");
		this.page.submit();
		assertEquals("http://localhost:4200/eBIRProject#/home", driver.getCurrentUrl());
	}
	
	@Test
	void testFailedLogin() {
		this.page.setUsername("jandrew");
		this.page.setPassword("notqwerty");
		this.page.submit();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		if(wait.until(ExpectedConditions.alertIsPresent()) == null) {
			
		}
	}

}
