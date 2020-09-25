package com.revature;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

	public static void main(String[] args) {
		File file = new File("src/main/resources/chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		System.getProperty("webdriver.chrome.driver");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:4200/eBIRProject#/login");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// Selenium will wait 2 seconds before throwing an exception
		// If I try to get an element, but can't, then keep trying for 2 seconds before failing
		// with an exception
		
		WebElement submitButton = driver.findElement(By.name("toRegister"));
		
		submitButton.click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement cpassword = driver.findElement(By.id("confirmpassword"));
		WebElement firstname = driver.findElement(By.id("firstname"));
		WebElement lastname = driver.findElement(By.id("lastname"));
		WebElement email = driver.findElement(By.id("email"));
		submitButton = driver.findElement(By.name("register"));
		
		username.sendKeys("jandrew");
		password.sendKeys("qwerty");
		cpassword.sendKeys("qwerty");
		firstname.sendKeys("Julien");
		lastname.sendKeys("Andrew");
		email.sendKeys("ja@gmail.com");
		
		submitButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"homeDiv\"]")));
		
		submitButton = driver.findElement(By.name("logout"));
		submitButton.click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"loginDiv\"]")));
		
		username = driver.findElement(By.id("username"));
		password = driver.findElement(By.id("password"));
	    submitButton = driver.findElement(By.name("login"));
		
		username.sendKeys("jandrew");
		password.sendKeys("qwerty");
		submitButton.click();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
		// This will close the browser and shut down the driver
	}

}
