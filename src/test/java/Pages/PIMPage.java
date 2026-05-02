package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class PIMPage extends BasePage
{
    // Locators de la pagina PIM (Personal Information Management)

    // Boton para acceder a la seccion de PIM en el menu lateral
    private final By PIMButton = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a");
    // Campo del formulario "Employee Information" correspondiente al Employee ID
    private final By employeeId = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input");
    
    // Campo de "Employee Name"
    private final By employeeNameInput = By.xpath("//label[normalize-space()='Employee Name']/../following-sibling::div//input");
    // Primera opcion del autocompletado en dropdown
    private final By autocompleteFirstOption = By.xpath("//div[@role='listbox']//span");
    // Filas resultantes de la tabla
    private final By resultTableRows = By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']");

    // Boton de busqueda del formulario "Employee Information"
    private final By searchButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    // Boton de reset del formulario "Employee Information"
    private final By resetButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]");
    // Contenedor de la caja de empleados (tabla de registros de empleados)
    private final By employeesList = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]");
    // Checkbox del id de empleado en los resultados de busqueda, se asume que el primer resultado es el correcto ya que se esta buscando por id
    private final By idCheckbox = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[1]/div/div[1]/div/label/span");
    // boton de eliminar que aparece al seleccionar un resultado de la busqueda
    private final By deleteButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/button");
    // Boton de confirmacion de eliminacion que aparece al hacer click en el boton de eliminar
    private final By deleteConfirmationButton = By.xpath("/html/body/div/div[3]/div/div/div/div[3]/button[2]");
    // Primer elemento de la tabla de registros, al hacer click se entra al menu de edicion
    private final By editButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div");
    // Boton de "Save" de la seccion de detalles personales al editar un empleado
    private final By SaveButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button");
    // campo dropdown del formulario "Personal information"
    private final By nationalityDropDown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[1]");
    // Boton de "Employee list" de la barra superior de la seccion de PIM
    private final By employeeListButton = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[2]/a");
    
    // Locators para la funcionalidad de Add Employee
    private final By addEmployeeTab = By.xpath("//a[text()='Add Employee']");
    private final By firstNameInput = By.xpath("//input[@name='firstName']");
    private final By lastNameInput = By.xpath("//input[@name='lastName']");
    private final By addEmployeeIdInput = By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");
    private final By saveAddEmployeeButton = By.xpath("//button[@type='submit']");

    // Texto que aparece al realizar una busqueda y no encontrar resultados
    private final By noRecordsText = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span");


    private final LoginPage loginPage;

    public PIMPage(WebDriver driver, int seconds, LoginPage loginPage)
    {
        super(driver, seconds);
        this.loginPage = loginPage;
    }

    // Setters de los campos de busqueda
    public void setEmployeeIdValue(String value)
    {
        click(employeeId);
        sendKeys(employeeId, value);
    }
    
    public void setEmployeeNameValue(String name)
    {
        click(employeeNameInput);
        sendKeys(employeeNameInput, name);
    }

    public void selectFirstEmployeeNameOption()
    {
        waitForElementVisibility(autocompleteFirstOption);
        click(autocompleteFirstOption);
    }
    
    public int getResultTableRowsCount()
    {
        try {
            // Un pequeño respiro para dar tiempo a que los items se rendericen en el DOM tras quitar el loading
            Thread.sleep(1500);
            waitForElementVisibility(resultTableRows);
            return driver.findElements(resultTableRows).size();
        } catch (Exception e) {
            return 0;
        }
    }

    // Navegamos hacia abajo en el dropdown de nacionalidad 4 veces y luego le damos enter para seleccionar
    // la opcion especificada en la matriz
    public void setNationalityDropDownValue()
    {
        click(nationalityDropDown);
        for (var i = 0 ; i < 4 ; i++)
            findElement(nationalityDropDown).sendKeys(Keys.ARROW_DOWN);
        findElement(nationalityDropDown).sendKeys(Keys.ENTER);
    }

    // Setters para Add Employee
    public void clickAddEmployeeTab() {
        click(addEmployeeTab);
    }

    public void setFirstNameValue(String firstName) {
        click(firstNameInput);
        sendKeys(firstNameInput, firstName);
    }

    public void setLastNameValue(String lastName) {
        click(lastNameInput);
        sendKeys(lastNameInput, lastName);
    }

    public void setAddEmployeeIdValue(String id) {
        // En OrangeHRM el campo ID de empleado puede autocompletarse, procedemos a borrarlo antes de escribir el nuevo
        click(addEmployeeIdInput);
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        sendKeys(addEmployeeIdInput, id);
    }

    public void clickSaveAddEmployeeButton() {
        click(saveAddEmployeeButton);
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
    public void clickSearchResultButton()
    {
        click(idCheckbox);
    }
    public void clickDeleteButton()
    {
        click(deleteButton);
    }

    public void clickEditButton()
    {
        click(editButton);
    }

    public void clickDeleteConfirmationButton()
    {
        click(deleteConfirmationButton);
    }

    public void clickSaveButton()
    {
        click(SaveButton);
    }

    public void clickResetButton()
    {
        click(resetButton);
    }

    public String getNoRecordsText()
    {
        return getText(noRecordsText);
    }

    public String getNationalityDropDownValue()
    {
        return getText(nationalityDropDown);
    }
    // Util para ir a la seccion deseada
    public void accessToPIM()
    {
        loginPage.enterEmail("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        click(PIMButton);
    }


    // Util para esperar a que el container del employee list vuelva a aparecer
    // (Significa que el servidor termino de procesar la peticion)
    public void waitForEmployeeListContainer()
    {
        waitForElementVisibility(employeesList);
    }
    // Util para esperar a que la pagina se refresque
    // tras editar los detalles personales de un empleado
    // (Significa que el servidor termino de procesar la peticion)
    public void waitForPersonalDetailsRefresh()
    {
        waitForElementVisibility(nationalityDropDown);
    }
}
