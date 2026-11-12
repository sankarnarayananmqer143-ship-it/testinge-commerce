
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EcommerceTesting {

    public static void main(String[] args) {

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10));

        try {

            // Open website
            driver.get("https://www.saucedemo.com/");

            driver.manage().window().maximize();

            System.out.println("Website opened successfully");

            // Verify page title
            String title = driver.getTitle();

            if (title.contains("Swag Labs")) {
                System.out.println("Title Test: PASSED");
            } else {
                System.out.println("Title Test: FAILED");
            }

            // Login
            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button"))
                  .click();

            wait.until(ExpectedConditions
                .visibilityOfElementLocated(
                    By.className("inventory_list")));

            System.out.println("Login Test: PASSED");

            // Add product to cart
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))
                  .click();

            System.out.println("Add to Cart Test: PASSED");

            // Open shopping cart
            driver.findElement(By.className("shopping_cart_link"))
                  .click();

            // Verify cart
            WebElement cartItem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.className("inventory_item_name")));

            if (cartItem.getText().contains("Backpack")) {
                System.out.println("Cart Verification: PASSED");
            } else {
                System.out.println("Cart Verification: FAILED");
            }

        } catch (Exception e) {

            System.out.println("Test Failed: " + e.getMessage());

        } finally {

            // Close browser
            driver.quit();

            System.out.println("Browser Closed");
        }
    }
}
