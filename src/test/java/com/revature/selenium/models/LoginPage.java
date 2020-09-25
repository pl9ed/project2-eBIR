package com.revature.selenium.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	private static String base_url = "http://52.205.93.132:8006/eBIRProject/";
	private WebDriver webdriver;
	
	private WebElement usernameInput;
	private WebElement passInput;
	private WebElement loginBtn;
	private WebElement regBtn;
	
	public LoginPage(WebDriver wd) {
		this.webdriver = wd;
		this.navTo();
	}
	
	// second constructor in case we do need to set env var
	public LoginPage(WebDriver wd, String base_url) {
		this.webdriver = wd;
		LoginPage.base_url = base_url;
		this.navTo();
	}
	
	// just nav to base since it redirects to login page anyways
	public void navTo() {
		webdriver.get(base_url);
	}

}
