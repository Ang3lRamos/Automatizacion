package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest {

    public static void main(String[] args) {
        // Configurar RestAssured
        RestAssured.baseURI = "https://gorest.co.in/public/v1";

        // Obtener un usuario
        Response getUserResponse = given()
                .header("Authorization", "Bearer 5750205b90c245d45903d18c65da4152602ba18a4d8aa1af6d9c47d361ee4e8a")
                .get("/users");

        int userId = getUserResponse.jsonPath().getInt("data[0].id");

        // Datos para actualizar
        String updatedName = "Angel Ramos";
        String updatedEmail = "angel@gmail.com";

        // Modificar el usuario
        Response putResponse = given()
                .header("Authorization", "Bearer 5750205b90c245d45903d18c65da4152602ba18a4d8aa1af6d9c47d361ee4e8a")
                .contentType("application/json")
                .body("{\"name\":\"" + updatedName + "\", \"email\":\"" + updatedEmail + "\"}")
                .when()
                .put("/users/" + userId);

        // Validar la respuesta
        putResponse.then().statusCode(200)
                .body("data.name", equalTo(updatedName))
                .body("data.email", equalTo(updatedEmail));

        System.out.println("Usuario actualizado y validado correctamente.");
    }
}
