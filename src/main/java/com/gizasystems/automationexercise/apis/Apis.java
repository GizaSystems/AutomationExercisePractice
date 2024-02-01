package com.gizasystems.automationexercise.apis;

import com.shaft.driver.SHAFT;

public class Apis {
    private SHAFT.API api;

    public Apis(SHAFT.API api) {
        this.api = api;
    }

    // Base URL
    public static String ApisBaseUrl = System.getProperty("baseUrl") + "/api";

    // Status Codes
    public static final int SUCCESS = 200;

    // Services

    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\

}
