package payload;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class MockTest {

	@Test
	public void validateTest() {
		
		
	/*	
		JsonPath  js=new JsonPath(GoogleplacePayload.CoursePrice());
		//number course
		int totalCourses =js.getInt("courses.size");
		System.out.println(totalCourses);
		
		//first course title
		String titleFirstCourse=js.getString("course[0].title");
		System.out.println(titleFirstCourse);
		
		//Title and price of all courses
		for(int i=0;i<totalCourses;i++) {
			String title=js.getString("courses["+i+"].title");
			System.out.println(title);
			if(title.equals("RPA")) {
				int copies=js.getInt("courses["+i+"].copies");
				System.out.println(copies);
			}
			int price=js.getInt("courses["+i+"].price");
			System.out.println(price);
		}
			//Copies of RPA course
			//purchase amount and total courses price
			int sum=0;
			for(int j=0;j<totalCourses;j++) {
				int copies=js.getInt("courses["+j+"].copies");
				int prices=js.getInt("courses["+j+"].price");
				int TotalPrice=copies*prices;
				sum=sum+TotalPrice;
			}
			System.out.println(sum);
			 
			int purchaseAmount=js.getInt("dashboard.purchaseAmount");
			System.out.println(purchaseAmount);
			assertEquals(sum,purchaseAmount);
		*/
		
	}
	
}
