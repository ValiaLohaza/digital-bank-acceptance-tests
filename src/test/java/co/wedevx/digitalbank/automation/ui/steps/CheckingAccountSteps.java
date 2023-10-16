package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.TransactionClass;
import co.wedevx.digitalbank.automation.ui.pages.CheckingAddPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class CheckingAccountSteps {

    WebDriver driver = Driver.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final CheckingAddPage checkingAddPage = new CheckingAddPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);


    @Given("the user enters login {string} and password {string}")
    public void the_user_enters_login_and_password(String username, String password) throws InterruptedException {
        Thread.sleep(2000);
        loginPage.login(username, password);

    }

    @When("the user creates a new Checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) throws InterruptedException {
        checkingAddPage.createNewChecking(checkingAccountInfoList);

    }
    @Then("the user should see the message {string} message")
    public void the_user_should_see_the_message_message(String expectedAlert) throws InterruptedException {

        expectedAlert = "Confirmation " + expectedAlert + "\n×";
        Assertions.assertEquals(expectedAlert, viewCheckingAccountPage.getActualConfMessage());

    }
    @Then("the user should see newly added account cart")
    public void the_user_should_see_newly_added_account_cart(List<AccountCard> accountCardList) {

        Map<String, String> actualResultMap = viewCheckingAccountPage.newlyAddedAccountInfo();

        AccountCard expectedResult = accountCardList.get(0);

        Assertions.assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
        Assertions.assertEquals("Account: " + expectedResult.getAccountType(), actualResultMap.get("actualAccountType"));
        Assertions.assertEquals("Ownership: " + expectedResult.getOwnership(), actualResultMap.get("actualOwnership"));
        //Assertions.assertEquals("Account Number:" + expectedResult.getAccountNumber(), actualAccountNumber);
        Assertions.assertEquals("Interest Rate: " + expectedResult.getInterestRate(), actualResultMap.get("actualInterestRate"));
        Assertions.assertEquals("Balance: " + expectedResult.getBalance(), actualResultMap.get("actualBalance"));


    }
    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<TransactionClass> expectedTransactions) {

       Map<String, String> actualResultMap = viewCheckingAccountPage.getnewlyAddedCheckingTransactionInfoMap();
        TransactionClass expectedTransaction = expectedTransactions.get(0);

        Assertions.assertEquals(expectedTransaction.getCategory(), actualResultMap.get("actualCategory"), "Transaction category mismatched");
        //   Assertions.assertEquals(expectedTransaction.getDescription(), actualDescription, "Transaction description mismatched");
        Assertions.assertEquals(expectedTransaction.getAmount(), Double.parseDouble(actualResultMap.get("actualAmount")), "Transaction amount mismatched");
        Assertions.assertEquals(expectedTransaction.getBalance(), Double.parseDouble(actualResultMap.get("actualBalance")), "Transaction balance mismatched");


       // viewCheckingAccountPage.goToHomePage();
    }
}
