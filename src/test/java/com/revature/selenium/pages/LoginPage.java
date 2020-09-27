package com.revature.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;

@Getter
public class LoginPage {
	private WebDriver driver;
	private WebElement username;
	private WebElement password;
	private WebElement loginBtn;
	private WebElement regBtn;
	
	private WebDriverWait wait;
	
	private static final String base_url = System.getenv("base_url");
	
	public LoginPage(WebDriver d) {
		this.driver = d;
		driver.get(base_url);
		
		wait = new WebDriverWait(driver,5);
		wait.until(driver -> driver.findElement(By.id("username")));
		
		this.username = driver.findElement(By.id("username"));
		this.password = driver.findElement(By.id("password"));
		this.loginBtn = driver.findElement(By.id("loginBtn"));
		this.regBtn = driver.findElement(By.id("toRegBtn"));
	}	

}
