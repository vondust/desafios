package idwall.desafio;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RedditCrawlerMain {

	public static void main(String[] args) {

		String projectDir = System.getProperty("user.dir");

		// TODO: Setar o gecko dentro de resources
		System.setProperty("webdriver.gecko.driver", projectDir + "/lib/geckodriver.exe");

		WebDriver driver = new FirefoxDriver();

		String subreddit = "gaming";
		driver.get("https://www.reddit.com/r/" + subreddit + "/");

		System.out.println("=============================================================");
		List<WebElement> threads = driver.findElements(By.xpath("//*[contains(@class, 'thing')]"));
		for (WebElement threadWrapper : threads) {
			if (Integer.parseInt(threadWrapper.getAttribute("data-score")) >= 5000) {
				System.out.println();
				System.out.println("Upvotes > " + threadWrapper.getAttribute("data-score"));
				System.out.println("Subreddit > " + threadWrapper.getAttribute("data-subreddit"));
				System.out.println("Thread Title > " + threadWrapper.findElement(By.className("title")).getText());
				String threadCommentsLink = "https://www.reddit.com" + threadWrapper.getAttribute("data-permalink");
				System.out.println("Thread Comments Link > " + threadCommentsLink);
				System.out.println("Thread Link > " + threadWrapper.getAttribute("data-url"));
				System.out.println("=============================================================");
			}
		}
		driver.quit();
	}
}
