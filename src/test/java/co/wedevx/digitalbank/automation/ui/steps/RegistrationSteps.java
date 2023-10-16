package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.pages.RegistrationPage;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.DBUtils.runSQLSelectQuery;
import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {

    RegistrationPage registrationPage = new RegistrationPage(getDriver());
    List<Map<String, Object>> nextId = new ArrayList<>();


    @Given("user navigates to Digital Bank signup page")
    public void user_navigates_to_digital_bank_signup_page() {
        getDriver().get(ConfigReader.getPropertiesValue("digitalbank.registrationpageurl"));

    }
    @Given("Verify that web title is {string}")
    public void verify_that_web_title_is(String title) {

        assertEquals(title, getDriver().getTitle(), "Registration title mismatch");
    }
    @When("user creates an account with following fields")
    public void user_creates_an_account_with_following_fields(List<Map<String, String>> registrationTestDataMap) {

        registrationPage.fillOutRegistrationPage(registrationTestDataMap);

    }
    @Then("User should see a message {string}")
    public void user_should_see_a_message(String expectedSuccessMessage) {

        assertEquals(expectedSuccessMessage, registrationPage.getMessage(), "Message mismatched");

    }
    @Then("User should see {string} required field error message {string}")
    public void user_should_see_required_field_error_message(String fieldName, String expectedErrorMessage) {
        String actualErrorMessage = registrationPage.getErrorMessage(fieldName);
        assertEquals(expectedErrorMessage, actualErrorMessage, "The error message of required " + fieldName + " field mismatch");

    }

    @Then("the following user info should be saved in the db")
    public void theFollowingUserInfoShouldBeSavedInTheDb(List<Map<String, String>> expectedUserProfileInfoInDB) {
        Map<String, String> expectedUserInfo = expectedUserProfileInfoInDB.get(0);
        String queryForUserTable = String.format("select * from users where username = '%s'", expectedUserInfo.get("email"));
        String queryForUserProfile = String.format("select * from user_profile where email_address = '%s'", expectedUserInfo.get("email"));


        List<Map<String, Object>> actualUserinfoList = runSQLSelectQuery(queryForUserTable);
        List<Map<String, Object>> actualUserProfileinfoList = runSQLSelectQuery(queryForUserProfile);

        assertEquals(1, actualUserinfoList.size(), "registration generated unexpected num of users");
        assertEquals(1, actualUserProfileinfoList.size(), "registration generated unexpected num of user profiles");

        Map<String, Object> actualUserInfoMap = actualUserinfoList.get(0);
        Map<String, Object> actualUserProfileInfoMap = actualUserProfileinfoList.get(0);

        assertEquals(expectedUserInfo.get("title"), actualUserProfileInfoMap.get("title"), "registration generated wrong title");
        assertEquals(expectedUserInfo.get("firstName"), actualUserProfileInfoMap.get("first_name"), "registration generated wrong first name");
        assertEquals(expectedUserInfo.get("lastName"), actualUserProfileInfoMap.get("last_name"), "registration generated wrong last name");
        assertEquals(expectedUserInfo.get("gender"), actualUserProfileInfoMap.get("gender"), "registration generated wrong gender");
        //assertEquals(expectedUserInfo.get("dob"), actualUserProfileInfoMap.get("dob"), "registration generated wrong dob");
        assertEquals(expectedUserInfo.get("ssn"), actualUserProfileInfoMap.get("ssn"), "registration generated wrong ssn");
        assertEquals(expectedUserInfo.get("email"), actualUserProfileInfoMap.get("email_address"), "registration generated wrong email");
        assertEquals(expectedUserInfo.get("address"), actualUserProfileInfoMap.get("address"), "registration generated wrong address");
        assertEquals(expectedUserInfo.get("locality"), actualUserProfileInfoMap.get("locality"), "registration generated wrong locality");
        assertEquals(expectedUserInfo.get("region"), actualUserProfileInfoMap.get("region"), "registration generated wrong region");
        assertEquals(expectedUserInfo.get("postalCode"), actualUserProfileInfoMap.get("postal_code"), "registration generated wrong postalCode");
        assertEquals(expectedUserInfo.get("country"), actualUserProfileInfoMap.get("country"), "registration generated wrong country");
        assertEquals(expectedUserInfo.get("homePhone"), actualUserProfileInfoMap.get("home_phone"), "registration generated wrong home Phone");
        assertEquals(expectedUserInfo.get("mobilePhone"), actualUserProfileInfoMap.get("mobile_phone"), "registration generated wrong mobile phone");
        assertEquals(expectedUserInfo.get("workPhone"), actualUserProfileInfoMap.get("work_phone"), "registration generated wrong work phone");

        //validate users table
        assertEquals(expectedUserInfo.get("accountNonExp"), String.valueOf(actualUserInfoMap.get("account_non_expired")), "accountNonExp mismatch user information");
        assertEquals(expectedUserInfo.get("accountNonLocked"), String.valueOf(actualUserInfoMap.get("account_non_locked")), "accountNonLocked mismatch user information");
        assertEquals(expectedUserInfo.get("credentialsNonExp"), String.valueOf(actualUserInfoMap.get("credentials_non_expired")), "credentialsNonExp mismatch user information");
        assertEquals(expectedUserInfo.get("enabled"), String.valueOf(actualUserInfoMap.get("enabled")), "enabled mismatch user information");
        assertEquals(expectedUserInfo.get("email"), String.valueOf(actualUserInfoMap.get("username")), "username mismatch user information");

        assertEquals(nextId.get(0).get("next_val"), actualUserInfoMap.get("id"), "ID mismatch");

        long expectedID = Integer.parseInt(String.valueOf((nextId.get(0).get("next_val"))));
        assertEquals(++expectedID, actualUserProfileInfoMap.get("id"), "ID mismatch");
    }

    @Given("the following user with email {string} is not in the DB")
    public void theFollowingUserWithEmailIsNotInTheDB(String email) {
        String userProfileQuery = String.format("DELETE FROM user_profile WHERE email_address = '%s'", email);
        String usersQuery = String.format("DELETE FROM users WHERE username = '%s'", email);

        String queryToGetNextId = String.format("select * from hibernate_sequence");
        nextId = DBUtils.runSQLSelectQuery(queryToGetNextId);

        DBUtils.runSQLUpdateQuery(userProfileQuery);
        DBUtils.runSQLUpdateQuery(usersQuery);
    }
}
