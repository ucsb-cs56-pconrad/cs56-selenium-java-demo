# Selenium

Selenium WebDriver is a tool used to automate web application testing to verify that the webpage works as expected. It supports many browsers such as Firefox, Chrome, IE, and Safari. The core of Selenium is essentially a web browser, it test a webpage by simulating users accessing the webpage and check if the webpage behaves as expected. The Selenium library provided many functions that allows the testers to create interactive end-to-end test with little effort. 

# Setup

First of all, make sure you have downloaded the binary file for the selenium driver (either Chrome or Firefox) and added that to your system path. [How to get selenium](https://github.com/ucsb-cs56-pconrad/UCSB-CS56-pconrad.github.io/blob/master/_topics/selenium.md)

# Dependencies
To use Selenium, please add the following dependency to your pom.xml file

    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>3.14.0</version>
    </dependency>

If you want to use it with JUnit test, add the following dependency too

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.8.2</version>
      <scope>test</scope>
    </dependency>

# How to add test
The following code is located in /src/test/java/edu/ucsb/cs56/pconrad/SparkDemo01Test.java
I added some additional comment to help with your understanding

	@Test
	public void test_hello() {
        	// Opens the a chrome browser window for testing
		WebDriver driver = new ChromeDriver();
        
        	// Let the browser access a certain page and retrieves the page's full content
        	// In this case, it opens our Hello World application hosted on heroku
        	// Change it to the webpages you want to test
		driver.get("https://derek-webapp.herokuapp.com/");
        
        	// The retrieved page would be a HTML file look like this
        	/*
        	<html>
            		<head>
                		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
            		</head>
            		<body>
                		Hello World
            		</body>
        	</html>
        	*/
		
		// Find all the element that's encompassed by "<body> </body>" tags
		// In this case, Hello World is inside the "<body> </body>" tags
		WebElement element = driver.findElement(By.tagName("body"));
		
		// Parse them to String
		String content = element.getText();
		
		// Close the chrome browser window
		driver.quit();
		
		// Checks if the retrieved content is indeed "Hello World"
		assertEquals("Hello World", content);
    }

If you need additional help for HTML, [w3shool](https://www.w3schools.com/) has some pretty good tutorials.

# To run test
To run tests, simply do:
| run junit tests | Type `mvn test` |

