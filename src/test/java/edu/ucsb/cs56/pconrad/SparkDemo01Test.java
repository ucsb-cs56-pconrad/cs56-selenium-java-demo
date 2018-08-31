package edu.ucsb.cs56.pconrad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SparkDemo01Test {


	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void test_out() {
		assertTrue(true);
	}

	@Test
	public void test_hello() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://derek-webapp.herokuapp.com/");

		//String content = driver.getPageSource();
		WebElement element = driver.findElement(By.tagName("body"));
		String content = element.getText();

		driver.quit();
		assertEquals("Hello World", content);
	}
}
