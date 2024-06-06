package com.blains.step_definitions;

import com.blains.utilities.Environment;
import io.cucumber.java.en.Given;

public class EnvironmentStepDefs {
    @Given("I get related environment information")
    public void i_get_related_environment_information() {
        System.out.println("Environment.URL = " + Environment.URL);
        System.out.println("Environment.BASE_URL = " + Environment.BASE_URL);
        System.out.println("Environment.USER1_EMAIL = " + Environment.USER1_EMAIL);

        String USER1_email = System.getProperty("USER1_email") != null ?
                USER1_email = System.getProperty("USER1_email") : Environment.USER1_EMAIL;

        System.out.println("USER1_email = " + USER1_email);
    }
}
