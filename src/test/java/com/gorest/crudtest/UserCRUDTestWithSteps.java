package com.gorest.crudtest;

import com.gorest.goreststeps.UserSteps;
import com.gorest.testbase.UserTestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class UserCRUDTestWithSteps extends UserTestBase {
    static String name = "Aarti Doshi" + TestUtils.getRandomValue();

    static String email = "Ada@gmail.com" + TestUtils.getRandomValue();

    static String gender = "female" ;

    static  String status = "active";

    static int userId;
    @Steps
    UserSteps userSteps;

    @Title("Get all Users Details")
    @Test
    public void test001(){
        ValidatableResponse response=userSteps.getAllUsers();
        response.log().all().statusCode(200);
    }

    @Title("This will create a new user")
    @Test
    public void test002(){
        ValidatableResponse response=userSteps.createUser(name,email,gender,status);
        response.log().all().statusCode(201);
        userId = response.extract().path("id");

    }

    @Title("Verify Newly Created User")
    @Test
    public void test003(){
        ValidatableResponse response=userSteps.VerifyUser(userId);
        response.log().all().statusCode(200);
    }

    @Title("Update the product information and verify the update information")
    @Test
    public void test004(){
        name = name+"_updated";
        userSteps.updateUser(userId,name,email,gender,status).statusCode(200);
        ValidatableResponse response=userSteps.updateUser(userId,name,email,gender,status);
        response.log().all().statusCode(200);
    }
    @Title("Delete the user and verify if the product is deleted!")
    @Test
    public void test005(){
        ValidatableResponse response=userSteps.deleteUser(userId);
        response.log().all().statusCode(204);
    }
}
