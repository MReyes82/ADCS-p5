package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage
{
    // Locators de la pagina PIM (Personal Information Management)
    private final By PIMButton = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a");
    private final By employeeId = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input");
    private final By searchButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private final By employeesList = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]");
    private final By deleteInlineButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div/div[1]/div[2]/div/div/button[2]");
    private final By editInlineButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div/div[1]/div[2]/div/div/button[1]");
    private final By deleteConfirmationButton = By.xpath("/html/body/div/div[3]/div/div/div/div[3]/button[2]");
    private final By SaveButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button"); // Seccion de detalles personales al editar un empleado
    private final By nationalityDropDown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div"); // Boton de "Save" de la seccion de detalles personales al editar un empleado
    private final By employeeListButton = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[2]/a");


    private final LoginPage loginPage;

    public PIMPage(WebDriver driver, int seconds, LoginPage loginPage)
    {
        super(driver, seconds);
        this.loginPage = loginPage;
    }

    // Setters de los campos de busqueda
    public void setEmployeeIdValue(String value)
    {
        sendKeys(employeeId, value);
    }
    public void setNationalityDropDownValue(String value)
    {
        click(nationalityDropDown);
        for (var i = 0 ; i < 3 ; i++)
            findElement(nationalityDropDown).sendKeys(Keys.ARROW_DOWN);
        findElement(nationalityDropDown).sendKeys(Keys.ENTER);
    }
    // Button clickers
    public void clickSearchButton()
    {
        click(searchButton);
    }

    public void clickEmployeeListButton()
    {
        click(employeeListButton);
    }

    public void clickDeleteButton()
    {
        click(deleteInlineButton);
    }

    public void clickEditButton()
    {
        click(editInlineButton);
    }

    public void clickDeleteConfirmationButton()
    {
        click(deleteConfirmationButton);
    }

    public void clickSaveButton()
    {
        click(SaveButton);
    }
    // Util para ir a la seccion deseada
    public void accessToPIM()
    {
        loginPage.enterEmail("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        click(PIMButton);
    }
}
