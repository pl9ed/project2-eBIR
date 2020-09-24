package com.revature.selenium.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	private static final String base_url = "http://52.205.93.132:8006/eBIRProject";
	private WebDriver webdriver;
	
	private WebElement usernameInput;
	private WebElement passInput;
	private WebElement loginBtn;
	private WebElement regBtn;
	
	public LoginPage(WebDriver wd) {
		this.webdriver = wd;
		wd.get(base_url);
	}

}
