package com.gizasystems.automationexercise.apis;

import com.shaft.driver.SHAFT;

public class Apis {
    // Variables
    private SHAFT.API api;

    // Constructor
    public Apis(SHAFT.API api) {
        this.api = api;
    }

    // Base URL
    public static String apisBaseUrl = System.getProperty("baseUrl") + "/api";

    // Status Codes
    public static final int SUCCESS = 200;

    // Services

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\

}
