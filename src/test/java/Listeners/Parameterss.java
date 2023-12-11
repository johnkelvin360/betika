package Listeners;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class Parameterss {

    @DataProvider
    public Object[][] getInValidData() {
        Object[][] data = new Object[2][]; // Increase the size to accommodate more data

        //Add data for an invalid phoneNumber
        Map<String, String> inValidData = Map.of("invalidPhone", "1234567890", "validPassword", "#112346789p");
        HashMap<String, String> dp1 = new HashMap<>(inValidData);
        data[0] = new Object[]{dp1};

        // Add data for an existing phone number
        Map<String, String> existingData = Map.of("existingPhone", "0715949396", "validPassword", "#112346789p");
        HashMap<String, String> dp2 = new HashMap<>(existingData);
        data[1] = new Object[]{dp2};

        return data;
    }
}
