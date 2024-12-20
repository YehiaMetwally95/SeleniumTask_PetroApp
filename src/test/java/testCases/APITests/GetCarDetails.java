package testCases.APITests;

import com.fasterxml.jackson.core.JsonProcessingException;
import objectModelsForAPIs.FetchCarsRequestModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yehiaEngine.managers.JsonManager;

public class GetCarDetails {
    //Variables:
    String jsonFilePathForLGetAllUserCarsTestData = "src/test/resources/TestDataJsonFiles/GetAllUserCarsTestData.json";
    String jsonFilePathForSessionIDs = "src/test/resources/SessionData/SessionIDsForUsers.json";
    JsonManager json = new JsonManager(jsonFilePathForLGetAllUserCarsTestData);
    JsonManager json2 = new JsonManager(jsonFilePathForSessionIDs);

    String sessionIDForUser1;

    @BeforeMethod
    public void getSessionDataForAllUsers() {
        sessionIDForUser1 = json2.getData("SessionID.User1");
    }

    @Test
    public void verifyCarDetails_1() throws JsonProcessingException {
        new FetchCarsRequestModel()
                .sendFetchCarsRequest(sessionIDForUser1)
                .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.FetchCarsSuccess")))
                .validateStatusFromResponse(json.getData("Statuses.FetchCarsSuccess"))
                .validateMassageFromResponse(json.getData("Messages.FetchCarsSuccess"))
                .validateUserIDFromResponse(Integer.parseInt(json.getData("User1.UserID")))
                .validateCarDetailsByIndex(0,json.getData("User1.Cars[0].car_id"),json.getData("User1.Cars[0].make"),json.getData("User1.Cars[0].model"),json.getData("User1.Cars[0].year"));
    }

    @Test
    public void verifyCarDetails_2() throws JsonProcessingException {
        new FetchCarsRequestModel()
                .sendFetchCarsRequest(sessionIDForUser1)
                .validateCodeFromResponse(Integer.parseInt(json.getData("ResponseCodes.FetchCarsSuccess")))
                .validateStatusFromResponse(json.getData("Statuses.FetchCarsSuccess"))
                .validateMassageFromResponse(json.getData("Messages.FetchCarsSuccess"))
                .validateUserIDFromResponse(Integer.parseInt(json.getData("User1.UserID")))
                .validateCarDetailsByIndex(1,json.getData("User1.Cars[1].car_id"),json.getData("User1.Cars[1].make"),json.getData("User1.Cars[1].model"),json.getData("User1.Cars[1].year"));
    }
}
