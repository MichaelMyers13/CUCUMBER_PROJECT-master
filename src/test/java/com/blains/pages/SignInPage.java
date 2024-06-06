package com.blains.pages;

import com.blains.utilities.BrowserUtils;
import com.blains.utilities.Driver;
import com.blains.utilities.Environment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
	
	public SignInPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}	
	
	@FindBy(name="email")
	public WebElement emailField;

	@FindBy(name = "password")
	public WebElement passwordField;
	
	@FindBy(xpath = "//button[.='sign in']")
	public WebElement signInButton;

	//Roles : USER1 , team-member , team-leader
	public void login(String role) {
		String email = "";
		String password = "";

		switch (role) {
			case "USER1":
				email = Environment.USER1_EMAIL;
				password = Environment.USER1_PASSWORD;
				break;

			case "team-member":
				email = Environment.MEMBER_EMAIL;
				password = Environment.MEMBER_PASSWORD;
				break;
			case "team-leader":
				email = Environment.LEADER_EMAIL;
				password = Environment.LEADER_PASSWORD;
				break;
			default:

				throw new RuntimeException("Invalid Role Entry : >> " + role + " <<");
		}
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		BrowserUtils.waitFor(1);
		signInButton.click();

	}
	
}
