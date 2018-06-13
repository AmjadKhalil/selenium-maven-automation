package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		// set up chrome driver path
		WebDriverManager.chromedriver().setup();
		//invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		//fullscreen
		driver.manage().window().maximize();
		//Set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		/*Step 1. Launch browser and navigate to https://dice.com 
        Expected: dice home page should be displayed
		 */
		
		String url = "https://dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice Homepage successfully downloaded");
		}else {
			System.out.println("Step FAIL. Dice Homepage FAILED to download");
			throw new RuntimeException("Step FAIL. Dice Homepage FAILED to download");
		}
		
		
		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear(); //clears whatever this field might contain by default
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		int countResult = Integer.parseInt(count.replace(",", ""));
		
		if(countResult > 0 ) {
			System.out.println("Step PASS: Keyword : " + keyword + " search returened " +
		countResult + " results in zip code" + location);
		}else {
			System.out.println("Sep FAIL:  Keyword : " + keyword + " search returened " +
					countResult + " results in zip code " + location);
		}
		
		driver.close();
		System.out.println("Test Completed " + LocalDateTime.now());
		
		
		
	}
}
