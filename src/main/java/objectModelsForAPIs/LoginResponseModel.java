package objectModelsForAPIs;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojoClassesForAPIs.LoginRequestPojo;
import pojoClassesForAPIs.LoginResponsePojo;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;

import static yehiaEngine.managers.ApisManager.getResponseCode;

public class LoginResponseModel {

    //ObjectsFromPojoClasses
    LoginRequestPojo requestObject;
    LoginResponsePojo responseObject;
    Response response;

    //Constructor to pass the response from Request Model to Response Model
    public LoginResponseModel(LoginRequestPojo requestObject, LoginResponsePojo responseObject,Response response) {
        this.requestObject = requestObject;
        this.responseObject = responseObject;
        this.response = response;
    }

    //Validation Methods
    @Step("ValidateMassageFromResponse")
    public LoginResponseModel validateMassageFromResponse(String expectedMessage) {
        CustomAssert.assertEquals(responseObject.getMessage(), expectedMessage);
        return this;
    }

    @Step("ValidateStatusFromResponse")
    public LoginResponseModel validateStatusFromResponse(String expectedStatus) {
        CustomAssert.assertEquals(responseObject.getStatus(), expectedStatus);
        return this;
    }

    @Step("ValidateCodeFromResponse")
    public LoginResponseModel validateCodeFromResponse(int responseCody) {
        CustomAssert.assertEquals(getResponseCode(response), responseCody);
        return this;
    }

    @Step("ValidateUserIDFromResponse")
    public LoginResponseModel validateUserIDFromResponse(int user_id) {
        CustomAssert.assertEquals(responseObject.getUser_id(), user_id);
        return this;
    }

    @Step("ValidateSessionIDExistsInResponse")
    public LoginResponseModel validateSessionIDExistsInResponse() {
        CustomAssert.assertTrue(responseObject.getSession_id() != null);
        return this;
    }

    //Getter Methods
    @Step("Get Session ID from Response")
    public String getSessionID() {
        return responseObject.getSession_id();
    }

    public LoginRequestPojo getRequestPojoObject() {
        return requestObject;
    }

    public LoginResponsePojo getResponsePojoObject() {
        return responseObject;

    }
}
