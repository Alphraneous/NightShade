package NightShade_BB;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.*;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class gpuBot {
	
    public static void main(String[] args) {
/* 
 _   _ _       _     _   _____ _               _       
| \ | (_)     | |   | | /  ___| |             | |      
|  \| |_  __ _| |__ | |_\ `--.| |__   __ _  __| | ___  
| . ` | |/ _` | '_ \| __|`--. \ '_ \ / _` |/ _` |/ _ \ 
| |\  | | (_| | | | | |_/\__/ / | | | (_| | (_| |  __/ 
\_| \_/_|\__, |_| |_|\__\____/|_| |_|\__,_|\__,_|\___| 
          __/ |                                        
         |___/                                            

NightShade is an bot that refreshes and purchases hard to find 
GPU's, such as the RX 6000 and RTX 3060 Series

DEPENDENCIES: 
Selenium, Java and ChromeDriver
Chromedriver and Selenium Dependencies are already included in /lib

Please message Ajax21#5396 on discord if you have any issues
*/                                                                       

        System.out.println("Starting");
        System.setProperty("webdriver.chrome.driver","lib\\chromedriver.exe");
        //Hello World, Starting Webdriver
        WebDriver wd = new ChromeDriver();
        //Ask user for the product they want to buy
        System.out.println("Enter BestBuy Product URL (NO QUOTES, WHOLE URL, INCLUDING HTTPS; COPY DIRECTLY FROM BROWSER)");
        Scanner productPage = new Scanner(System.in);
        String productURL = productPage.nextLine();
        //Open the BestBuy sign in page so the user can sign in
        wd.get("https://www.bestbuy.com/");
        System.out.println("Please Sign In And Press Any Key to Start Bot");
        Scanner userInput = new Scanner(System.in);
        String inputNull = userInput.nextLine(); 
        // NOTE: userInput is not used because it is simply to stop the below code from running until the user has logged in
        System.out.println("Bot Started, Best of Luck!");
        masterLoop:
        wd.get(productURL);
        while (true) {
        	boolean soldOut = wd.findElements(By.xpath("//*[text()='Sold out']")).size() > 0;
        	if (soldOut) {
        		System.out.println("Still unavailable");
        		wd.navigate().refresh();
        	} else {
        		WebElement addToCartButton = wd.findElement(By.xpath("//*[text()='Add to Cart']"));
        		addToCartButton.click();
        		System.out.println("Added to Cart, Checking Out");
        		wd.get("https://bestbuy.com/cart");
        		boolean checkoutReady = wd.findElements(By.xpath("//*[text()='Checkout']")).size() > 0;
        		if (checkoutReady) {
        			WebElement checkoutBtn = wd.findElement(By.xpath("//*[text()='Checkout']"));
        			checkoutBtn.click();
        			boolean pmntReady = wd.findElements(By.xpath("//*[@id=\"checkoutApp\"]/div[2]/div[1]/div[1]/main/div[2]/div[2]/form/section/div/div[2]/div/div/button")).size() > 0;
        			for (int i = 0; i < 8; i++) {
        				if (pmntReady) {
        					WebElement pmntBtn = wd.findElement(By.xpath("//*[@id=\"checkoutApp\"]/div[2]/div[1]/div[1]/main/div[2]/div[2]/form/section/div/div[2]/div/div/button"));
        					pmntBtn.click();
        					break;
        				}
        			}
        			while (true) {
						boolean poReady = wd.findElements(By.xpath("//*[text()='Place Your Order']")).size() > 0;
						if (poReady) {
							WebElement placeOrderBtn = wd.findElement(By.xpath("//*[text()='Place Your Order']"));
							placeOrderBtn.click();
							break;
						}
					}
        			
        		}
        		
        	}
       }
        
    }
}
