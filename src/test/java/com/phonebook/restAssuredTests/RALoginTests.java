package com.phonebook.restAssuredTests;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.AuthResponseDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RALoginTests extends TestBase {

    @Test
    public void loginSuccessTest(){

        AuthRequestDto auth = AuthRequestDto.builder()
                .username("ka@gmai.com")
                .password("Ka12345678$")
                .build();

        AuthResponseDto responseDto = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);

        System.out.println(responseDto.getToken());

        // eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoia2FAZ21haS5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDQ1ODA0MiwiaWF0IjoxNjg5ODU4MDQyfQ.ZWcC4wAk0akTSzrIBuk0eaKSG19vgcFVXe1wkqb1c0I


    }

    @Test
    public void loginWithWrongFormatEmailTest(){
        AuthRequestDto auth = AuthRequestDto.builder()
                .username("brgmail.com") //....
                .password("Ka12345678$")
                .build();

        ErrorDto errorDto = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDto.class);

        System.out.println(errorDto.getMessage());
        System.out.println(errorDto.getError());

    }

    @Test
    public void loginWithWrongFormatEmailTest2(){
        AuthRequestDto auth = AuthRequestDto.builder()
                .username("brgmail.com") //....
                .password("Ka12345678$")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .assertThat().body("message",containsString("Login or Password incorrect"))
                .assertThat().body("error",equalTo("Unauthorized"));


    }


}
