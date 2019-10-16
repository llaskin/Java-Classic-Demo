import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasicDemo {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");

        // Use Chrome browser
        WebDriver driver = new ChromeDriver();


        // Initialize the eyes SDK and set your private API key.
        Eyes eyes = new Eyes();
//
//		// Set the API key from the env variable. Please read the "Important Note"
//		// section above.
        eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        eyes.setStitchMode(StitchMode.CSS);
        eyes.setForceFullPageScreenshot(true);


        try {

            // Start the test by setting AUT's name, test name and viewport size (width X
            // height)
            eyes.open(driver, "Applitools  App", "Jenkins Smoke Test", new RectangleSize(1200,800));
//			eyes.open(driver, "Demo App", "Smoke Test");

            //To see visual bugs, change the above URL to:
            driver.get("https://www.applitools.com/");
            // Visual checkpoint #1 - Check the login page
            eyes.checkWindow("Main screen");

//			//Visual Region Checkpoint
            eyes.checkRegion(By.cssSelector(".navbar"), "Primary Nav");

            // End the test.
            TestResults results = eyes.close();
            System.out.println(results);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            // Close the browser.
            driver.quit();

            // If the test was aborted before eyes.close was called, ends the test as
            // aborted.
            eyes.abortIfNotClosed();

            // End main test
            System.exit(0);
        }

    }
}