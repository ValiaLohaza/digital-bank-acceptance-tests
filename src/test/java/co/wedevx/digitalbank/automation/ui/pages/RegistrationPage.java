package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.utils.MockData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class RegistrationPage extends  BasePageClass{

    MockData mockData = new MockData();

    public RegistrationPage(WebDriver driver) {

        super(driver);
    }


    @FindBy(id = "title")
    private WebElement titleDropDown;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(xpath = "//label[@for='male']//input")
    private WebElement genderM;

    @FindBy(xpath = "//label[@for='female']//input")
    private WebElement genderF;

    @FindBy(id = "dob")
    private WebElement dob;

    @FindBy(id = "ssn")
    private WebElement ssn;

    @FindBy(id = "emailAddress")
    private WebElement emailAddress;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[contains(text(), 'Next')]")
    private WebElement nextButton;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-flat m-b-30 m-t-30']")
    private WebElement submitButton;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "locality")
    private WebElement locality;

    @FindBy(id = "region")
    private WebElement region;

    @FindBy(id = "postalCode")
    private WebElement postalCode;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "homePhone")
    private WebElement homePhone;

    @FindBy(id = "mobilePhone")
    private WebElement mobilePhone;

    @FindBy(name = "workPhone")
    private WebElement workPhone;

    @FindBy(id = "agree-terms")
    private WebElement agreeTermsCheckbox;

    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement messageLabel;


    public void fillOutRegistrationPage(List<Map<String, String>> registrationPageTestDataListOfMap) {

        Select select = new Select(titleDropDown);

        Map<String, String> firstRow = registrationPageTestDataListOfMap.get(0);

        if (firstRow.get("title") != null) {
            select.selectByVisibleText(firstRow.get("title"));
        }
        if (firstRow.get("firstName") != null) {
            firstName.sendKeys(firstRow.get("firstName"));
        }
        if (firstRow.get("lastName") != null) {
            lastName.sendKeys(firstRow.get("lastName"));
        }
        if (firstRow.get("gender") != null) {
            if (firstRow.get("gender").equalsIgnoreCase("M")) {
                genderM.click();
            } else if (firstRow.get("gender").equalsIgnoreCase("F")) {
                genderF.click();
            } else {
                System.out.println("Wrong gender");
            }
        }

        if (firstRow.get("dob") != null) {
            dob.sendKeys(firstRow.get("dob"));
        }

        if (firstRow.get("ssn") != null) {
            ssn.sendKeys(firstRow.get("ssn"));
        }

        if (firstRow.get("email") != null) {
            emailAddress.sendKeys(firstRow.get("email"));
        }

        if (firstRow.get("password") != null) {
            password.sendKeys(firstRow.get("password"));
            confirmPassword.sendKeys(firstRow.get("password"));
        }

        nextButton.click();

        if (address.isDisplayed()) {

            if (firstRow.get("address") != null) {
                address.sendKeys(firstRow.get("address"));

            }

            if (firstRow.get("locality") != null) {
                locality.sendKeys(firstRow.get("locality"));
            }

            if (firstRow.get("region") != null) {
                region.sendKeys(firstRow.get("region"));
            }

            if (firstRow.get("postalCode") != null) {
                postalCode.sendKeys(firstRow.get("postalCode"));
            }

            if (firstRow.get("country") != null) {
                country.sendKeys(firstRow.get("country"));
            }

            if (firstRow.get("homePhone") != null) {
                homePhone.sendKeys(firstRow.get("homePhone"));
            }

            if (firstRow.get("mobilePhone") != null) {
                mobilePhone.sendKeys(firstRow.get("mobilePhone"));
            }

            if (firstRow.get("workPhone") != null) {
                workPhone.sendKeys(firstRow.get("workPhone"));
            }
            if (firstRow.get("termsCheckMark") != null) {
                if (firstRow.get("termsCheckMark").equalsIgnoreCase("true")) {
                    agreeTermsCheckbox.click();
                }
            }

            submitButton.click();
        }
    }

    public String getMessage() {

        return messageLabel.getText().substring(0, messageLabel.getText().lastIndexOf(".") + 1);
    }
    public String getErrorMessage(String fieldName) {

        switch (fieldName.toLowerCase()){
            case "title":
                return titleDropDown.getAttribute("validationMessage");
            case"firstname":
                return firstName.getAttribute("validationMessage");
            case"lastname":
                return lastName.getAttribute("validationMessage");
            case"gender":
                return genderM.getAttribute("validationMessage");
            case"dob":
                return dob.getAttribute("validationMessage");
            case"ssn":
                return ssn.getAttribute("validationMessage");
            case"email":
                return emailAddress.getAttribute("validationMessage");
            case"password":
                return password.getAttribute("validationMessage");
            case"address":
                return address.getAttribute("validationMessage");
            case"locality":
                return locality.getAttribute("validationMessage");
            case"region":
                return region.getAttribute("validationMessage");
            case"postalcode":
                return postalCode.getAttribute("validationMessage");
            case"country":
                return country.getAttribute("validationMessage");
            case"homephone":
                return homePhone.getAttribute("validationMessage");
            case"mobilephone":
                return mobilePhone.getAttribute("validationMessage");
            case"workphone":
                return workPhone.getAttribute("validationMessage");
            case"termscheckmark":
                return agreeTermsCheckbox.getAttribute("validationMessage");
            default:
                return  null;
        }
    }
}
