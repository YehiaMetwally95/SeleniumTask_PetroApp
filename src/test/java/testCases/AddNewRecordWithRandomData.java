package testCases;

import baseTest.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddRecordPage;
import yehiaEngine.managers.JsonManager;

import java.util.List;

import static pages.AddRecordPage.*;
import static yehiaEngine.utilities.RandomDataGenerator.*;

import static yehiaEngine.driverManager.BrowserFactory.getDriver;

public class AddNewRecordWithRandomData extends BaseTest {
    //Variables
    String jsonFilePathForAddRecord = "src/test/resources/TestDataJsonFiles/AddRecordTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddRecord);

    @BeforeMethod
    public void prepareRecordInputs()
    {
        json
                .setData("SecondRecord.CarNumber",generateUniqueInteger())
                .setData("SecondRecord.FuelAmount",getRandomFuelAmount())
                .setData("SecondRecord.FuelCost",getRandomFuelCost())
                .setData("SecondRecord.FuelType", generateItemFromList(json.getDataAsJsonArray("FuelTypes").asList()).getAsString())
                .setData("SecondRecord.Date",getRandomDate())
                .setData("SecondRecord.Time",getRandomTime())
                .setData("SecondRecord.CustomerID",generateUniqueInteger());
    }

    @Test
    public void addNewRecordWithRandomData()
    {
        new AddRecordPage(getDriver(isolatedDriver))
                .verifyFuelTrackerPageIsOpened(json.getData("Headers.FuelTrackerPage"))
                .verifyCreationFormHeader(json.getData("Headers.CreationForm"))
                .addNewRecord(json.getData("SecondRecord.CarNumber"),json.getData("SecondRecord.FuelAmount"), json.getData("SecondRecord.FuelCost"),json.getData("SecondRecord.FuelType"), json.getData("SecondRecord.Date"),json.getData("SecondRecord.Time"),json.getData("SecondRecord.CustomerID"))
                .verifyRecordDetailsInGrid("1",json.getData("SecondRecord.CarNumber"),json.getData("SecondRecord.FuelAmount"), json.getData("SecondRecord.FuelCost"),json.getData("SecondRecord.FuelType"), json.getData("SecondRecord.Date"),json.getData("SecondRecord.Time"),json.getData("SecondRecord.CustomerID"));
    }
}
