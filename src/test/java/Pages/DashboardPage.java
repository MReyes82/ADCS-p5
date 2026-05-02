package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage
{
    private final By clockButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div[1]/div[2]/button");
    private final By noteTextField = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/form/div[2]/div/div/div/div[2]/textarea");
    private final By punchHeader = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/h6");
    private final By submitButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/form/div[3]/button");

    private final LoginPage loginPage;

    public DashboardPage(WebDriver driver, int seconds, LoginPage loginPage)
    {
        super(driver, seconds);
        this.loginPage = loginPage;
    }
    // Obtenemos el texto que contiene el header para saber si tenemos que hacer
    // punch in o punch out de manera dinamica
    public String getPunchHeaderText()
    {
        return getText(punchHeader);
    }
    // setters de los campos del formulario
    public void setNoteTextFieldValue(String value)
    {
        sendKeys(noteTextField, value);
    }
    // Button clickers
    public void clickSubmitButton()
    {
        click(submitButton);
    }
    public void clickClockButton()
    {
        click(clockButton);
    }
    // Util para autenticarse
    public void accessToDashboard()
    {
        loginPage.enterEmail("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
    }
    // Util para esperar a la redireccion a que se suba la tarjeta de asistencia
    public void waitForAttendanceSection(String refreshedValue)
    {
        wait.until(ExpectedConditions.urlContains(refreshedValue));
    }
}
