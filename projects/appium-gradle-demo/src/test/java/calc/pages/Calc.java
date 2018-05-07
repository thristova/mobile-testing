package calc.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Calculator app abstraction.
 * Can also look at this class as page object for calculator home page.
 */
public class Calc {
    private AppiumDriver driver;

    /**
     * Init calculator object (calc app home page).
     *
     * @param driver appium driver.
     */
    public Calc(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Click number on the calculator.
     *
     * @param digit Digit enum value.
     */
    public void clickNumber(Digit digit) {
        String id = "net.ludeke.calculator:id/digit" + digit;
        MobileElement num = (MobileElement) this.driver.findElementById(id);
        num.click();
        System.out.println("Click digit \"" + digit + "\" button.");
    }

    /**
     * Perform operation.
     *
     * @param operation Operation enum value.
     */
    public void performOperation(Operation operation) {
        String id = String.format("net.ludeke.calculator:id/%s", operation);
        MobileElement op = (MobileElement) this.driver.findElement(By.id(id));
        op.click();
        System.out.println("Click " + operation.name() + " button.");
    }

    /**
     * Clean calculator.
     */
    public void clean() {
        List<WebElement> buttons = this.driver
                .findElementsById("net.ludeke.calculator:id/clear");
        if (buttons.size() > 0) {
            buttons.get(0).click();
        } else {
            WebElement button = this.driver.findElementById("net.ludeke.calculator:id/del");
            while (!this.getResult().equalsIgnoreCase("")) {
                button.click();
            }
        }
        System.out.println("Clean the calculator.");
    }

    /**
     * Get current result.
     *
     * @return Current result as String (trimmed).
     */
    public String getResult() {
        return this.driver
                .findElement(By.className("android.widget.EditText"))
                .getText().trim();
    }
}
