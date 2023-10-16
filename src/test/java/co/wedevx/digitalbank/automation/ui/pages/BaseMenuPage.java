package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMenuPage extends  BasePageClass{

    public BaseMenuPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "checking-menu")
    protected WebElement checkingBtn;

    @FindBy(id = "new-checking-menu-item")
    protected WebElement newCheckingBtn;

    @FindBy(id = "view-checking-menu-item")
    protected WebElement viewCheckingBtn;

    @FindBy(id= "home-menu-item")
    protected  WebElement homeMenuBtn;

    public  void  goToHomePage() {
        homeMenuBtn.click();
    }

}
