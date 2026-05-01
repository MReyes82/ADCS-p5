package Pages;
import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage
{
    // Definir los locators de los elementos de la página de login
    private final By emailField = By.name("username");;
    private final By passwordField = By.name("password");
    private final By loginButton = By.className("oxd-button oxd-button--medium oxd-button--main orangehrm-login-button");;

    public LoginPage(WebDriver driver, int seconds)
    {
        super(driver,  seconds);
    }

    public void enterEmail(String email)
    {
        sendKeys(emailField, email);
    }

    public void enterPassword(String password)
    {
        sendKeys(passwordField, password);
    }
    // Mandar señal de click al boton de login, usando el método click de la clase base
    // el cual utiliza wait.until con la cantidad de segundos definida en el constructor de esta clase
    public void clickLoginButton()
    {
        click(loginButton);
    }
}
