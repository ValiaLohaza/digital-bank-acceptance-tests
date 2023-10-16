package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCheckingAccountPage extends  BaseMenuPage{

    public ViewCheckingAccountPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(id = "new-account-conf-alert")
    private WebElement alertConfMessage;

    @FindBy(xpath = "//div[@id='firstRow']/div")
    private List<WebElement> allFirstRowsDivs;
    @FindBy(xpath = "//table[@id='transactionTable']/tbody/tr")
    private WebElement firstRowOfTransactions;

    public  Map<String, String> getnewlyAddedCheckingTransactionInfoMap() {

        //  //table[@id='transactionTable']/tbody/tr
        List<WebElement> firstRowColunms = firstRowOfTransactions.findElements(By.xpath("td"));

        String actualCategory = firstRowColunms.get(1).getText();
        String actualDescription = firstRowColunms.get(2).getText();
        double actualAmount = Double.parseDouble(firstRowColunms.get(3).getText().substring(1));
        double actualBalance = Double.parseDouble(firstRowColunms.get(4).getText().substring(1));

        Map<String, String> actualResultMap = new HashMap<>();

        actualResultMap.put("actualCategory", firstRowColunms.get(1).getText());
        actualResultMap.put("actualDescription", firstRowColunms.get(2).getText());
        actualResultMap.put("actualAmount", firstRowColunms.get(3).getText().substring(1));
        actualResultMap.put("actualBalance", firstRowColunms.get(4).getText().substring(1));

        return actualResultMap;
    }
    public Map<String, String> newlyAddedAccountInfo() {

        WebElement lastAccountCard = allFirstRowsDivs.get(allFirstRowsDivs.size()-1);
        String actualResult = lastAccountCard.getText();

        Map<String, String> actualResultMap = new HashMap<>();

        actualResultMap.put("actualAccountName", actualResult.substring(0, actualResult.indexOf("\n")));
        actualResultMap.put("actualAccountType", actualResult.substring(actualResult.indexOf("Account:"), actualResult.indexOf("Ownership:")).trim());
        actualResultMap.put("actualOwnership", actualResult.substring(actualResult.indexOf("Ownership:"), actualResult.indexOf("Account Number:")).trim());
        actualResultMap.put("actualAccountNumber", actualResult.substring(actualResult.indexOf("Account Number:"), actualResult.indexOf("Interest Rate:")).trim());
        actualResultMap.put("actualInterestRate", actualResult.substring(actualResult.indexOf("Interest Rate"), actualResult.indexOf("Balance:")).trim());
        actualResultMap.put("actualBalance", actualResult.substring(actualResult.indexOf("Balance:")).trim());

        return actualResultMap;
    }

    public String getActualConfMessage() {
        return alertConfMessage.getText();
    }



}