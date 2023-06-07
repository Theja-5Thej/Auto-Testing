import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class App {
    public static void main(String[] args) throws Exception {
 
        System.setProperty("webdriver.chrome.driver", "F:\\java projec\\First_Selenium\\src\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.amazon.in/");
		driver.manage().window().maximize();
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("dress");
        searchBox.submit();
        driver.findElement(By.linkText("Dresses for Women || Western Dresses for Women || Dress for Women || Dresses (514-516)")).click();
		Set<String> windowHandl= driver.getWindowHandles();
		Iterator<String> iterate = windowHandl.iterator();
		String parentWin= iterate.next();
		String childWin= iterate.next();
		driver.switchTo().window(childWin);
		driver.findElement(By.className("a-dropdown-container")).click();
		WebElement w = driver.findElement(By.id("native_dropdown_selected_size_name"));
		Select dropDown = new Select(w);
		dropDown.selectByIndex(4);
		driver.findElement(By.xpath("//input[@name='submit.add-to-cart']")).click();
		driver.findElement(By.id("nav-cart-count")).click();
	
		float  actualPrice = Float.parseFloat( driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[5]/div/div[1]/div[1]/div/form/div/div/div[1]/span[2]/span")).getText());
		float expectedPrice = 599f ;
		WebElement qty = driver.findElement(By.className("a-dropdown-prompt"));
		String actualQty = qty.getText();
		
		
		String expectedQty = "1";
	
		if(actualPrice==expectedPrice){
			System.out.println("The price  is verified successfully");
		}else{
			System.out.println("The price  is  not verified!!!!!! ");
		}
		if(actualQty.equals(expectedQty)){
			System.out.println("The  quantity is verified successfully");
		}else{
			System.out.println("The quantity is not verified !!!1");
		}

		driver.findElement(By.name("proceedToRetailCheckout")).click();
		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f, new File("C:\\Users\\Theja\\Downloads\\SampleScreenShot\\amazon.jpg"));
       
        driver.quit();

    }
}
