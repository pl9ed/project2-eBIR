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
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.DAO.UserDAO;
import com.revature.models.User;
import com.revature.selenium.pages.LoginPage;

public class LoginE2ETest {
	private ChromeDriver driver;
	private static ChromeOptions options;
	private static WebDriverWait wait;

	private static UserDAO ud = new UserDAO();
	private static User u = new User();

	private static final String user = "LOGIN_TEST";
	private static final String pass = "pass123";
	private static final String base_url = System.getenv("base_url");

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
		options = new ChromeOptions();
		options.addArguments("headless", "disable-gpu", "disable-extensions");

		u.setUsername(user);
		u.setPasswordPlain(pass);
		if (!ud.saveUser(u)) {
			System.out.print("FAILED TO SAVE TEST USER");
		}

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ud.deleteUser(u);

	}

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		this.page = new LoginPage(driver);
	}

	@After
	public void tearDown() throws Exception {
		this.page = null;
		driver.quit();
	}

	@Test
	public void testSuccessfulLogin() throws InterruptedException {
		this.page.getUsername().sendKeys(user);
		this.page.getPassword().sendKeys(pass);
		this.page.getLoginBtn().click();
		
		Thread.sleep(1000);
		
		wait.until(driver -> driver.findElement(By.id("searchBtn")));

		assertEquals(base_url + "home", driver.getCurrentUrl());

	}

	@Test
	public void testSuccessfulLoginEnter() throws InterruptedException {
		this.page.getUsername().sendKeys(user);
		this.page.getPassword().sendKeys(pass);
		this.page.getPassword().sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		
		wait.until(driver -> driver.findElement(By.id("searchBtn")));

		assertEquals(base_url + "home", driver.getCurrentUrl());

	}

	@Test
	public void testFailedLogin() {
		this.page.getUsername().sendKeys(user);
		this.page.getPassword().sendKeys("not the password");
		this.page.getPassword().sendKeys(Keys.ENTER);

		wait.until(ExpectedConditions.alertIsPresent());
		assertEquals("Problem logging into account!", driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
	}

	@Test
	public void testRegBtn() {
		this.page.getRegBtn().click();

		wait.until(driver -> driver.findElement(By.id("register")));
		assertEquals(base_url + "register", driver.getCurrentUrl());
	}

}
