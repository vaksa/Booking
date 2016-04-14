package MavenBooking.MavenBooking;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class StepDefinitions {

	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver, 10, 1);

	@After
	public void tearDown() {
		driver.close();
	}

	@Given("^Go to \"([^\"]*)\"$")
	public void shouldGoTO(String url) throws Throwable {
		// This step should be define for secial url of web-server or something.
		// Randomly redirect or change language
		// That's why i hardcoded url
		// driver.navigate().to(url);
		driver.navigate().to(
				"http://www.booking.com/index.en-gb.html?label=gen173nr-1DCAEoggJCAlhYSDNiBW5vcmVmaOkBiAEBmAEuuAEGyAEP2AED6AEBqAID;sid=29985deb8e642141e61464291d399385;dcid=12;bb_ltbi=0&sb_price_type=total&");
	}

	@When("^Fill \"([^\"]*)\" in Destination/hotel name field$")
	public void shouldFillInDestinationHotelNameField(String value) throws Throwable {
		driver.findElement(By.id("ss")).sendKeys(value);
		By first = By.xpath(".//*[text()='" + value + "']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(first));
		driver.findElement(first).click();
	}

	@When("^Select \"([^\"]*)\" in \"([^\"]*)\" dropdown$")
	public void shouldSelectInDropdown(String date, String dropdown) throws Throwable {
		driver.findElement(By.name(dropdown)).sendKeys(date);
	}

	@When("^Click \"([^\"]*)\" button$")
	public void shouldClickButtonByText(String buttonText) throws Throwable {
		driver.findElement(By.xpath(".//*[text()='" + buttonText + "']")).click();
	}

	@Then("^Verify that all hotels on a page of results are located in \"([^\"]*)\"$")
	public void verify_that_all_hotels_on_a_page_of_results_are_located_in(String address) throws Throwable {
		List<WebElement> hotels = driver.findElements(By.className("sr_item"));
		for (WebElement hotel : hotels) {
			String hotelAddress = hotel.findElement(By.className("address")).getText();
			Assert.assertTrue(hotelAddress.contains(address));
		}
	}

}
