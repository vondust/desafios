package idwall.desafio;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RedditCrawler {

	private static final String redditURL = "https://www.reddit.com";
	private List<String> listaMensagens = new ArrayList<>();

	public List<String> executeCrawling(String subreddit) {

		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/lib/geckodriver.exe");

		WebDriver driver = new FirefoxDriver();
		driver.get(redditURL + "/r/" + subreddit + "/top");

		StringBuilder responseMessage = new StringBuilder();

		List<WebElement> threads = driver.findElements(By.xpath("//*[contains(@class, 'thing')]"));
		for (WebElement threadItem : threads) {
			if (Integer.parseInt(threadItem.getAttribute("data-score")) >= 5000 && !threadItem.getAttribute("data-subreddit").equalsIgnoreCase("promos")) {
				responseMessage.append("---\n").append("upvotes: " + threadItem.getAttribute("data-score")).append("\n")
						.append("subreddit: " + threadItem.getAttribute("data-subreddit")).append("\n")
						.append("thread title: " + threadItem.findElement(By.className("title")).getText()).append("\n")
						.append("thread link: " + threadItem.getAttribute("data-url")).append("\n")
						.append("thread comment's link: " + redditURL + threadItem.getAttribute("data-permalink")).append("\n");

				System.out.println(responseMessage.toString());
				listaMensagens.add(responseMessage.toString());
				responseMessage = new StringBuilder();
			}
		}

		driver.quit();

		return listaMensagens;
	}

}
