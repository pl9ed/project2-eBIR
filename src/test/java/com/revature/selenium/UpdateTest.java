package com.revature.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
import com.revature.util.HibernateUtil;

public class UpdateTest {

	private static WebDriver driver;
	private static String newFirstName;
	private static String newLastName;
	private static String newPassword;
	private static String newEmail;
	
	private static final String base_url = System.getenv("base_url"); // Structure example: http://localhost:4200/eBIRProject#/

	@BeforeClass
	public static void beforeClass() {
		HibernateUtil.reconfigureSchema("public");
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
		options.addArguments("headless", "disable-gpu", "disable-extensions"); 
		driver = new ChromeDriver(options);
		
		System.out.println(base_url + "login");
		//log in as Hot: Wheels
		driver.get(base_url + "login");
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.sendKeys("Hot");
		password.sendKeys("Wheels");
		WebDriverWait wait = new WebDriverWait(driver, 2);

		WebElement loginBtn = driver.findElement(By.name("login"));
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));

		loginBtn.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		WebElement profileBtn = driver.findElement(By.id("ProfileBtn"));
		wait.until(ExpectedConditions.elementToBeClickable(profileBtn));

		profileBtn.click();
		
		WebElement updateBtn = driver.findElement(By.name("update"));
		wait.until(ExpectedConditions.elementToBeClickable(updateBtn));

		updateBtn.click();
		
		newFirstName = "newFirstName";
		newLastName = "newLastName";
		newPassword = "Wheels";
		newEmail = "newEmail@gmail.com";
	}
	
	@AfterClass
	public static void afterClass() {
		TestUtilities.clearDB();
		HibernateUtil.reconfigureSchema(System.getenv("project2_schema"));
		driver.quit();
	}
	
	@Before
	public void before() {
		UserDAO ud = new UserDAO();
		User u = new User();
		u.setUsername("Hot");
		u.setPasswordPlain("Wheels");
		u.setFirstName("Mario");
		u.setLastName("Mario");
		u.setEmail("Kk@email.com");
		System.out.println(u);
		ud.saveUser(u);
	}
	
	@After
	public void after() {
		TestUtilities.clearDB();
	}
	
	@Ignore
	void updateFirstName(String value) {
		WebElement firstname = driver.findElement(By.id("uFirstname"));
		firstname.clear();
		firstname.sendKeys(value);
	}
	
	@Ignore
	void updateLastName(String value) {
		WebElement lastname = driver.findElement(By.id("uLastname"));
		lastname.clear();
		lastname.sendKeys(value);
	}
	
	@Ignore
	void updatePassword(String value) {
		WebElement password = driver.findElement(By.id("uPassword"));
		password.clear();
		password.sendKeys(value);
	}
	
	@Ignore
	void updateEmail(String value) {
		WebElement email = driver.findElement(By.id("uEmail"));
		email.clear();
		email.sendKeys(value);
	}
	
	@Test
	public void updateAllFields() {
		updateEmail(newEmail);
		updateFirstName(newFirstName);
		updateLastName(newLastName);
		updatePassword(newPassword);
		
		WebElement updateBtn = driver.findElement(By.id("updatBtn"));
		updateBtn.click(); 

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
		
		String fullname = driver.findElement(By.id("fullname")).getText();
		String email = driver.findElement(By.id("email")).getText();
		
		assertEquals(newFirstName+" "+newLastName, fullname);
		assertEquals(newEmail,email);
	}
	
	@Test
	public void updateAllFieldsButFirstName() {
		String ln = "Killi";
		String em = "Kk@gmail.com";
		String ps = "Wheels";
		
		updateEmail(em);
		updateLastName(ln);
		updatePassword(ps);
			
		WebElement updateBtn = driver.findElement(By.id("updatBtn"));
		updateBtn.click(); //button doesn't work, do I need to use pageFactory???
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
		
		String fullname = driver.findElement(By.id("fullname")).getText();
		String email = driver.findElement(By.id("email")).getText();
		
		assertTrue(fullname.contains(ln));
		assertEquals(em,email);
		//need to check password change
		
	}
	
	@Test
	public void updateAllFieldsButLastName() {
		String fn = "Maria";
		String em = "Kk@gmail.com";
		String ps = "Wheels";
		
		updateEmail(em);
		updateFirstName(fn);
		updatePassword(ps);
			
		WebElement updateBtn = driver.findElement(By.id("updatBtn"));
		updateBtn.click(); 
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
		
		String fullname = driver.findElement(By.id("fullname")).getText();
		String email = driver.findElement(By.id("email")).getText();
		
		assertTrue(fullname.contains(fn));
		assertEquals(em,email);
		
	}
	
	@Test
	public void updateAllFieldsButEmail() {
		String fn = "Luigi";
		String ln = "Daisy";
		String ps = "Wheels";
		
		updateFirstName(fn);
		updateLastName(ln);
		updatePassword(ps);
			
		WebElement updateBtn = driver.findElement(By.id("updatBtn"));
		updateBtn.click(); //button doesn't work, do I need to use pageFactory???
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
		
		String fullname = driver.findElement(By.id("fullname")).getText();
		
		assertTrue(fullname.contains(fn));
		//need to check password change
		
	}

}
