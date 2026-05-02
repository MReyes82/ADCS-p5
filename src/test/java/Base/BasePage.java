package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, int seconds)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // Encontrar element con wait explicito (ToBeClickable))
    protected WebElement findElement(By locator)
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    // Mandar señal de click al elemento (usando wait explicito)
    protected void click(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    // Mandar texto al elemento
    protected void sendKeys(By locator, String value)
    {
        findElement(locator).sendKeys(value);
    }
    // Obtener el texto de un elemento
    protected String getText(By locator)
    {
        return findElement(locator).getText();
    }
    // Esperar a que el elemento sea visible
    protected void waitForElementVisibility(By locator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Revisa si el elemento se encuentra actualmente disponible
    protected boolean isElementDisplayed(By locator)
    {
        try
        {
            // Si es verdadero regresarlo
            return findElement(locator).isDisplayed();
        }
        // Si no, catch a la excepcion y regresar falso
        catch (Exception e)
        {
            return false;
        }
    }
}
