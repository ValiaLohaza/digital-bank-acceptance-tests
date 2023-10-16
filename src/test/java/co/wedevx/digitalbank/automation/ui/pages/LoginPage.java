package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePageClass{

    public LoginPage(WebDriver driver) {
        super(driver);


    }
    @FindBy(id = "username")
    private WebElement usernameTxtBox;

    @FindBy(id = "password")
    private  WebElement passwordTxtBox;

    @FindBy(id = "remember me")
    private  WebElement rememberMeCheckBox;

    @FindBy(xpath = "//button")
    private WebElement submitBtn;

    @FindBy(xpath = "//a[contains(text(), ' Sign Up Here')]")
    private  WebElement signUpHereBtn;

    //create action method
    public void login(String username, String password) {

        usernameTxtBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        submitBtn.click();

    }

}
