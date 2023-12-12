package Listeners;

import org.testng.annotations.DataProvider;
import java.util.HashMap;
import java.util.Map;

public class Parameterss {

    @DataProvider
    public Object[][] getInvalidData() {
        Object[][] data = new Object[1][]; // Adjust size based on the number of test cases

        // Add data for an invalid phoneNumber
        Map<String, String> invalidData = Map.of("invalidPhone", "1234567890", "invalidPassword", "#112346789p");
        HashMap<String, String> dp1 = new HashMap<>(invalidData);
        data[0] = new Object[]{dp1};

        // Add data for an existing phone number
        Map<String, String> existingData = Map.of("existingPhone", "0715949396", "validPassword", "#112346789p");
        HashMap<String, String> dp2 = new HashMap<>(existingData);
        data[1] = new Object[]{dp2};

        return data;
    }
    
}
