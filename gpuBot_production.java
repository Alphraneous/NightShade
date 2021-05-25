import java.net.*;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class gpuBot_production {
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

NightShade is an amazon bot that uses the "Buy Now" button to refresh and purchase hard to find 
GPU's, such as the RX 6000 and RTX 3060 Series

DEPENDENCIES: 
Selenium and ChromeDriver
I recommend that you run this in VS Code, Eclipse, or some other IDE for easy syntax based editing
Chromedriver location must be called below

Please message Ajax21#5396 on discord for issues
*/                                                                       

        System.out.println("Starting");
        //Please set path to chromedriver here\
        //NOTE: file path MUST use double forward slashes (\\), not single forward slashes (\) or backslashes (/), otherwise an error will be thrown 
        System.setProperty("webdriver.chrome.driver","C:\\Path\\To\\chromedriver.exe");
        //Hello World, Starting Webdriver
        WebDriver wd = new ChromeDriver();
        //Ask user for the product they want to buy
        System.out.println("Enter Amazon Product URL (NO QUOTES, WHOLE URL, INCLUDING HTTPS; COPY DIRECTLY FROM BROWSER)");
        Scanner productPage = new Scanner(System.in);
        String productURL = productPage.nextLine();
        //Ask user for the maximum price they are willing to pay for the product
        System.out.println("Please Enter Maximum Product Price (Integer in USD, no dollar signs, commas, or decimals)");
        Scanner priceCut = new Scanner(System.in);
        int cutoffPrice = Integer.parseInt(priceCut.nextLine()); 
        //Open the amazon sign in page so the user can sign in
        wd.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Faws%2Fcart%2Fadd.html%3F%26ref_%3Dnav_custrec_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
        System.out.println("Please Sign In And Press Any Key to Start Bot");
        Scanner userInput = new Scanner(System.in);
        String inputNull = userInput.nextLine(); 
        // NOTE: userInput is not used because it is simply to stop the below code from running until the user has logged in
        wd.get(productURL);
        while (true) {
        //See if the product is in stock by seeing if a price textbox is visible
        boolean isInStock = wd.findElement(By.xpath("//*[@id='price_inside_buybox']")).isDisplayed();
        if (isInStock) {
            //Retrieve Price
            String price = wd.findElement(By.xpath("//*[@id='price_inside_buybox']")).getText();
            //Format the text to be readable as an integer
            String priceRev1 = price.substring(1,(price.length()-3));
            String priceRev2 = priceRev1.replaceAll(",","");
            int priceNum = Integer.parseInt(priceRev2);
            System.out.println("Price is:" + price);
            //Check if the price is higher than the user sepcified
            if (priceNum > cutoffPrice) {
                //If the price of the item is too high, refresh the page
                System.out.println("Price is too high, retrying");
                wd.navigate().refresh();
            } else {
            //If the product is in stock for under the user specified price, proceed with the order
            WebElement buyNowButton = wd.findElement(By.xpath("//*[@id='buy-now-button']"));
            buyNowButton.click();
            while (true) {
                WebElement placeOrderButton = wd.findElement(By.xpath("//*[@id='turbo-checkout-pyo-button']"));
                boolean placeOrderVisible = wd.findElement(By.xpath("//*[@id='turbo-checkout-pyo-button']")).isDisplayed();
                if (placeOrderVisible) {
                    placeOrderButton.click();
                }
            }
            }
        } else {
            //If the item isn't in stock, refresh the page
            System.out.println("Not In Stock");
            wd.navigate().refresh();
        }
        }
    }  
}
