package com.revature.selenium;

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

class UpdateTest {

	private static WebDriver driver;

	@BeforeAll
	static void beforeClass() {
		//log in as a:a
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://52.205.93.132:8006/eBIRProject/#/login");
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		username.sendKeys("a");
		password.sendKeys("a");
	
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
		WebElement loginBtn = driver.findElement(By.name("login"));
		loginBtn.click(); 
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
		WebElement profileBtn = driver.findElement(By.id("ProfileBtn"));
		profileBtn.click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
		WebElement updateBtn = driver.findElement(By.name("update"));
		updateBtn.click();
		
	}
	
	@AfterAll
	static void afterClass() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
		WebElement updateBtn = driver.findElement(By.id("updateProfileBtn"));
		updateBtn.click();
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
		WebElement firstname = driver.findElement(By.id("uFirstname"));	
		firstname.sendKeys("new 1st name");
	}
	
	@Test
	void updateLastName() {
		WebElement lastname = driver.findElement(By.id("uLastname"));
		lastname.sendKeys("new last name");
	}
	
	@Test
	void updatePassword() {
		WebElement password = driver.findElement(By.id("uPassword"));
		password.sendKeys("newpassword");
	}
	
	@Test
	void updateEmail() {
		WebElement email = driver.findElement(By.id("uEmail"));
		email.sendKeys("newEmail@gmail.com");
	}

}
