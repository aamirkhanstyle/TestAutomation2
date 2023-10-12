package test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.RequestPayload;
import pojo.Location;
import pojo.PlaceData;

  
public class AddPlace {
	
	static String placeId;
	Workbook book;
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
	}
/*	@Test(priority=0,description="Creating a place data  server",invocationCount=1,groups="Smoke")
	public void createGooglePlace() {
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =
				given().log().all().queryParam("key","qaclick123").
		header("Content-Type","application/json").
		body(RequestPayload.placePayload()).
		when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).header("Server","Apache/2.4.52 (Ubuntu)")
		.body("status",equalTo("OK")).extract().response().asString();;
		
		JsonPath js =new JsonPath(response);
		 placeId=js.getString("place_id");
		System.out.println(placeId);   
	}   */
	
/*	@Test(priority=0,description="Creating a place data  server",invocationCount=1,groups="Smoke",dataProvider="userdata")
	public void createGooglePlace(String name,String address) {
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =
				given().log().all().queryParam("key","qaclick123").
		header("Content-Type","application/json").
		body(RequestPayload.placePayload(name,address)).
		when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).header("Server","Apache/2.4.52 (Ubuntu)")
		.body("status",equalTo("OK")).extract().response().asString();;
 		
		JsonPath js =new JsonPath(response);
		 placeId=js.getString("place_id");
		System.out.println(placeId); 
		
		
	}    */
	@Test(priority=0,description="Creating a place data  server",invocationCount=1,groups="Smoke",dataProvider="userdata")
	public void createGooglePlace(String name,String address) {
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		PlaceData d=new PlaceData();
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		d.setLocation(l);
		d.setAccuracy(50);
		d.setName("Aamir");
		d.setPhone_number("9716261388");
		d.setAddress("Delhi");
		List<String> list=new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		d.setTypes(list);
		d.setWebsite("http://google.com");
		d.setLanguage("French-IN");
		String response=given().log().all().queryParam("key","qaclick123")
				.header("Content-Type","application/json")
				.body(d)
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200)
				.header("Server","Apache/2.4.52 (Ubuntu)")
				.body("scope",equalTo("APP")).extract().response().asString();
				JsonPath js=new JsonPath(response);
			 placeId=js.getString("place_id");
				System.out.println(placeId);
				String statusValue=js.getString("status");
				assertEquals("OK",statusValue);
		
	}  
	
	@Test(priority=2,description="Getting existing place data from server")
public void getGooglePlaceData() {
	//RestAssured.baseURI="https://rahulshettyacademy.com";
	String res=given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeId).
	when().get("maps/api/place/get/json")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js1 =new JsonPath(res);
	 String add=js1.getString("address");
	System.out.println(add);  
	assertEquals("Delhi NCR", add);
	//assertEquals("29, side layout, cohen 09", add);
	
	String typ=js1.getString( "types");
	   System.out.println(typ);
	   assertEquals("shoe park,shop", typ);
	   
	   String lan=js1.getString( "language");
	   System.out.println(lan);
	   assertEquals("French-IN", lan);
	   
	   String web=js1.getString( "website");
	   System.out.println(web);
	   assertEquals("http://google.com", web);
	
}
	@Test(priority=1)
    public void  updateAddress() {
    Response re=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
    			.body("{\r\n"
    					+ "\"place_id\":\""+placeId+"\",\r\n"
    					+ "\"address\":\"Delhi NCR\",\r\n"
    					+ "\"key\":\"qaclick123\"\r\n"
    					+ "}")
    			.when().put("maps/api/place/update/json")
    			.then().log().all().assertThat().statusCode(200).header("Server","Apache/2.4.52 (Ubuntu)")
    			.body("msg",equalTo("Address successfully updated"))
    			.extract().response();
    
     long resTime=re.getTime();
     System.out.println(resTime);
     int statusCode=re.getStatusCode();
     System.out.println(statusCode);
     assertEquals(200,statusCode);
     }
	
	@DataProvider(name="userdata")
	//public Object[][]  getData( String sheetName)
    public Object[][]  getData(){
    //	return new Object[][] {{"Manish","Gwalior"},{"Rahul","Indore"},{"Tarun","Nagpur"},{"Aamir","Delhi"}};
		
		try {
			FileInputStream file=new FileInputStream("D:\\TestAutomation\\TestAutomationAPI\\TestData\\Book 1.xlsx ");
			try {
			 book=WorkbookFactory.create(file);
			} catch (EncryptedDocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Sheet sheet = book.getSheetAt(0);
    	//Sheet sheet = book.getSheetAt(sheetName   );
    	Object[][]data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
    	for(int i=0;i<sheet.getLastRowNum();i++) {
    		for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
    		data[i][j]=	sheet.getRow(i+1).getCell(j).toString();
    		}
    	}
    	return data;

  
    	
    }	
	
}
