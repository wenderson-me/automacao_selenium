package tests;

    import org.testng.annotations.BeforeMethod;
    import org.testng.annotations.Test;
    import org.testng.Assert;
    import pages.LoginPage;
    import base.BaseTest;

    public class LoginTest extends BaseTest {

        private LoginPage loginPage;
        private static final String URL = "https://www.saucedemo.com/v1/";
        private static final String VALID_USERNAME = "standard_user";
        private static final String VALID_PASSWORD = "secret_sauce";
        private static final String INVALID_PASSWORD = "wrong_password";

        @BeforeMethod
        @Override
        public void setUp() {
            super.setUp();
            driver.get(URL);
            loginPage = new LoginPage(driver);
        }

        @Test
        public void testSuccessfulLogin() {
            loginPage.login(VALID_USERNAME, VALID_PASSWORD);
            Assert.assertTrue(loginPage.isLoginSuccessful(), "Login foi bem-sucedido");
        }

      @Test
        public void testFailedLogin() {
            loginPage.login(VALID_USERNAME, INVALID_PASSWORD);
            Assert.assertFalse(loginPage.isLoginSuccessful(), "Login falhou como esperado");
            String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
            expectedErrorMessage = loginPage.getErrorMessage();
            Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage,
                "A mensagem de erro não corresponde ao esperado");
        }

        @Test
        public void testLoginFieldsVisibility() {
            Assert.assertTrue(loginPage.isUsernameFieldVisible(), "Campo de usuário está visível");
            Assert.assertTrue(loginPage.isPasswordFieldVisible(), "Campo de senha está visível");
        }

        @Test
        public void testClearFields() {
            loginPage.clearFields();
            Assert.assertTrue(loginPage.isUsernameFieldVisible(), "Campo de usuário está visível após limpeza");
            Assert.assertTrue(loginPage.isPasswordFieldVisible(), "Campo de senha está visível após limpeza");
        }
    }