package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.elementActions.WebElementsActions;
import yehiaEngine.managers.JsonManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static yehiaEngine.utilities.RandomDataGenerator.*;
import static yehiaEngine.utilities.RandomDataGenerator.generateUniqueInteger;

public class AddRecordPage {
    //Variables
    WebDriver driver;
    WebElementsActions action;
    String jsonFilePathForAddRecord = "src/test/resources/TestDataJsonFiles/AddRecordTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddRecord);
    String browserType = System.getProperty("browserType");

    //Locators
    final private By pageHeader = By.cssSelector(".container h1");
    final private By formHeader = By.xpath("//form/preceding-sibling::h3");
    final private By carNumberTextBox = By.name("carNumber");
    final private By fuelAmountInLitreTextBox = By.name("fuelInLiters");
    final private By fuelCostTextBox = By.name("fuelCost");
    final private By fuelTypeTextBox = By.name("fuelType");
    final private By dateTimeTextBox = By.name("dateAndTime");
    final private By companyIdTexBox = By.name("companyId");
    final private By submitButton=By.xpath("//button[@type='submit']");

    By getRecordFromGrid(String recordIndex){
        return By.xpath("//table[contains(@class,'table-striped')]//tbody//tr["+recordIndex+"]");
    }
    By getCarNumberFromGrid(String recordIndex){
        return By.xpath("//table[contains(@class,'table-striped')]//tbody//tr["+recordIndex+"]/td[1]");
    }
    By getFuelAmountFromGrid(String recordIndex){
        return By.xpath("//table[contains(@class,'table-striped')]//tbody//tr["+recordIndex+"]/td[2]");
    }
    By getFuelCostFromGrid(String recordIndex){
        return By.xpath("//table[contains(@class,'table-striped')]//tbody//tr["+recordIndex+"]/td[3]");
    }
    By getFuelTypeFromGrid(String recordIndex){
        return By.xpath("//table[contains(@class,'table-striped')]//tbody//tr["+recordIndex+"]/td[4]");
    }
    By getDateTimeFromGrid(String recordIndex){
        return By.xpath("//table[contains(@class,'table-striped')]//tbody//tr["+recordIndex+"]/td[5]");
    }
    By getCustomerIDFromGrid(String recordIndex){
        return By.xpath("//table[contains(@class,'table-striped')]//tbody//tr["+recordIndex+"]/td[6]");
    }
    By getDeleteButtonFromGrid(String recordIndex){
        return By.xpath("//table[contains(@class,'table-striped')]//tbody//tr["+recordIndex+"]/td[7]/button");
    }

    //Constructor
    public AddRecordPage(WebDriver driver)
    {
        this.driver = driver;
        action = new WebElementsActions(driver);
    }

    //Actions
    @Step("Add New Record")
    public AddRecordPage addNewRecord(String carNumber, String fuelAmountInLitre, String fuelCost,
                                      String fuelType, String date, String time, String customerID)
    {
        if (browserType.equalsIgnoreCase("Firefox"))
        {
            action
                    .type(carNumberTextBox,carNumber)
                    .type(fuelAmountInLitreTextBox,fuelAmountInLitre)
                    .type(fuelCostTextBox,fuelCost)
                    .type(fuelTypeTextBox,fuelType)
                    .typeDateTime(dateTimeTextBox,date+time)
                    .type(companyIdTexBox,customerID)
                    .press(submitButton);
        }

        else
        {
            action
                    .type(carNumberTextBox,carNumber)
                    .type(fuelAmountInLitreTextBox,fuelAmountInLitre)
                    .type(fuelCostTextBox,fuelCost)
                    .type(fuelTypeTextBox,fuelType)
                    .typeDateTime(dateTimeTextBox,date)
                    .pressOnKey(dateTimeTextBox,Keys.ARROW_RIGHT)
                    .typeDateTime(dateTimeTextBox,time)
                    .type(companyIdTexBox,customerID)
                    .press(submitButton);
        }
        return this;
    }



    @Step("Delete Existing Record")
    public AddRecordPage deleteExistingRecord(String recordIndex)
    {
        action.press(getDeleteButtonFromGrid(recordIndex));
        return this;
    }

    @Step ("Add Multiple Records Then Verify")
    public AddRecordPage addMultipleRecordsThenVerify(String recordIndex)
    {
        //Generate Record Inputs
        String carNumber = generateUniqueInteger();
        String fuelAmount = getRandomFuelAmount();
        String fuelCost = getRandomFuelCost();
        String fuelType = generateItemFromList(json.getDataAsJsonArray("FuelTypes").asList()).getAsString();
        String date = getRandomDate();
        String time = getRandomTime();
        String customerID = generateUniqueInteger();

        //Add Record
        addNewRecord(carNumber,fuelAmount,fuelCost,fuelType,date,time,customerID);

        //Verify Record Details in Grid
        verifyRecordDetailsInGrid(recordIndex,carNumber,fuelAmount,fuelCost,fuelType,date,time,customerID);

        return this;
    }

    //Getters
    public static String getRandomFuelAmount() {
        return generateNumericalString(15,25);
    }

    public static String getRandomFuelCost() {
        return generateNumericalString(5,10);
    }

    public static String getRandomDate() {
        return generatePreviousDate("MM/dd/yyyy");
    }

    public static String getRandomTime() {
        return String.format("%02d:%02d",Integer.parseInt(generateNumericalString(1,12)),Integer.parseInt(generateNumericalString(1,59)))+generateItemFromList(List.of("AM","PM"));
    }


    //Validations
    @Step("Verify Fuel Tracker Page is Opened")
    public AddRecordPage verifyFuelTrackerPageIsOpened(String header)
    {
        verifyPageHeader(header);
        return this;
    }

    @Step("Verify Creation Form Header")
    public AddRecordPage verifyCreationFormHeader(String header)
    {
        CustomSoftAssert.assertEquals(action.readText(formHeader),header);
        return this;
    }

    @Step("Verify Record Details In Grid")
    public AddRecordPage verifyRecordDetailsInGrid(String recordIndex,String carNumber,String fuelAmountInLitre,String fuelCost,
                                                   String fuelType,String date,String time,String customerID)
    {
        verifyCarNumberFromGrid(recordIndex,carNumber)
                .verifyFuelAmountFromGrid(recordIndex,fuelAmountInLitre)
                .verifyFuelCostFromGrid(recordIndex,fuelCost)
                .verifyFuelTypeFromGrid(recordIndex,fuelType)
                .verifyDateTimeFromGrid(recordIndex,date,time)
                .verifyCustomerIDFromGrid(recordIndex,customerID);
        return this;
    }

    @Step("Assert Record is Deleted")
    public AddRecordPage assertRecordIsDeleted(String recordIndex)
    {
        CustomAssert.assertTrue(action.isElementNotDisplayed(getRecordFromGrid(recordIndex)));
        return this;
    }

    //Private Methods
    @Step("verify Page Header")
    private AddRecordPage verifyPageHeader(String header)
    {
        CustomSoftAssert.assertEquals(action.readText(pageHeader),header);
        return this;
    }

    @Step("Verify Car Number from Grid")
    private AddRecordPage verifyCarNumberFromGrid(String recordIndex,String carNumber)
    {
        String expected = action.readText(getCarNumberFromGrid(recordIndex));
        CustomSoftAssert.assertEquals(expected,carNumber);
        return this;
    }

    @Step("Verify Fuel Amount from Grid")
    private AddRecordPage verifyFuelAmountFromGrid(String recordIndex,String fuelAmount)
    {
        String expected = action.readText(getFuelAmountFromGrid(recordIndex));
        CustomSoftAssert.assertEquals(expected,fuelAmount);
        return this;
    }

    @Step("Verify Fuel Cost from Grid")
    private AddRecordPage verifyFuelCostFromGrid(String recordIndex,String fuelCost)
    {
        String expected = action.readText(getFuelCostFromGrid(recordIndex));
        CustomSoftAssert.assertEquals(expected,fuelCost);
        return this;
    }

    @Step("Verify Fuel Type from Grid")
    private AddRecordPage verifyFuelTypeFromGrid(String recordIndex,String fuelType)
    {
        String expected = action.readText(getFuelTypeFromGrid(recordIndex));
        CustomSoftAssert.assertEquals(expected,fuelType);
        return this;
    }

    @Step("Verify Date And Time from Grid")
    private AddRecordPage verifyDateTimeFromGrid(String recordIndex,String date,String time)
    {
        String expected = action.readText(getDateTimeFromGrid(recordIndex));
        date = date.replace("/","");
        time = time.replace(":","");
        String actual = convertDateTimeFormat(date+time);
        CustomSoftAssert.assertEquals(expected,actual);
        return this;
    }

    @Step("Verify CustomerID from Grid")
    private AddRecordPage verifyCustomerIDFromGrid(String recordIndex,String CustomerID)
    {
        String expected = action.readText(getCustomerIDFromGrid(recordIndex));
        CustomSoftAssert.assertEquals(expected,CustomerID);
        return this;
    }

    private String convertDateTimeFormat(String input)
    {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMddyyyyhhmma");
        LocalDateTime dateTime = LocalDateTime.parse(input,inputFormat);

        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return dateTime.format(outputFormat);
    }

}
