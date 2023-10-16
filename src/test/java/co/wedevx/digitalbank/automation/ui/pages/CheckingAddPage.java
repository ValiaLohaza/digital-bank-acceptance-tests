package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class CheckingAddPage extends BaseMenuPage{

    public CheckingAddPage(WebDriver driver) {
       super(driver);

    }


    @FindBy(id = "Standard Checking")
    private WebElement standartAccountTypeBtn;
    @FindBy(id = "Interest Checking")
    private  WebElement interestCheckingBtn;

    @FindBy(id = "Individual")
    private WebElement indOwnershipTypeBtn;
    @FindBy(id = "Joint")
    private WebElement jointOwnershipTypeBtn;

    @FindBy(id = "name")
    private WebElement accountNameTxtBtn;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement submitBtn;

    @FindBy(id = "new-account-conf-alert")
    private  WebElement alertConfMessage;

    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        NewCheckingAccountInfo testDataInfo = checkingAccountInfoList.get(0);

        checkingBtn.click();

        newCheckingBtn.click();
        Assertions.assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"), getDriver().getCurrentUrl(), "new checking btn didn't take to the correct URL");

        //ACCOUNT TYPE
        if(testDataInfo.getCheckingAccountType().equalsIgnoreCase("Standard Checking")){
            standartAccountTypeBtn.click();
        }
        else if(testDataInfo.getCheckingAccountType().equalsIgnoreCase("Interest Checking")){
            interestCheckingBtn.click();
        }
        else {
            throw new NoSuchElementException("Invalid selection for account type");
        }
        //ownership
        if(testDataInfo.getAccountOwnership().equalsIgnoreCase("Individual")){
            indOwnershipTypeBtn.click();
        }
        else if(testDataInfo.getAccountOwnership().equalsIgnoreCase("Joint")){
            jointOwnershipTypeBtn.click();
        }
        else{
            throw new NoSuchElementException("Invalid selection for account ownership");
        }

        accountNameTxtBtn.sendKeys(testDataInfo.getAccountName());

        openingBalanceTxtBox.sendKeys(String.valueOf(testDataInfo.getInitialDepositAmount()));

        submitBtn.click();

    }
    public String getActualConfMessage() {
        return alertConfMessage.getText();
    }


}
