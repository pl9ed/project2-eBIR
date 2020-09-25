package com.revature.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
private WebDriver driver;
	
	private WebElement header;
	private WebElement usernameField;
	private WebElement passwordField;
	private WebElement loginButton;
	private WebElement toRegisterButton;
	
	private static final String title = "eBIRProject";
	
	public LoginPage(WebDriver driver) {
		super();
		this.driver= driver;
		WebElement appLogin = driver.findElement(By.xpath("//div[@class=\"loginDiv\"]"));
		this.header = driver.findElement(By.tagName("h1"));
		this.usernameField = driver.findElement(By.id("username"));
		this.passwordField = driver.findElement(By.id("password"));
		this.loginButton = driver.findElement(By.name("login"));
		this.toRegisterButton = driver.findElement(By.name("toRegister"));
	}
	
	public void setUsername(String username) {
		this.usernameField.sendKeys(username);
	}
	
	public String getUsername() {
		return this.usernameField.getAttribute("value");
	}

	public String getHeader() {
		return header.getText();
	}

	public String getPassword() {
		return passwordField.getAttribute("value");
	}

	public void setPassword(String password) {
		this.passwordField.clear();
		this.passwordField.sendKeys("password");
	}

	public void submit() {
		this.loginButton.click();
	}
	
	public void navigateTo() {
		this.driver.get("http://localhost:4200/eBIRProject#/login");
	}
}
