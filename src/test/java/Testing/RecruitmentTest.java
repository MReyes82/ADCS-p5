package Testing;

import Pages.LoginPage;
import Pages.RecruitmentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecruitmentTest
{
    private WebDriver driver;
    private RecruitmentPage recruitmentPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        
        loginPage = new LoginPage(driver, 10);
        recruitmentPage = new RecruitmentPage(driver, 10);
        
        // Precondición: El usuario se encuentra dentro del sistema.
        loginPage.enterEmail("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
    }

    @Test
    public void testRecruitmentCandidatesSearch_P_REC_CANDIDATES_01P()
    {
        // Datos de prueba
        String jobTitle = "Payroll Administrator";
        String vacancy = "test";
        String hiringManager = "Thomas D. Hart";

        recruitmentPage.clickRecruitmentMenu();
        //recruitmentPage.selectJobTitle(jobTitle);
        recruitmentPage.selectVacancy(vacancy);
        //recruitmentPage.selectHiringManager(hiringManager);
        recruitmentPage.clickSearch();

        // Resultado esperado: 
        // Se muestra el listado en la parte inferior de la pagina mostrando las 
        // solicitudes que tiene el reclutador en dicha vacante.
        boolean isListDisplayed = recruitmentPage.isCandidatesListDisplayed();
        
        Assert.assertTrue(isListDisplayed, "La lista de candidatos para la vacante no se mostró correctamente.");
    }

    @Test
    public void testRecruitmentCandidatesSearchInvalidName_P_REC_CANDIDATES_01N()
    {

        recruitmentPage.clickRecruitmentMenu();

        String candidateName = "Jon Dou";

        recruitmentPage.enterCandidateName(candidateName);
        recruitmentPage.clickSearch();

        // Resultado esperado:
        // El sistema muestra que no existe dicho nombre o no es correcto (Mensaje "Invalid" / borde rojo).
        boolean isInvalidMessageDisplayed = recruitmentPage.isInvalidCandidateNameMessageDisplayed();

        Assert.assertTrue(isInvalidMessageDisplayed, "El feedback ('Invalid') para un nombre de candidato incorrecto no se mostró.");
    }

    @Test
    public void testRecruitmentAddVacancy_P_REC_VACANCIES_01P()
    {
        // Precondiciones: El usuario se encuentra dentro del modulo de Recruitment.
        recruitmentPage.clickRecruitmentMenu();

        // Datos de prueba
        String vacancyName = "Senior QA Lead";
        String jobTitle = "QA Engineer";
        String hiringManager = "Ranga  Akunuri";

        recruitmentPage.clickVacanciesTab();
        recruitmentPage.clickAddButton();
        recruitmentPage.enterVacancyName(vacancyName);
        recruitmentPage.selectVacancyJobTitle(jobTitle);
        recruitmentPage.enterHiringManager(hiringManager);
        recruitmentPage.selectHiringManagerFromDropdown();
        recruitmentPage.clickSave();
        recruitmentPage.clickVacanciesTab();

        // Resultado esperado: Se muestra la lista de vacantes con la nueva vacante.
        boolean isVacancyAdded = recruitmentPage.isVacancyInList(vacancyName);

        Assert.assertTrue(isVacancyAdded, "La nueva vacante agregada no se mostró en la lista de vacantes.");
    }

    @Test
    public void testRecruitmentSearchVacanciesNoRecords_P_REC_VACANCIES_01N()
    {
        // Precondiciones: El usuario se encuentra dentro del modulo de Recruitment (Vacancies).
        recruitmentPage.clickRecruitmentMenu();
        recruitmentPage.clickVacanciesTab();

        // Datos de prueba
        String jobTitle = "Automaton Tester";
        String vacancy = "Junior Account Assistant";
        // El test indica: Hiring Manager: Primera opcion de dropdown.
        // Simularemos llamando al método genérico.
        String hiringManager = "Peter Mac Anderson";

        recruitmentPage.selectFirstJobTitle();
        recruitmentPage.selectFirstVacancy();
        recruitmentPage.selectFirstHiringManager();

        recruitmentPage.clickSearch();

        // Resultado esperado:
        // No se debe mostrar ningun resultado en la lista y aparece una leyenda "No records found".
        boolean isNoRecords = recruitmentPage.isNoRecordsFoundDisplayed();

        Assert.assertTrue(isNoRecords, "No se mostró la leyenda 'No Records Found'.");
    }

    @AfterMethod
    public void tearDown()
    {
        if (driver != null) {
            try {
                // Pausa de 3 segundos antes de cerrar el navegador para poder observar el resultado
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}
