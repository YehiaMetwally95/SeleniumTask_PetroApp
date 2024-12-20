package testCases.UITests;

import baseTest.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddRecordPage;
import yehiaEngine.managers.JsonManager;

import static pages.AddRecordPage.*;
import static pages.AddRecordPage.getRandomTime;
import static yehiaEngine.driverManager.BrowserFactory.getDriver;
import static yehiaEngine.utilities.RandomDataGenerator.generateItemFromList;
import static yehiaEngine.utilities.RandomDataGenerator.generateUniqueInteger;

public class RemoveExistingRecord extends BaseTest {
    //Variables
    String jsonFilePathForRemoveRecord = "src/test/resources/TestDataJsonFiles/RemoveRecordTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForRemoveRecord);

    @BeforeMethod
    public void prepareRecordInputs()
    {
        json
                .setData("SecondRecord.CarNumber",generateUniqueInteger())
                .setData("SecondRecord.FuelAmount",getRandomFuelAmount())
                .setData("SecondRecord.FuelCost",getRandomFuelCost())
                .setData("SecondRecord.FuelType", generateItemFromList(json.getDataAsListOfObjects("FuelTypes")).toString())
                .setData("SecondRecord.Date",getRandomDate())
                .setData("SecondRecord.Time",getRandomTime())
                .setData("SecondRecord.CustomerID",generateUniqueInteger());
    }

    @BeforeMethod (dependsOnMethods = "prepareRecordInputs")
    public void addNewRecordWithRandomData()
    {
        new AddRecordPage(getDriver(isolatedDriver))
                .verifyFuelTrackerPageIsOpened(json.getData("Headers.FuelTrackerPage"))
                .verifyCreationFormHeader(json.getData("Headers.CreationForm"))
                .addNewRecord(json.getData("FirstRecord.CarNumber"),json.getData("FirstRecord.FuelAmount"), json.getData("FirstRecord.FuelCost"),json.getData("FirstRecord.FuelType"), json.getData("FirstRecord.Date"),json.getData("FirstRecord.Time"),json.getData("FirstRecord.CustomerID"))
                .verifyRecordDetailsInGrid("1",json.getData("FirstRecord.CarNumber"),json.getData("FirstRecord.FuelAmount"), json.getData("FirstRecord.FuelCost"),json.getData("FirstRecord.FuelType"), json.getData("FirstRecord.Date"),json.getData("FirstRecord.Time"),json.getData("FirstRecord.CustomerID"));
    }

    @Test
    public void removeExistingRecord()
    {
        new AddRecordPage(getDriver(isolatedDriver))
                .deleteExistingRecord("1")
                .assertRecordIsDeleted("1");
    }
}
