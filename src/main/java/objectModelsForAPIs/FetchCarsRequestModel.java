package objectModelsForAPIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojoClassesForAPIs.FetchCarsRequestPojo;
import pojoClassesForAPIs.FetchCarsResponsePojo;
import pojoClassesForAPIs.LoginRequestPojo;
import pojoClassesForAPIs.LoginResponsePojo;

import static yehiaEngine.managers.ApisManager.*;
import static yehiaEngine.managers.PropertiesManager.getPropertiesValue;

public class FetchCarsRequestModel {

    //Variables
    String fetchCarsEndpoint = getPropertiesValue("baseUrlApi")+"?endpoint=fetch_cars";
    String jsonBodyAsString;
    Response response;
    JsonMapper mapper;

    //ObjectsFromPojoClasses
    FetchCarsRequestPojo requestObject;
    FetchCarsResponsePojo responseObject;

    //Method to Execute FetchCars Request
    @Step("Send Request of FetchCars")
    public FetchCarsResponseModel sendFetchCarsRequest(String sessionID) throws JsonProcessingException {
        response =
                GetAuthRequest(fetchCarsEndpoint, null, "Session-ID",null,null,sessionID);
        jsonBodyAsString = getResponseBody(response);

        mapper = new JsonMapper();
        responseObject = mapper.readValue(jsonBodyAsString, FetchCarsResponsePojo.class);

        return new FetchCarsResponseModel(requestObject, responseObject,response);
    }
}
