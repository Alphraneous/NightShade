package NightShade_AMZ;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.*;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;


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

Please message Ajax21#5396 on discord for issues
*/                                                                       

        System.out.println("Starting");
        //Please set path to chromedriver here\
        File config = new File("credentials.txt");
        Boolean configFileFound = true;
        String username = null;
        String password = null;
        try {
			Scanner configReader = new Scanner(config);
			username = configReader.nextLine();
			password = configReader.nextLine();
			System.out.println("Config file found");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Credentials file not found, continuing");
			configFileFound = false;
		}
        
        System.setProperty("webdriver.chrome.driver","lib\\chromedriver.exe");
        //Hello World, Starting Webdriver
        WebDriver wd = new ChromeDriver();
        //Ask user for the product they want to buy
        System.out.println("Enter Amazon Smile (smile.amazon.com) Product URL (NO QUOTES, WHOLE URL, INCLUDING HTTPS; COPY DIRECTLY FROM BROWSER)");
        Scanner productPage = new Scanner(System.in);
        String productURL = productPage.nextLine();
        //Ask user for the maximum price they are willing to pay for the product
        System.out.println("Please Enter Maximum Product Price (Integer in USD, no dollar signs, commas, or decimals)");
        Scanner priceCut = new Scanner(System.in);
        int cutoffPrice = Integer.parseInt(priceCut.nextLine()); 
        //Open the amazon sign in page so the user can sign in
        wd.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Faws%2Fcart%2Fadd.html%3F%26ref_%3Dnav_custrec_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
        if (configFileFound) {
        	WebElement emailBox = wd.findElement(By.xpath("//*[@id='ap_email']"));
        	emailBox.sendKeys(username);
        	WebElement continueButton = wd.findElement(By.xpath("//*[@id=\'continue\']"));
        	continueButton.click();
        	WebElement passwordBox = wd.findElement(By.xpath("//*[@id=\'ap_password\']"));
        	passwordBox.sendKeys(password);
        	WebElement loginButton = wd.findElement(By.xpath("//*[@id=\'signInSubmit\']"));
        	loginButton.click();
        } else {
        	System.out.println("Please Sign In And Press Any Key to Start Bot");
        	Scanner userInput = new Scanner(System.in);
            String inputNull = userInput.nextLine(); 
        }
        // NOTE: userInput is not used because it is simply to stop the below code from running until the user has logged in
        System.out.println("Bot Started, Best of Luck!");
        wd.get(productURL);
        while (true) {
        //See if the product is in stock by seeing if a price textbox is visible
        try {
        boolean isOutOfStock = wd.findElements(By.xpath("//*[@id=\'availability\']/span")).size() > 0;
        Random delay = new Random();
        double delayAmount = 0.5 + (1.5 - 0.5) * delay.nextDouble();
        if (wd.findElements(By.xpath("//*[ contains (text(), 'not a robot' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'servers are getting hit' ) ]")).size() > 0) {
    		System.out.println("Please solve the captcha and press enter to continue");
    		Scanner captcha = new Scanner(System.in);
            String phs1 = captcha.nextLine();
    	}
        if (!isOutOfStock) {
            //Retrieve Price
        	String price = null;
			if (wd.findElements(By.xpath("//*[@id='price_inside_buybox']")).size() > 0) {
        		price = wd.findElement(By.xpath("//*[@id='price_inside_buybox']")).getText();
        	} else if (wd.findElements(By.xpath("//*[@id=\'priceblock_ourprice\']")).size() > 0) {
        		price = wd.findElement(By.xpath("//*[@id=\'priceblock_ourprice\']")).getText();
        	} else if (wd.findElements(By.xpath("//*[ contains (text(), 'not a robot' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'servers are getting hit' ) ]")).size() > 0) {
        		System.out.println("Please solve the captcha and press enter to continue");
        		Scanner captcha = new Scanner(System.in);
                String phs1 = captcha.nextLine();
        	} else {
        		System.out.println("An error has occurred, please restart bot");
        	}
            //Format the text to be readable as an integer
            String priceRev1 = price.substring(1,(price.length()-3));
            String priceRev2 = priceRev1.replaceAll(",","");
            int priceNum = Integer.parseInt(priceRev2);
            System.out.println("Price is:" + price);
            //Check if the price is higher than the user specified
            if (priceNum > cutoffPrice) {
                //If the price of the item is too high, refresh the page
                System.out.println("Price is too high, retrying");
                try {
					TimeUnit.SECONDS.sleep((long) delayAmount);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                wd.navigate().refresh();
            } else {
            //If the product is in stock for under the user specified price, proceed with the order
            WebElement buyNowButton = wd.findElement(By.xpath("//*[@id=\'buy-now-button\']"));
            buyNowButton.click();
            while (true) {
                boolean placeOrder1Visible = wd.findElements(By.xpath("//*[@id=\'placeYourOrder\']/span/input")).size() > 0;
                boolean placeOrder2Visible = wd.findElements(By.xpath("//*[@id=\'turbo-checkout-pyo-button\']")).size() > 0;
                	if (placeOrder1Visible) {
                		WebElement placeOrderButton = wd.findElement(By.xpath("//*[@id=\'placeYourOrder\']/span/input"));
                    	placeOrderButton.click();
                    	System.out.println("Product Successfully Purchased, Browser Will Close in 5 Seconds");
                    	break;
                	} else if (placeOrder2Visible) {
                		WebElement placeOrderButton = wd.findElement(By.xpath("//*[@id=\'turbo-checkout-pyo-button\']/span/input"));
                    	placeOrderButton.click();
                    	System.out.println("Product Successfully Purchased, Browser Will Close in 5 Seconds");
                    	break;
                	}
            	}
            	try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	wd.quit();
            	System.out.println("Browser Closed");
            	System.out.println("Congratulations! You got one!");
            	
            
            }
        } else {
            //If the item isn't in stock, refresh the page
            System.out.println(wd.findElement(By.xpath("//*[@id=\'availability\']/span")).getText());
            try {
				TimeUnit.SECONDS.sleep((long) delayAmount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            wd.navigate().refresh();
        }
        } catch (NoSuchWindowException e) {
        	System.out.println("Window Closed");
        	System.out.println(e);
        	wd.quit();
        	break;
        }
        }
    }  
}
