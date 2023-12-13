package Listeners;

import org.testng.annotations.DataProvider;
import java.util.HashMap;
import java.util.Map;

public class Parameterss {

    @DataProvider(name = "getInvalidData")
    public Object[][] getInvalidData() {
        Object[][] data = new Object[1][]; // Adjust size based on the number of test cases

        // Add data for an invalid phoneNumber
        Map<String, String> invalidData = Map.of("invalidPhone", "1234567890", "invalidPassword", "#112346789p");
        HashMap<String, String> dp1 = new HashMap<>(invalidData);
        data[0] = new Object[]{dp1};

        return data;
    }

    @DataProvider(name = "getValidLoginData")
    public Object[][] getValidLoginData() {
        Object[][] data = new Object[1][]; // Adjust size based on the number of test cases

        // Add data for a valid login
        Map<String, String> validLoginData = Map.of("phoneNumber", "valid_phone_number", "password", "valid_password");
        HashMap<String, String> dp2 = new HashMap<>(validLoginData);
        data[0] = new Object[]{dp2};

        return data;
    }
}
