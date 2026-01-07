package com.ibm.climate;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StocksTest {

    static String stockId;   
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:3000";
    }

  
    @Test
    public void get() {

        Response response =
                given()
                    .log().all()
                .when()
                    .get("/stocks")
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        Assert.assertTrue(response.asString().contains("google"));
    }

  
    @Test()
    public void post() {

        String body = """
        {
          "name": "ChatGPT",
          "price": 9999
        }
        """;

        Response response =
                given().header("Content-Type", "application/json").body(body).when().post("/stocks").then()
                .statusCode(201).extract().response();

        stockId = response.jsonPath().getString("id"); 
        System.out.println("Created Stock ID: " + stockId);
    }

    @Test()
    public void Put() {
    	String stockId="10";

        String body = String.format("""
        {
          "id": "10",
          "name": "ABC",
          "price": 500
        }
        """, stockId);

        given()
            .header("Content-Type", "application/json")
            .body(body)
        .when()
            .put("/stocks/" + stockId)
        .then()
            .statusCode(200);
    }

    @Test()
    public void Patch() {
    	String stockId = "1"; 

        String body = """
        {
          "name": "Apple",
          "price": 7777
        }
        """;

        given()
            .header("Content-Type", "application/json")
            .body(body)
            .log().all()
        .when()
            .patch("/stocks/" + stockId)
        .then()
            .log().all()
            .statusCode(200);
    }
    
    @Test()
    public void deleteStock() {
    	String stockId = "1103";
    	

        given()
        .when()
            .delete("/stocks/" + stockId)
        .then()
            .statusCode(200);
    }

}