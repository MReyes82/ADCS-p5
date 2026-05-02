package Pages;
import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage
{
    // Definir los locators de los elementos de la página de login
    private final By emailField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");;

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
