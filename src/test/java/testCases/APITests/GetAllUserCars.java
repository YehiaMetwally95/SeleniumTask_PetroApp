package testCases.APITests;

import com.fasterxml.jackson.core.JsonProcessingException;
import objectModelsForAPIs.FetchCarsRequestModel;
import objectModelsForAPIs.FetchCarsResponseModel;
import objectModelsForAPIs.LoginRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yehiaEngine.managers.JsonManager;

import java.lang.annotation.Target;

public class GetAllUserCars {
    //Variables:
    String jsonFilePathForLGetAllUserCarsTestData = "src/test/resources/TestDataJsonFiles/GetAllUserCarsTestData.json";
    String jsonFilePathForSessionIDs = "src/test/resources/SessionData/SessionIDsForUsers.json";
    JsonManager json = new JsonManager(jsonFilePathForLGetAllUserCarsTestData);
    JsonManager json2 = new JsonManager(jsonFilePathForSessionIDs);

    String sessionIDForAdmin;
    String sessionIDForUser1;
    String sessionIDForUser2;
    String invalidSessionID;

    @BeforeMethod
    public void getSessionDataForAllUsers() {
        sessionIDForAdmin = json2.getData("SessionID.Admin");
        sessionIDForUser1 = json2.getData("SessionID.User1");
        sessionIDForUser2 = json2.getData("SessionID.User2");
        invalidSessionID = json2.getData("SessionID.UnAuthorizedUser");
    }

    @Test
    public void getAllCarsOfAdmin() throws JsonProcessingException {
        new FetchCarsRequestModel()
                .sendFetchCarsRequest(sessionIDForAdmin)
                .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.FetchCarsSuccess")))
                .validateStatusFromResponse(json.getData("Statuses.FetchCarsSuccess"))
                .validateMassageFromResponse(json.getData("Messages.FetchCarsSuccess"))
                .validateUserIDFromResponse(Integer.parseInt(json.getData("Admin.UserID")))
                .validateAllCarsRetrievedFromResponse(json.getDataAsListOfObjects("Admin.Cars"));
    }

    @Test
    public void getAllCarsOfUser1() throws JsonProcessingException {
        new FetchCarsRequestModel()
                .sendFetchCarsRequest(sessionIDForUser1)
                .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.FetchCarsSuccess")))
                .validateStatusFromResponse(json.getData("Statuses.FetchCarsSuccess"))
                .validateMassageFromResponse(json.getData("Messages.FetchCarsSuccess"))
                .validateUserIDFromResponse(Integer.parseInt(json.getData("User1.UserID")))
                .validateAllCarsRetrievedFromResponse(json.getDataAsListOfObjects("User1.Cars"));
    }

    @Test
    public void getAllCarsOfUser2() throws JsonProcessingException {
        new FetchCarsRequestModel()
                .sendFetchCarsRequest(sessionIDForUser2)
                .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.FetchCarsSuccess")))
                .validateStatusFromResponse(json.getData("Statuses.FetchCarsSuccess"))
                .validateMassageFromResponse(json.getData("Messages.FetchCarsSuccess"))
                .validateUserIDFromResponse(Integer.parseInt(json.getData("User2.UserID")))
                .validateAllCarsRetrievedFromResponse(json.getDataAsListOfObjects("User2.Cars"));
    }

    @Test
    public void getAllCarsWithUnAuthorizedUser() throws JsonProcessingException {
        new FetchCarsRequestModel()
                .sendFetchCarsRequest(invalidSessionID)
                .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.FetchCarsFailure")))
                .validateStatusFromResponse(json.getData("Statuses.FetchCarsFailure"))
                .validateMassageFromResponse(json.getData("Messages.FetchCarsFailure"))
                .validateNoCarsExistInResponse();
    }
}
