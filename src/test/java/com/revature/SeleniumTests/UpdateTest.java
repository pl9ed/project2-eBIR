package com.revature.SeleniumTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class UpdateTest {

	private static WebDriver driver;

	@BeforeClass 
	void beforeClass() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	
		//registering a new user with Hot wheels as username and password
		WebDriver driver = new ChromeDriver();
		driver.get("http://52.205.93.132:8006/eBIRProject/#/login");
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
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
		WebElement registerBtn = driver.findElement(By.id("register"));
		registerBtn.click(); 
	}
	
	@AfterClass
	void afterClass() {
		driver.quit();
	}
	
	@Before
	void before() {
		
	}
	
	@After
	void after() {
		
	}
	
	@Test
	void updateFirstName() {
		WebElement firstname = driver.findElement(By.id("firstname"));	
		firstname.sendKeys("new 1st name");
	}
	
	@Test
	void updateLastName() {
		WebElement lastname = driver.findElement(By.id("lastname"));
		lastname.sendKeys("new last name");
	}
	
	@Test
	void updatePassword() {
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("newpassword");
	}
	
	@Test
	void updateEmail() {
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("newemail@gmail.com");
	}

}
