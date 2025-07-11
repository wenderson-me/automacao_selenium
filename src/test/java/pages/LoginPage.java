package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.BaseTest;

public class LoginPage extends BaseTest {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".app_logo")
    private WebElement appLogo;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public boolean isLoginSuccessful() {
        try {
            return appLogo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clearFields() {
        usernameInput.clear();
        passwordInput.clear();
    }

    public boolean isUsernameFieldVisible() {
        return usernameInput.isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return passwordInput.isDisplayed();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
