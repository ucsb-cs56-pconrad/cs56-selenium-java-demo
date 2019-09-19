package edu.ucsb.cs56.pconrad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */

public class SimpleScraperDemo {
    /**
     * Sample code for a (very) simple and naive web scraper.  A real scraper would need to be far more sophisticated, but this
     * shows the basic setup.
     * @param args Command Line Arguments to main (none expected in this case).
     */
    public static void main(String[] args) {
        // Set up web driver
        WebDriver driver = new ChromeDriver();

        // Get the first url
        String url = "https://derek-webapp.herokuapp.com/"; 
		driver.get(url);

        // Pull out the contents from the page
		WebElement element = driver.findElement(By.tagName("body"));
		String content = element.getText();
        System.out.println(String.format("From the web page: %s, I retrieved: %s",url,content));

        // Get another url
        url = "https://derek-webapp.herokuapp.com/bye"; 
		driver.get(url);

        // Pull out the contents from the page
		element = driver.findElement(By.tagName("body"));
		content = element.getText();
        System.out.println(String.format("From the web page: %s, I retrieved: %s",url,content));

        // We're done, so shut the web browser (this doesn't happen automatically)
		driver.quit();
    }	
}
