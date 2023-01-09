package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchProduct {
	WebDriver driver;
	
	public SearchProduct(WebDriver driver){
		this.driver=driver;
	}
	
	public void searchItem() {
		WebElement searchBar=driver.findElement(By.xpath("//input[@placeholder]"));
		searchBar.sendKeys("redmi");
		searchBar.sendKeys(Keys.RETURN);
	}
	
	
}
