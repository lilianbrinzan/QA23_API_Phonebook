package com.phonebook.restAssuredTests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoia2FAZ21haS5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDQ1ODA0MiwiaWF0IjoxNjg5ODU4MDQyfQ.ZWcC4wAk0akTSzrIBuk0eaKSG19vgcFVXe1wkqb1c0I";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
}
