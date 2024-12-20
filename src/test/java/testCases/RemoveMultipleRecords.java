package testCases;

import baseTest.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddRecordPage;
import yehiaEngine.managers.JsonManager;

import static pages.AddRecordPage.*;
import static yehiaEngine.driverManager.BrowserFactory.getDriver;
import static yehiaEngine.utilities.RandomDataGenerator.generateItemFromList;
import static yehiaEngine.utilities.RandomDataGenerator.generateUniqueInteger;

public class RemoveMultipleRecords extends BaseTest {
    //Variables
    String jsonFilePathForRemoveRecord = "src/test/resources/TestDataJsonFiles/RemoveRecordTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForRemoveRecord);

    @BeforeMethod
    public void addMultipleRecordsWithRandomData()
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

    @Test
    public void removeMultipleRecords()
    {
        new AddRecordPage(getDriver(isolatedDriver))
                //Delete All Records by the index
                .deleteExistingRecord("17")
                .deleteExistingRecord("16")
                .deleteExistingRecord("15")
                .deleteExistingRecord("14")
                .deleteExistingRecord("13")
                .deleteExistingRecord("12")
                .deleteExistingRecord("11")
                .deleteExistingRecord("10")
                .deleteExistingRecord("9")
                .deleteExistingRecord("8")
                .deleteExistingRecord("7")
                .deleteExistingRecord("6")
                .deleteExistingRecord("5")
                .deleteExistingRecord("4")
                .deleteExistingRecord("3")
                .deleteExistingRecord("2")
                .deleteExistingRecord("1")

                //Assert All Records are Deleted by the index
                .assertRecordIsDeleted("1")
                .assertRecordIsDeleted("2")
                .assertRecordIsDeleted("3")
                .assertRecordIsDeleted("4")
                .assertRecordIsDeleted("5")
                .assertRecordIsDeleted("6")
                .assertRecordIsDeleted("7")
                .assertRecordIsDeleted("8")
                .assertRecordIsDeleted("9")
                .assertRecordIsDeleted("10")
                .assertRecordIsDeleted("11")
                .assertRecordIsDeleted("12")
                .assertRecordIsDeleted("13")
                .assertRecordIsDeleted("14")
                .assertRecordIsDeleted("15")
                .assertRecordIsDeleted("16")
                .assertRecordIsDeleted("17");
    }
}
