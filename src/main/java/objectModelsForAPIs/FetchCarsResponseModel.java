package objectModelsForAPIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import pojoClassesForAPIs.FetchCarsRequestPojo;
import pojoClassesForAPIs.FetchCarsResponsePojo;
import pojoClassesForAPIs.LoginRequestPojo;
import pojoClassesForAPIs.LoginResponsePojo;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;

import java.util.Arrays;
import java.util.List;

import static yehiaEngine.managers.ApisManager.getResponseCode;

public class FetchCarsResponseModel {

    //ObjectsFromPojoClasses
    FetchCarsRequestPojo requestObject;
    FetchCarsResponsePojo responseObject;
    Response response;

    //Constructor to pass the response from Request Model to Response Model
    public FetchCarsResponseModel(FetchCarsRequestPojo requestObject, FetchCarsResponsePojo responseObject, Response response) {
        this.requestObject = requestObject;
        this.responseObject = responseObject;
        this.response = response;
    }

    //Validation Methods
    @Step("ValidateMassageFromResponse")
    public FetchCarsResponseModel validateMassageFromResponse(String expectedMessage) {
        CustomAssert.assertEquals(responseObject.getMessage(), expectedMessage);
        return this;
    }

    @Step("ValidateStatusFromResponse")
    public FetchCarsResponseModel validateStatusFromResponse(String expectedStatus) {
        CustomAssert.assertEquals(responseObject.getStatus(), expectedStatus);
        return this;
    }

    @Step("ValidateCodeFromResponse")
    public FetchCarsResponseModel validateCodeFromResponse(int responseCody) {
        CustomAssert.assertEquals(getResponseCode(response), responseCody);
        return this;
    }

    @Step("ValidateUserIDFromResponse")
    public FetchCarsResponseModel validateUserIDFromResponse(int user_id) {
        CustomAssert.assertEquals(responseObject.getUser_id(), user_id);
        return this;
    }

    @Step("validateNoCarsExistInResponse")
    public FetchCarsResponseModel validateNoCarsExistInResponse() {
        CustomAssert.assertTrue(responseObject.getCars() == null);
        return this;
    }

    @Step("ValidateAllCarsRetrievedFromResponse")
    public FetchCarsResponseModel validateAllCarsRetrievedFromResponse(List<Object> cars) throws JsonProcessingException {
        //Serialize the Pojo Object and Convert it to Json String
        String actualListOfCars = new JsonMapper().writeValueAsString(responseObject.getCars());

        //Convert List of Cars to String
        String expectedListOfCars = cars.toString();

        CustomAssert.assertEquals(actualListOfCars,expectedListOfCars);
        return this;
    }

    @Step("ValidateCarDetailsByIndex")
    public FetchCarsResponseModel validateCarDetailsByIndex(int car_index,String car_id,String make,String model,String year) {
        CustomSoftAssert.assertEquals(responseObject.getCars().get(car_index).getCar_id(),car_id);
        CustomSoftAssert.assertEquals(responseObject.getCars().get(car_index).getMake(),make);
        CustomSoftAssert.assertEquals(responseObject.getCars().get(car_index).getModel(),model);
        CustomSoftAssert.assertEquals(responseObject.getCars().get(car_index).getYear(),year);
        return this;
    }

    //Getter Methods
    @Step("Get Cars List from Response")
    public List<Object> getCarsList() {
        return Arrays.asList(responseObject.getCars().toArray());
    }

    public FetchCarsRequestPojo getRequestPojoObject() {
        return requestObject;
    }

    public FetchCarsResponsePojo getResponsePojoObject() {
        return responseObject;

    }
}
