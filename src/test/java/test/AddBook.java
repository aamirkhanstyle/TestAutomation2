package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import pojo.BookData;

public class AddBook {

	@Test
	public void addNewBook() {
		BookData d=new BookData();
		d.setName("Test");
		d.setIsbn("sdfs");
		d.setAisle("1234");
		d.setAuther("Aamir");
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		given().log().all().header("Content-Type","application/json").body(d).
		when().post("Library/Addbook.php").then().assertThat().statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchema(new File("D:\\TestAutomation\\TestAutomationAPI\\TestData\\test.json")));

}	
	
}
