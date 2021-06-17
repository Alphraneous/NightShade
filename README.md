# NightShade

Shield: [![CC BY-SA 4.0][cc-by-sa-shield]][cc-by-sa]

This work is licensed under a
[Creative Commons Attribution-ShareAlike 4.0 International License][cc-by-sa].

[![CC BY-SA 4.0][cc-by-sa-image]][cc-by-sa]

[cc-by-sa]: http://creativecommons.org/licenses/by-sa/4.0/
[cc-by-sa-image]: https://licensebuttons.net/l/by-sa/4.0/88x31.png
[cc-by-sa-shield]: https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg

NightShade is a bot using Amazon's Buy Now functionality to automatically refresh a product and purchase it at a specific price
It can help users obtain hard to get GPUs, such as the RTX 3000 and RX 6000 Series

After logging in to Amazon, the bot will turn on and automatically refresh the page until the price goes under the user specified limit
It will then purchase the item and stop.

This bot is simple, and more features (Hopefully AI assisted captcha solving) are yet to come. Amazon smile links are preffered due to them having less frequent captchas
 
# How to install/use
First, install java on your system. It can be found at https://java.com/en/download/
You may already have it, expecially if you also play games such as Minecraft
 
Download and extract the latest release of the bot, for whichever platform you want (Amazon drops more frequently, and the bestbuy bot is still very unstable)
 
For BestBuy:
Open a browser, and navigate to the bestbuy page of the product you wish to purchase, and copy the URL from the URL bar
Open the extracted bot files, and run the start.bat
When prompted, paste the product URL, enter maximum price, and sign in to bestbuy
One you press enter, the bot will start

For Amazon:
Open a browser, and navigate to the Amazon page of the product you wish to purchase
Reccomended: once you are at the amazon product page (www.amazon.com/someStuff), replace the "www" with "smile" (smile.amazon.com/someStuff)
You can then choose a charity, and any purchases you make will benefit that charity, at no cost to you. In addition, the captchas are less frequent. Make sure to do this before starting the bot
Copy the normal or smile.amazon.com product url from the URL bar
Open the extracted bot files, and using a text editor, such as notepad, open the credentials.txt file
The default credentials.txt file will look like this:

email - *Amazon account email*<br/>
password - *Amazon account password*<br/>
producturl - *Amazon product url*<br/>
maxprice - *The maximum price you are willing to pay*

You can either delete this file, and be prompted for these details every time you start at the bot, or you can change these as you wish

For example:

johndoe@gmail.com <br/>
SuchAnAw#somePassword675 <br/>
https://smile.amazon.com/dp/B08WHJPBFX <br/>
800

NOTE: MAKE SURE YOU HAVE A PAYMENT METHOD LINKED TO YOUR ACCOUNT, FOR BOTH BESTBUY AND AMAZON

 

