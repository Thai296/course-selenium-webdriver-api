package TestNG;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class NewTest {
	WebDriver driver;
	Random rnd;
	int wait = 1500;
	
	// scroll windows
	private void scrollByWindows(WebElement element) throws InterruptedException {
//cach 1 move to element
		
//		Actions actions = new Actions(driver);
//		actions.moveToElement(element);
//		Thread.sleep(wait);
//		actions.perform();
// cach 2 scroll window
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		1. scrolling by pixel
//		js.executeScript("window.scrollBy(0,1000)");
		// 2. scrolling by element middle screen
		js.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", inline: \"nearest\"});",element);
		// 3. scroll page till bottom
//		js.executeScript("window.scrollTo(0,document.body.scrollHieght)");

	}
//tao phan tu highlight khi chon
	private void highlightElement(WebElement element) throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
	}
	private void click(WebElement webElement) throws InterruptedException {
		scrollByWindows(webElement);
		highlightElement(webElement);
		webElement.click();

		return;
	}
	private String randomEmail() throws InterruptedException {

		String random = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * random.length());
			salt.append(random.charAt(index));
		}
		String mail = salt.toString();
//		System.out.println("Selected value is: " + mail);
		Thread.sleep(wait);
		return mail;
	}
	//random name
	private String randomName() throws InterruptedException {

		String random = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		rnd = new Random();
		while (salt.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * random.length());
			salt.append(random.charAt(index));
		}
		String name = salt.toString();
//		System.out.println("Selected value is: " + name);
		Thread.sleep(wait);
		return name;
	}
//random phone
	private String randomPhone() throws InterruptedException {
	
		String random = "1234567890";
		StringBuilder salt = new StringBuilder();
		rnd = new Random();
		while (salt.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * random.length());
			salt.append(random.charAt(index));
		}
		String phone = salt.toString();
//		System.out.println("Selected value is: " + phone);
		Thread.sleep(wait);
		return phone;
	}
//random dropdown
	private void selectRandomDropdown(WebElement element) throws InterruptedException {
		// sử dụng Class Select để lấy số lượng
		scrollByWindows(element);
		Select selectDay = new Select(element);
		List<WebElement> dayList = selectDay.getOptions();
		// lấy vị trí của giá trị
		int sizes = dayList.size();
		// sủ dụng random đẩy lấy giá trị
		rnd = new Random();
		int sizeselect = rnd.nextInt(sizes);
		// chọn giá trị từ dropdown list
		selectDay.selectByIndex(sizeselect);
		System.out.println("Selected value is: " + element.getAttribute("value"));
		Thread.sleep(wait);
		return ;
	}
	@BeforeSuite(description = "Connecting")
	public void BeforeSuite() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\TraOLong\\eclipse-workspace\\TestMaven2\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@BeforeTest(description = "refresh the web page")
	public void BeforeTest() {
		// refresh the web page to avoid interference
		driver.navigate().refresh();
	}
	@Test(description = "Test Body")
	public void FirstTest() throws InterruptedException {
//Home page		
	
		click(driver.findElement(By.xpath("//a[text()='Women']")));
		click(driver.findElement(By.cssSelector("#subcategories [title='Tops'] > img")));
		click(driver.findElement(By.cssSelector("#subcategories [title='T-shirts'] > img")));
		click(driver.findElement(By.cssSelector(".icon-th-list")));
		click(driver.findElement(By.cssSelector("[title='Add to cart'] > span")));
		click(driver.findElement(By.cssSelector("[title='Continue shopping'] > span")));
		click(driver.findElement(By.cssSelector("[title='Add to cart'] > span")));
		Thread.sleep(wait);
		click(driver.findElement(By.cssSelector("[title='Proceed to checkout'] > span")));
//Order page
		click(driver.findElement(By.cssSelector(".icon-plus")));
		click(driver.findElement(By.cssSelector(".standard-checkout > span")));
		WebElement txtAddEmail = driver.findElement(By.cssSelector("#email_create"));
		scrollByWindows(txtAddEmail);
		highlightElement(txtAddEmail);
		txtAddEmail.sendKeys(randomEmail() + "@gmail.com");		
		System.out.println("Email: " + txtAddEmail.getAttribute("value"));
		
		click(driver.findElement(By.cssSelector("#SubmitCreate")));
//Create account web page 
		click(driver.findElement(By.cssSelector("#id_gender1")));
		WebElement txtFrName = driver.findElement(By.cssSelector("#customer_firstname"));
		scrollByWindows(txtFrName);
		highlightElement(txtFrName);
		txtFrName.sendKeys(randomName());
		System.out.println("FristName: " + txtFrName.getAttribute("value"));
		
		WebElement txtLastName = driver.findElement(By.cssSelector("#customer_lastname"));
		scrollByWindows(txtLastName);
		highlightElement(txtLastName);
		txtLastName.sendKeys(randomName());
		System.out.println("LastName: " + txtLastName.getAttribute("value"));
		
		WebElement txtPass = driver.findElement(By.cssSelector("#passwd"));
		scrollByWindows(txtPass);
		highlightElement(txtPass);
		txtPass.sendKeys(randomEmail());
		System.out.println("Password: " + txtPass.getAttribute("value"));
		
		selectRandomDropdown(driver.findElement(By.cssSelector("#days")));
		selectRandomDropdown(driver.findElement(By.cssSelector("#months")));
		selectRandomDropdown(driver.findElement(By.cssSelector("#years")));
		
		
		driver.findElement(By.cssSelector("#newsletter")).click();
		driver.findElement(By.cssSelector("#optin")).click();
		
		scrollByWindows(driver.findElement(By.cssSelector("#firstname")));
		driver.findElement(By.cssSelector("#firstname")).sendKeys(randomName());
		
		scrollByWindows(driver.findElement(By.cssSelector("#lastname")));
		driver.findElement(By.cssSelector("#lastname")).sendKeys(randomName());
		
		scrollByWindows(driver.findElement(By.cssSelector("#company")));
		driver.findElement(By.cssSelector("#company")).sendKeys(randomName());
		
		scrollByWindows(driver.findElement(By.cssSelector("#address1")));
		driver.findElement(By.cssSelector("#address1")).sendKeys(randomName());
		
		scrollByWindows(driver.findElement(By.cssSelector("#city")));
		driver.findElement(By.cssSelector("#city")).sendKeys(randomName());
		
		selectRandomDropdown(driver.findElement(By.cssSelector("#id_state")));
		
		scrollByWindows(driver.findElement(By.cssSelector("#postcode")));
		driver.findElement(By.cssSelector("#postcode")).sendKeys(randomPhone());
		
		scrollByWindows(driver.findElement(By.cssSelector("#phone")));
		driver.findElement(By.cssSelector("#phone")).sendKeys(randomPhone());
		
		scrollByWindows(driver.findElement(By.cssSelector("#phone_mobile")));
		driver.findElement(By.cssSelector("#phone_mobile")).sendKeys(randomPhone());
		
		scrollByWindows(driver.findElement(By.cssSelector("#alias")));
		driver.findElement(By.cssSelector("#alias")).sendKeys(randomEmail());
		
		scrollByWindows(driver.findElement(By.cssSelector("#submitAccount > span")));
		driver.findElement(By.cssSelector("#submitAccount > span")).click();
//Address web page 
		click(driver.findElement(By.cssSelector("[name='processAddress'] > span")));
//Shipping web page 
		click(driver.findElement(By.cssSelector("#cgv")));
		click(driver.findElement(By.cssSelector("[name='processCarrier'] > span")));
//Payment website
		click(driver.findElement(By.cssSelector("[title='Previous']")));
		click(driver.findElement(By.cssSelector("#cgv")));
		click(driver.findElement(By.cssSelector("[name='processCarrier'] > span")));
		click(driver.findElement(By.cssSelector("[title='Pay by check.']")));
		click(driver.findElement(By.cssSelector("#cart_navigation .button > span")));
//Home page
		click(driver.findElement(By.cssSelector(".logo")));
		click(driver.findElement(By.cssSelector("[title='Log me out']")));
	}
	@AfterSuite(description = "The end!")
	public void AfterSuite() throws InterruptedException {

		Thread.sleep(10000);
		driver.quit();// exit web page
	}

}
