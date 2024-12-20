package testCases.UITests;

import baseTest.BaseTest;
import org.testng.annotations.Test;
import pages.AddRecordPage;
import yehiaEngine.managers.JsonManager;

import static yehiaEngine.driverManager.BrowserFactory.getDriver;

public class AddNewRecordWithStaticData extends BaseTest {
    //Variables
    String jsonFilePathForAddRecord = "src/test/resources/TestDataJsonFiles/AddRecordTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddRecord);

    @Test
    public void addNewRecordWithStaticData()
    {
        new AddRecordPage(getDriver(isolatedDriver))
                .verifyFuelTrackerPageIsOpened(json.getData("Headers.FuelTrackerPage"))
                .verifyCreationFormHeader(json.getData("Headers.CreationForm"))
                .addNewRecord(json.getData("FirstRecord.CarNumber"),json.getData("FirstRecord.FuelAmount"), json.getData("FirstRecord.FuelCost"),json.getData("FirstRecord.FuelType"), json.getData("FirstRecord.Date"),json.getData("FirstRecord.Time"),json.getData("FirstRecord.CustomerID"))
                .verifyRecordDetailsInGrid("1",json.getData("FirstRecord.CarNumber"),json.getData("FirstRecord.FuelAmount"), json.getData("FirstRecord.FuelCost"),json.getData("FirstRecord.FuelType"), json.getData("FirstRecord.Date"),json.getData("FirstRecord.Time"),json.getData("FirstRecord.CustomerID"));
    }
}
