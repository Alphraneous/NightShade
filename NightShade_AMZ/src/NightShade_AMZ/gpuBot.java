package NightShade_AMZ;

import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.TakesScreenshot;


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
    	final String ANSI_RESET = "<ESC>[0m"; 
    	final String ANSI_BLACK = "\u001B[30m"; 
    	final String ANSI_RED = "\u001B[31m"; 
    	final String ANSI_GREEN = "\u001B[32m"; 
    	final String ANSI_YELLOW = "<ESC>[93m"; 
    	final String ANSI_BLUE = "\u001B[34m"; 
    	final String ANSI_PURPLE = "\u001B[35m"; 
    	final String ANSI_CYAN = "\u001B[36m"; 
    	final String ANSI_WHITE = "\u001B[37m"; 
    	
        System.out.println(ANSI_YELLOW + "Starting" + ANSI_RESET);
        Boolean testMode = false;
        if (args.length > 0) {
        	for (String arg : args) {
        		if (arg.equals("--test")) {
        			System.out.println("Test mode is enabled, no purchases will be made");
        			testMode = true;
        		} else {
        			System.out.println("Production mode is enabled, purchases WILL be made");
        		}
        	}
        }
        File config = new File("credentials.txt");
        Boolean configFileFound = true;
        String username = null;
        String password = null;
        String productURL = null;
        Integer maxPrice = null;
        Boolean productURLFound = true;
        Boolean maxPriceFound = true;
        try {
			Scanner configReader = new Scanner(config);
			System.out.println("Configuration file found");
			username = configReader.nextLine();
			if (!username.contains("@")) {
				System.out.println("Invalid email, please check config file");
				System.exit(0);
			}
			password = configReader.nextLine();
			productURL = configReader.nextLine();
			if (!productURL.contains("amazon")) {
				System.out.println("Your product URL is not an amazon link, please check config file");
				System.exit(0);
			}
			try {
				maxPrice = Integer.parseInt(configReader.nextLine());
				System.out.println("Maximum product price is: $" + maxPrice);
			} catch (Exception e) {
				System.out.println("Invalid max price, please check config file");
				System.exit(0);
			}
			productURLFound = productURL.length() > 0;
			maxPriceFound = maxPrice.toString().length() > 0;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Credentials file not found, continuing");
			configFileFound = false;
		}
        System.setProperty("webdriver.chrome.driver","lib\\chromedriver.exe");
        //Hello World, Starting Webdriver
        WebDriver wd = new ChromeDriver();
        //Open the amazon sign in page so the user can sign in
        wd.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Faws%2Fcart%2Fadd.html%3F%26ref_%3Dnav_custrec_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
        if (!productURLFound) {
        	System.out.println("Enter Amazon Smile (smile.amazon.com) Product URL (NO QUOTES, WHOLE URL, INCLUDING HTTPS; COPY DIRECTLY FROM BROWSER)");
            Scanner productPage = new Scanner(System.in);
            productURL = productPage.nextLine();
        }
        if (!maxPriceFound) {
        	//Ask user for the maximum price they are willing to pay for the product
            System.out.println("Please Enter Maximum Product Price (Integer in USD, no dollar signs, commas, or decimals)");
            Scanner priceCut = new Scanner(System.in);
            maxPrice = Integer.parseInt(priceCut.nextLine());
        }
        if (configFileFound) {
        	WebElement emailBox = wd.findElement(By.xpath("//*[@id='ap_email']"));
        	emailBox.sendKeys(username);
        	WebElement continueButton = wd.findElement(By.xpath("//*[@id=\'continue\']"));
        	continueButton.click();
        	if (wd.findElements(By.xpath("//*[ contains (text(), 'There was a problem' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'We cannot find' ) ]")).size() > 0 ) {
        		System.out.println("Your email is incorrect, please check config file");
        		System.exit(0);
        	}
        	WebElement passwordBox = wd.findElement(By.xpath("//*[@id=\'ap_password\']"));
        	passwordBox.sendKeys(password);
        	WebElement loginButton = wd.findElement(By.xpath("//*[@id=\'signInSubmit\']"));
        	loginButton.click();
        	if (wd.findElements(By.xpath("//*[ contains (text(), 'There was a problem' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'incorrect' ) ]")).size() > 0 ) {
        		System.out.println("Your password is incorrect, please check config file");
        		System.exit(0);
        	}
        } else {
        	//Ask user for the product they want to buy 
        	System.out.println("Please Sign In And Press Any Key to Start Bot");
        	Scanner userInput = new Scanner(System.in);
            String inputNull = userInput.nextLine(); 
        }
        
        if (wd.findElements(By.xpath("//*[ contains (text(), 're-enter' ) ]")).size() > 0) {
        	WebElement passwordBox = wd.findElement(By.xpath("//*[@id=\'ap_password\']"));
        	passwordBox.sendKeys(password);
        	Tesseract captchaOCR = new Tesseract();
        	WebElement imageBox = wd.findElement(By.xpath("//*[@id=\'auth-captcha-image\']"));
        	File imageFile = imageBox.getScreenshotAs(OutputType.FILE);
            captchaOCR.setDatapath("lib\\Tess4J\\tessdata");
            String solvedCaptcha = null;
            try {
				solvedCaptcha = captchaOCR.doOCR(imageFile);
				WebElement captchaBox = wd.findElement(By.xpath("//*[@id=\'auth-captcha-guess\']"));
	            captchaBox.sendKeys(solvedCaptcha);
			} catch (TesseractException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            WebElement loginButton = wd.findElement(By.xpath("//*[@id=\'signInSubmit\']"));
        	loginButton.click();
        	if (wd.findElements(By.xpath("//*[ contains (text(), 'There was a problem' ) ]")).size() > 0 ) {
        		System.out.println("Automatic captcha solving failed, please try manually, then press enter to continue");
        		Scanner captcha = new Scanner(System.in);
                String phs1 = captcha.nextLine();
                wd.get(productURL);
        	}
    		
    	}
        if (wd.findElements(By.xpath("//*[ contains (text(), 'not a robot' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'servers are getting hit' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'Enter the characters' ) ]")).size() > 0 ) {
    		Tesseract captchaOCR = new Tesseract();
            captchaOCR.setDatapath("/lib/Tess4J/tessdata");
            System.out.println("Please solve captcha and press enter to continue");
    		Scanner captcha = new Scanner(System.in);
            String phs1 = captcha.nextLine();
    	}
        // NOTE: userInput is not used because it is simply to stop the below code from running until the user has logged in
        System.out.println("Bot Started, Best of Luck!");
        wd.get(productURL);
        masterLoop:
        while (true) {
        //See if the product is in stock by seeing if a price textbox is visible
        try {
        Random delay = new Random();
        double delayAmount = 0.5 + (1.5 - 0.5) * delay.nextDouble();
        boolean isOutOfStock = wd.findElements(By.xpath("//*[ contains (text(), 'Currently unavailable' ) ]")).size() > 0;
        outerLoop:
        if (!isOutOfStock) {
            //Retrieve Price
        	String price = null;
        	if (wd.findElements(By.xpath("//*[ contains (text(), 'See All Buying' ) ]")).size() > 0) {
        		WebElement seeAllOffers = wd.findElement(By.xpath("//*[@id=\'buybox-see-all-buying-choices\']"));
        		seeAllOffers.click();
        		try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		while (true) {
        			if (wd.findElements(By.xpath("//*[@id=\'aod-price-1\']/span/span[2]/span[2]")).size() > 0) {
        				break;
        				}
        			}
        		for (int i = 1;i<7;i++) {
        			if (wd.findElements(By.xpath("//*[@id=\'aod-price-"+i+"\']/span/span[2]/span[2]")).size() > 0) {
        				WebElement priceOffer = wd.findElement(By.xpath("//*[@id=\'aod-price-"+i+"\']/span/span[2]/span[2]"));
        				String priceRev1 = priceOffer.getText();  
        				String priceRev2 = priceRev1.replaceAll(",","");
        				Integer pricenum = Integer.parseInt(priceRev2);
        				System.out.println("One offer for: " + pricenum);
        				if (pricenum > maxPrice) {
        					System.out.println("Offer " + i + " price is too high");
        				} else {
        					System.out.println("Offer " + i + " is under max price, purchasing");
        					WebElement offerATCBtn = wd.findElement(By.xpath("/html/body/div[1]/span/span/span/div/div/div[4]/div["+i+"]/div[2]/div/div/div[2]/div/div/div[2]/form/span/span/span/input"));
        					offerATCBtn.click();
        					if (wd.findElements(By.xpath("//*[ contains (text(), 'Failed to add' ) ]")).size() > 0 || (wd.findElements(By.xpath("//*[ contains (text(), 'Nothing was added' ) ]")).size() > 0) || (wd.findElements(By.xpath("//*[ contains (text(), 'Not added' ) ]")).size() > 0)) {
        						System.out.println("Adding to cart failed, trying again");
        						wd.get(productURL);
        						break outerLoop;
        					}
        					for (int s = 0;s < 5;s++) {
        						if (wd.findElements(By.xpath("//*[@id=\'hlb-ptc-btn-native\']")).size() > 0) {
        							if (wd.findElements(By.xpath("//*[ contains (text(), 'No thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No thanks' ) ]"));
        								noThanks.click();
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'no thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'no thanks' ) ]"));
        								noThanks.click();
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'No Thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No Thanks' ) ]"));
        								noThanks.click();
        							}
        							break;
        						}
        						try {
        							TimeUnit.SECONDS.sleep((long) 0.1);
        						} catch (InterruptedException e) {
        							// TODO Auto-generated catch block
        							e.printStackTrace();
        						}
        					}
        					if (wd.findElements(By.xpath("//*[@id=\'hlb-ptc-btn-native\']")).size() > 0) {
    							WebElement checkoutBtn = wd.findElement(By.xpath("//*[@id=\'hlb-ptc-btn-native\']"));
    							checkoutBtn.click();
        						if (wd.findElements(By.xpath("//*[ contains (text(), 'Enjoy immediate access' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'Prime Benefits' ) ]")).size() > 0) {
        							if (wd.findElements(By.xpath("//*[ contains (text(), 'No thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'no thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'no thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'No Thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No Thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							}
        						
        						}
        					}
        					WebElement placeYourOrder = wd.findElement(By.xpath("//*[@id=\'placeYourOrder\']"));
        					if (!testMode) {
        						placeYourOrder.click();
        					}
        					System.out.println("Product Successfully Purchased, Browser Will Close in 5 Seconds");
                        	try {
            					TimeUnit.SECONDS.sleep(5);
            				} catch (InterruptedException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
                        	wd.quit();
                        	System.out.println("Browser Closed");
                        	System.out.println("Congratulations! You got one!");
                        	break masterLoop;
        				}
        			} else {
        				System.out.println("No offers available");
        				break;
        			}
        		}
        		try {
					TimeUnit.SECONDS.sleep((long) delayAmount);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		wd.navigate().refresh();
        	} else {
        		if (wd.findElements(By.xpath("//*[@id='price_inside_buybox']")).size() > 0) {
        			price = wd.findElement(By.xpath("//*[@id='price_inside_buybox']")).getText();
        		} else if (wd.findElements(By.xpath("//*[@id=\'priceblock_ourprice\']")).size() > 0) {
        			price = wd.findElement(By.xpath("//*[@id=\'priceblock_ourprice\']")).getText();
        		} else {
        			System.out.println("An error has occurred, please restart bot");
        		}
        		 //Format the text to be readable as an integer
                String priceRev1 = price.substring(1,(price.length()-3));
                String priceRev2 = priceRev1.replaceAll(",","");
                int priceNum = Integer.parseInt(priceRev2);
                System.out.println("Price is:" + price);
                //Check if the price is higher than the user specified
                if (priceNum > maxPrice) {
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
                if (wd.findElements(By.xpath("//*[@id=\'buy-now-button\']")).size() > 0) {
                	WebElement buyNowButton = wd.findElement(By.xpath("//*[@id=\'buy-now-button\']"));
                	buyNowButton.click();
                	if (wd.findElements(By.xpath("//*[ contains (text(), 'Failed to add' ) ]")).size() > 0 || (wd.findElements(By.xpath("//*[ contains (text(), 'Nothing was added' ) ]")).size() > 0) || (wd.findElements(By.xpath("//*[ contains (text(), 'Not added' ) ]")).size() > 0)) {
                		System.out.println("Adding to cart failed, trying again");
                		wd.get(productURL);
                		break outerLoop;
                	}
                	while (true) {
                		boolean placeOrder1Visible = wd.findElements(By.xpath("//*[@id=\'placeYourOrder\']/span/input")).size() > 0;
                		boolean placeOrder2Visible = wd.findElements(By.xpath("//*[@id=\'turbo-checkout-pyo-button\']")).size() > 0;
                			if (placeOrder1Visible) {
                				WebElement placeOrderButton = wd.findElement(By.xpath("//*[@id=\'placeYourOrder\']/span/input"));
                				if (!testMode) {
                					placeOrderButton.click();
                				}
                				System.out.println("Product Successfully Purchased, Browser Will Close in 5 Seconds");
                				break;
                			} else if (placeOrder2Visible) {
                				WebElement placeOrderButton = wd.findElement(By.xpath("//*[@id=\'turbo-checkout-pyo-button\']/span/input"));
                				if (!testMode) {
                					placeOrderButton.click();
                				}
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
                	
                	} else if (wd.findElements(By.xpath("//*[@id=\'add-to-cart-button-ubb\']")).size() > 0 || wd.findElements(By.xpath("//*[@id=\'add-to-cart-button\']")).size() > 0) {
                		if (wd.findElements(By.xpath("//*[@id=\'add-to-cart-button-ubb\']")).size() > 0) {
        					WebElement offerATCBtn = wd.findElement(By.xpath("//*[@id=\'add-to-cart-button-ubb\']"));
        					offerATCBtn.click();
        					if (wd.findElements(By.xpath("//*[ contains (text(), 'Failed to add' ) ]")).size() > 0 || (wd.findElements(By.xpath("//*[ contains (text(), 'Nothing was added' ) ]")).size() > 0) || (wd.findElements(By.xpath("//*[ contains (text(), 'Not added' ) ]")).size() > 0)) {
        						System.out.println("Adding to cart failed, trying again");
        						wd.get(productURL);
        						break outerLoop;
        					}
        					for (int s = 0;s < 5;s++) {
        						if (wd.findElements(By.xpath("//*[@id=\'hlb-ptc-btn-native\']")).size() > 0) {
        							if (wd.findElements(By.xpath("//*[ contains (text(), 'No thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No thanks' ) ]"));
        								noThanks.click();
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'no thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'no thanks' ) ]"));
        								noThanks.click();
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'No Thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No Thanks' ) ]"));
        								noThanks.click();
        							}
        							break;
        						}
        						try {
        							TimeUnit.SECONDS.sleep((long) 0.1);
        						} catch (InterruptedException e) {
        							// TODO Auto-generated catch block
        							e.printStackTrace();
        						}
        					}
        					if (wd.findElements(By.xpath("//*[@id=\'hlb-ptc-btn-native\']")).size() > 0) {
    							WebElement checkoutBtn = wd.findElement(By.xpath("//*[@id=\'hlb-ptc-btn-native\']"));
    							checkoutBtn.click();
        						if (wd.findElements(By.xpath("//*[ contains (text(), 'Enjoy immediate access' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'Prime Benefits' ) ]")).size() > 0) {
        							if (wd.findElements(By.xpath("//*[ contains (text(), 'No thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'no thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'no thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'No Thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No Thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							}
        						
        						}
        					}
        					WebElement placeYourOrder = wd.findElement(By.xpath("//*[@id=\'placeYourOrder\']"));
        					if (!testMode) {
        						placeYourOrder.click();
        					}
        					System.out.println("Product Successfully Purchased, Browser Will Close in 5 Seconds");
                        	try {
            					TimeUnit.SECONDS.sleep(5);
            				} catch (InterruptedException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
                        	wd.quit();
                        	System.out.println("Browser Closed");
                        	System.out.println("Congratulations! You got one!");
                        	break masterLoop;
                		} else if (wd.findElements(By.xpath("//*[@id=\'add-to-cart-button\']")).size() > 0) {
        					WebElement offerATCBtn = wd.findElement(By.xpath("//*[@id=\'add-to-cart-button\']"));
        					offerATCBtn.click();
        					if (wd.findElements(By.xpath("//*[ contains (text(), 'Failed to add' ) ]")).size() > 0 || (wd.findElements(By.xpath("//*[ contains (text(), 'Nothing was added' ) ]")).size() > 0) || (wd.findElements(By.xpath("//*[ contains (text(), 'Not added' ) ]")).size() > 0)) {
        						System.out.println("Adding to cart failed, trying again");
        						wd.get(productURL);
        						break outerLoop;
        					}
        					for (int s = 0;s < 5;s++) {
        						if (wd.findElements(By.xpath("//*[@id=\'hlb-ptc-btn-native\']")).size() > 0) {
        							if (wd.findElements(By.xpath("//*[ contains (text(), 'No thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No thanks' ) ]"));
        								noThanks.click();
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'no thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'no thanks' ) ]"));
        								noThanks.click();
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'No Thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No Thanks' ) ]"));
        								noThanks.click();
        							}
        							break;
        						}
        						try {
        							TimeUnit.SECONDS.sleep((long) 0.1);
        						} catch (InterruptedException e) {
        							// TODO Auto-generated catch block
        							e.printStackTrace();
        						}
        					}
        					if (wd.findElements(By.xpath("//*[@id=\'hlb-ptc-btn-native\']")).size() > 0) {
    							WebElement checkoutBtn = wd.findElement(By.xpath("//*[@id=\'hlb-ptc-btn-native\']"));
    							checkoutBtn.click();
        						if (wd.findElements(By.xpath("//*[ contains (text(), 'Enjoy immediate access' ) ]")).size() > 0 || wd.findElements(By.xpath("//*[ contains (text(), 'Prime Benefits' ) ]")).size() > 0) {
        							if (wd.findElements(By.xpath("//*[ contains (text(), 'No thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'no thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'no thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							} else if (wd.findElements(By.xpath("//*[ contains (text(), 'No Thanks' ) ]")).size() > 0) {
        								WebElement noThanks = wd.findElement(By.xpath("//*[ contains (text(), 'No Thanks' ) ]"));
        								if (noThanks.isDisplayed()) {
        									noThanks.click();
        								}
        							}
        						
        						}
        					}
        					WebElement placeYourOrder = wd.findElement(By.xpath("//*[@id=\'placeYourOrder\']"));
        					if (!testMode) {
        						placeYourOrder.click();
        					}
        					System.out.println("Product Successfully Purchased, Browser Will Close in 5 Seconds");
                        	try {
            					TimeUnit.SECONDS.sleep(5);
            				} catch (InterruptedException e) {
            					// TODO Auto-generated catch block
            					e.printStackTrace();
            				}
                        	wd.quit();
                        	System.out.println("Browser Closed");
                        	System.out.println("Congratulations! You got one!");
                        	break masterLoop;
                		}
                	}
                }
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
