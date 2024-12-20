package testCases;

import baseTest.BaseTest;
import org.testng.annotations.Test;
import pages.AddRecordPage;
import yehiaEngine.managers.JsonManager;

import static yehiaEngine.driverManager.BrowserFactory.getDriver;

public class AddMultipleRecordsWithRandomData extends BaseTest {
    //Variables
    String jsonFilePathForAddRecord = "src/test/resources/TestDataJsonFiles/AddRecordTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddRecord);

    @Test
    public void addMultipleRecords()
    {
        new AddRecordPage(getDriver(isolatedDriver))
                .verifyFuelTrackerPageIsOpened(json.getData("Headers.FuelTrackerPage"))
                .verifyCreationFormHeader(json.getData("Headers.CreationForm"))
                .addMultipleRecordsThenVerify("1")
                .addMultipleRecordsThenVerify("2")
                .addMultipleRecordsThenVerify("3")
                .addMultipleRecordsThenVerify("4")
                .addMultipleRecordsThenVerify("5")
                .addMultipleRecordsThenVerify("6")
                .addMultipleRecordsThenVerify("7")
                .addMultipleRecordsThenVerify("8")
                .addMultipleRecordsThenVerify("9")
                .addMultipleRecordsThenVerify("10")
                .addMultipleRecordsThenVerify("11")
                .addMultipleRecordsThenVerify("12")
                .addMultipleRecordsThenVerify("13")
                .addMultipleRecordsThenVerify("14")
                .addMultipleRecordsThenVerify("15")
                .addMultipleRecordsThenVerify("16")
                .addMultipleRecordsThenVerify("17");
    }
}
