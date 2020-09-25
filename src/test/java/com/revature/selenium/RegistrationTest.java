package com.revature.selenium;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class RegistrationTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void registerTest() {
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

}
