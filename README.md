# cs56-selenium-java-demo

The [Original version of this tutorial](https://github.com/guanguangua/sparkjava-01) is by [Derek Ren](https://github.com/guanguangua/), for CS56/CS190J in Summer 2018.   It was updated Summer 2019 by P. Conrad.

# Selenium

Selenium WebDriver is a tool used to automate web application testing to verify that the webpage works as expected. It supports many browsers such as Firefox, Chrome, IE, and Safari. The core of Selenium is essentially a web browser, it test a webpage by simulating users accessing the webpage and check if the webpage behaves as expected. The Selenium library provided many functions that allows the testers to create interactive end-to-end test with little effort. 

# Setup

Before you can use this repo, you'll need to have downloaded the binary file for the selenium driver (either Chrome or Firefox) and added that to your system path. 

Detailed instructions on that appear here: [How to get selenium](http://ucsb-cs56.github.io/topics/selenium.md)

As a reminder: Selenium won't work unless a binary for an *appropriate* selenium driver is in your executable path
* You'll have to first choose whether to work with Firefox, Chrome, or some other browser
* You'll then need to determine the version of the browser and your platform (e.g. Chrome v76, Mac)
* You need to download an appropriate driver for *that* platform
* Then you need to put that driver in your executable path (the instructions for this also vary between Linux, Mac and Windows)
* Finally, you need to be sure that the Java code you are running is looking for the right driver in the right place.
   * For example, this line of code is looking for a Chrome Driver, not a Firefox Driver.  You'd have to change it if you intend to use Firefox.
     ```
	 		WebDriver driver = new ChromeDriver();
	 ```


Getting all of that to work can be tricky, but if you are patient, it is doable.  This is the kind of work where a pair partner can be very helpful---they may see small details that you overlook if you try to do this by yourself, and that can save you a lot of time and headaches.

# How to run the demo

The demo depends on the existence of a sample web app deployed here: <https://derek-webapp.herokuapp.com/>
* This web app displays a `<body>` element containing the text 

# Dependencies

To use Selenium, please add the following dependency to your pom.xml file.  Rather than using the version number in the example below,
you may want to check [this link at MvnRepository.com](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) to find the latest stable version, and update your dependency to that.

```xml
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>3.14.0</version>
    </dependency>
```

The javadoc for the Java Selenium driver is here: <https://seleniumhq.github.io/selenium/docs/api/java/index.html?overview-summary.html>.  That documents, for example the methods of the `WebElement` class in the sample code below.

(See latest version number for [JUnit on MvnRepository.com](https://mvnrepository.com/artifact/junit/junit)).

If you want to use it with JUnit tests, add the following dependency too (Optional but preferred)

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.8.2</version>
      <scope>test</scope>
    </dependency>


# Understanding the code

There are two Java source files in the repo:

|Filename| Explanation | Full Path |
|--------|------------|-------------|
|`SimpleScraperDemo.java` | A trivial web scraper as a simple Java main program | `src/main/java/edu/ucsb/cs56/pconrad/SimpleScraperDemo.java` |
|`SeleniumDemo01Test.java` | A very simple end-to-end test of a web site | `src/test/java/edu/ucsb/cs56/pconrad/SeleniumDemo01Test.java` |

More details on each of these follow below

# `SimpleScraperDemo.java`

This shows a plain old ordinary Java main program that uses Selenium to get the contents of two web pages, and print the results on `System.out`.

A more sophisticated web scraper might traverse an entire web app with hundreds of queries, carefully spaced over time so as not to trigger alarm bells
and rate limits, and then store the information into a JSON file, CSV file, or a database.   

To run this code, you can use either of these:
* `mvn exec:java`
* `mvn package` followed by `java -jar target/selenium-demo-01-1.0-jar-with-dependencies.jar`

# `SeleniumDemo01Test.java`

This shows an example of a couple of "end-to-end" tests of a web application.   Using JUnit, the test cases each retrieve one URL from the webapp,
and then assert that a particular element on the page is what it is expected to be.

To run this case, you can simply use:
* `mvn test`

# Resources

To understand web scraping and testing, it's important to have a solid understanding of HTML and XPath.  Even with a solid understanding,
you may occasionally need some reference material to look up details.

Some good tutorials and references can be found at [w3schools](https://w3schools.com):
* [HTML]<https://www.w3schools.com/html/default.asp>
* [XPath]<https://www.w3schools.com/xml/xpath_intro.asp>


# Details

In the `pom.xml`, if you use this plugin so that you can run the main with `mvn exec:java` 

```
   <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        ...   
    </plugin>
```

there can be a problem that arises as described in [this StackOverflow post](https://stackoverflow.com/questions/13471519/running-daemon-with-exec-maven-plugin-avoiding-illegalthreadstateexception) where some of the threads that are stated by Selenium fail
to terminate.    The way to fix it is to add a `<cleanupDaemonThreads>false</cleanupDaemonThreads>` configuration element, as shown below:

```
 <!-- enables mvn exec:java -->
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.6.0</version>
        <executions>
          <execution>
            <id>my-execution</id>
            <goals>
              <goal>java</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <mainClass>edu.ucsb.cs56.pconrad.SimpleScraperDemo</mainClass>
          <cleanupDaemonThreads>false</cleanupDaemonThreads> <!-- needed for Selenium to avoid thread cleanup errors-->
        </configuration>
      </plugin>
```