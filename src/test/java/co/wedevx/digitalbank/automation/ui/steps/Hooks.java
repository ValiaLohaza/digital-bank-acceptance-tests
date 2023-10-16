package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;

import static co.wedevx.digitalbank.automation.ui.utils.DBUtils.establishConnection;
import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {

    @Before("@Registration")
    public void clearTheDBForRegistration() {
        establishConnection();

    }


    @Before("not @Registration")
    public void the_user_is_on_dbank_homepage() {
        getDriver().manage().window().maximize();
        getDriver().get("https://dbank-qa.wedevx.co/bank/login");

    }

    @After("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario) {
        Driver.takeScreenshot(scenario);
        Driver.closeDriver();

    }
    @After()
    public void  closeConnectionToDB() {
        DBUtils.closeConnection();

    }
}
