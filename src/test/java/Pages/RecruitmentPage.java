package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class RecruitmentPage extends BasePage {

    private By recruitmentMenuBtn = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[5]/a[1]");
    private By jobTitleDropdown = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]");

    private By vacancyDropdown = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]");

    // Cambiando el locator de Vacancy a un input de texto directo de acuerdo a las indicaciones
    // Cambiando el locator de Vacancy a un input de texto directo de acuerdo a las indicaciones
    private By vacancyInput = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/input[1]"); // Puede requerir ajustar si es realmente un <input>

    private By hiringManagerDropdown = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]");
    private By searchBtn = By.xpath("//button[@type='submit']");
    
    // Locators for Candidate Name and invalid message
    private By candidateNameInput = By.xpath("//html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]");
    private By invalidCandidateMessage = By.xpath("//html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[1]/div[1]/span[1]");

    // Elements for Vacancies
    private By vacanciesTab = By.xpath("//a[text()='Vacancies']");
    private By addBtn = By.xpath("//button[normalize-space()='Add']");
    private By vacancyNameInput = By.xpath("//label[text()='Vacancy Name']/../following-sibling::div//input");
    private By vacancyJobTitleDropdown = By.xpath("//label[text()='Job Title']/../following-sibling::div//div[@class='oxd-select-text-input']");
    private By hiringManagerInput = By.xpath("//label[text()='Hiring Manager']/../following-sibling::div//input");
    private By hiringManagerDropdownOption = By.xpath("//div[@role='listbox']//span"); // To click the first option in the autocomplete dropdown
    private By saveBtn = By.xpath("//button[@type='submit']");
    private By vacancyRecordInTable = By.xpath("//div[@class='oxd-table-body']//div[contains(text(), 'QA Engineer')]"); // Example locator
    
    // Mensaje "No Records Found"
    private By noRecordsFoundMessage = By.xpath("//span[contains(normalize-space(), 'No Records Found')]");

    // Localizador representativo para validar que la tabla/lista de candidatos cargó
    private By candidatesListTable = By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-card']");

    public RecruitmentPage(WebDriver driver, int seconds) {
        super(driver, seconds);
    }

    public void clickRecruitmentMenu() {
        click(recruitmentMenuBtn);
    }

    public void selectJobTitle(String jobTitle) {
        click(jobTitleDropdown);
        By opcionEspecifica = By.xpath("//div[@role='listbox']//span[text()='" + jobTitle + "']");
        waitForElementVisibility(opcionEspecifica);
        click(opcionEspecifica);
    }

    public void selectFirstJobTitle() {
        click(jobTitleDropdown);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void selectVacancy(String vacancy) {
        // En lugar de seleccionar en un dropdown, se ingresa el texto directamente
        click(vacancyInput);

        Actions action = new Actions(driver);
        action.sendKeys(vacancy).perform();
    }

    public void selectFirstVacancy() {
        click(vacancyDropdown);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void selectHiringManager(String managerName) {
        click(hiringManagerDropdown);
        By opcionEspecifica = By.xpath("//div[@role='listbox']//span[text()='" + managerName + "']");
        waitForElementVisibility(opcionEspecifica);
        click(opcionEspecifica);
    }

    public void selectFirstHiringManager() {
        click(hiringManagerDropdown);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void clickSearch() {
        click(searchBtn);
    }
    
    // Vacancies methods
    public void clickVacanciesTab() {
        click(vacanciesTab);
    }

    public void clickAddButton() {
        click(addBtn);
    }

    public void enterVacancyName(String vacancyName) {
        click(vacancyNameInput);
        By opcionEspecifica = By.xpath("//div[@role='listbox']//span[text()='" + vacancyName + "']");
        waitForElementVisibility(opcionEspecifica);
        click(opcionEspecifica);
    }



    public void selectVacancyJobTitle(String jobTitle) {
        click(vacancyJobTitleDropdown);
        
        // Pausa breve para asegurar que el listado se ha renderizado en la página
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Usar la clase Actions para bajar una posición e "imprimir" ENTER
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    public void enterHiringManager(String managerName) {
        click(hiringManagerInput);
        sendKeys(hiringManagerInput, managerName);
    }

    public void enterOnlyVacancyName(String vacancyName){
        click(vacancyInput);
        sendKeys(vacancyInput, vacancyName);
    }


    public void selectHiringManagerFromDropdown() {
        // Wait for the dropdown to appear and click the first corresponding option
        click(hiringManagerDropdownOption);

    }

    public void clickSave() {
        click(saveBtn);
    }

    public boolean isVacancyInList(String vacancyName) {
        By dynamicVacancyRecord = By.xpath("//div[@class='oxd-table-body']//div[contains(text(), '" + vacancyName + "')]");
        waitForElementVisibility(dynamicVacancyRecord);
        return isElementDisplayed(dynamicVacancyRecord);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return isElementDisplayed(noRecordsFoundMessage);
    }

    public void enterCandidateName(String candidateName) {
        click(candidateNameInput);
        sendKeys(candidateNameInput, candidateName);
    }

    // Validar mensaje de error
    public boolean isInvalidCandidateNameMessageDisplayed() {
        return isElementDisplayed(invalidCandidateMessage);
    }

    public boolean isCandidatesListDisplayed() {
        return isElementDisplayed(candidatesListTable);
    }
}
