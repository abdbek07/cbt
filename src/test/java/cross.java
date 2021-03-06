import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class cross {
	RemoteWebDriver driver;
	
	@BeforeSuite
    public void setup() throws MalformedURLException {
        String username = "you%40yourdomain.com";
        String authkey = "yourauthkey";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");    // Automatically pulls the latest version of Chrome
        caps.setCapability("platform", "Windows 10");   // To specify a version, add setCapability("version", "desired version")

        driver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey +"@hub.crossbrowsertesting.com:80/wd/hub"), caps);
    }
	
	@Test
    public void todoTest() {
        System.out.println("Loading Url");
                driver.get("http://crossbrowsertesting.github.io/todo-app.html");

                // maximize the window - DESKTOPS ONLY
                //System.out.println("Maximizing window");
                //driver.manage().window().maximize();

                System.out.println("Checking Box");
                driver.findElement(By.name("todo-4")).click();

                System.out.println("Checking Another Box");
                driver.findElement(By.name("todo-5")).click();

                // If both clicks worked, then the following List should be have length 2
                List<WebElement> elems = driver.findElements(By.className("done-true"));
                // So we'll assert that this is correct.
                AssertJUnit.assertEquals(2, elems.size());

                System.out.println("Entering Text");
                driver.findElement(By.id("todotext")).sendKeys("Run your first Selenium Test");
                driver.findElement(By.id("addbutton")).click();

                // Let's also assert that the todo we added is present in the list.
                String spanText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
                AssertJUnit.assertEquals("Run your first Selenium Test", spanText);

                System.out.println("Archiving old todos");
                driver.findElement(By.linkText("archive")).click();

                // If our archive link worked, then the following list should have length 4.
                elems = driver.findElements(By.className("done-false"));
                // So will assert that this is true as well.
                AssertJUnit.assertEquals(4, elems.size());

                System.out.println("TestFinished");
    }
}
