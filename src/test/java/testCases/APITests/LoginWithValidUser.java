package testCases.APITests;

import com.fasterxml.jackson.core.JsonProcessingException;
import objectModelsForAPIs.LoginRequestModel;
import objectModelsForAPIs.LoginResponseModel;
import org.testng.annotations.Test;
import yehiaEngine.managers.JsonManager;
import yehiaEngine.managers.SessionManager;

public class LoginWithValidUser {
    //Variables:
    String jsonFilePathForLoginTestData = "src/test/resources/TestDataJsonFiles/LoginTestData.json";
    String jsonFilePathForSessionIDs = "src/test/resources/SessionData/SessionIDsForUsers.json";
    JsonManager json = new JsonManager(jsonFilePathForLoginTestData);
    JsonManager json2 = new JsonManager(jsonFilePathForSessionIDs);
    String sessionID;

    @Test
    public void loginWithAdmin() throws JsonProcessingException {
        sessionID =
        new LoginRequestModel()
                .prepareLoginRequestWithCredentials(json.getData("Admin.Username"),json.getData("Admin.Password"))
                .sendLoginRequest()
                .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.LoginSuccess")))
                .validateStatusFromResponse(json.getData("Statuses.LoginSuccess"))
                .validateMassageFromResponse(json.getData("Messages.LoginSuccess"))
                .validateUserIDFromResponse(Integer.parseInt(json.getData("Admin.UserID")))
                .validateSessionIDExistsInResponse()
                .getSessionID();

        //Store SessionData into Json File
        json2.setData("SessionID.Admin",sessionID);
    }

    @Test
    public void loginWithUser1() throws JsonProcessingException {
        sessionID =
                new LoginRequestModel()
                        .prepareLoginRequestWithCredentials(json.getData("User1.Username"),json.getData("User1.Password"))
                        .sendLoginRequest()
                        .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.LoginSuccess")))
                        .validateStatusFromResponse(json.getData("Statuses.LoginSuccess"))
                        .validateMassageFromResponse(json.getData("Messages.LoginSuccess"))
                        .validateUserIDFromResponse(Integer.parseInt(json.getData("User1.UserID")))
                        .validateSessionIDExistsInResponse()
                        .getSessionID();

        //Store SessionData into Json File
        json2.setData("SessionID.User1",sessionID);
    }

    @Test
    public void loginWithUser2() throws JsonProcessingException {
        sessionID =
                new LoginRequestModel()
                        .prepareLoginRequestWithCredentials(json.getData("User2.Username"),json.getData("User2.Password"))
                        .sendLoginRequest()
                        .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.LoginSuccess")))
                        .validateStatusFromResponse(json.getData("Statuses.LoginSuccess"))
                        .validateMassageFromResponse(json.getData("Messages.LoginSuccess"))
                        .validateUserIDFromResponse(Integer.parseInt(json.getData("User2.UserID")))
                        .validateSessionIDExistsInResponse()
                        .getSessionID();

        //Store SessionData into Json File
        json2.setData("SessionID.User2",sessionID);
    }
}
