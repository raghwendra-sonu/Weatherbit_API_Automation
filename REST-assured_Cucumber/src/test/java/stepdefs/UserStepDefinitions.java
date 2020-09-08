package stepdefs;

import static io.restassured.RestAssured.given;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserStepDefinitions {

	private Response response;
	private RequestSpecification request;
	private String BaseURL = "https://api.weatherbit.io";
	private String APIKey = "943884ad1a6a4a1ea2395f6854091767";
	private String GeoLocation = "";

	@Given("I like to holiday in (.*)")
	public void verify_holiday_city(String City) {
		GeoLocation = City;
		request = given();
		request.header("Content-Type", "application/json");
		response = request.when().get("" + BaseURL + "/v2.0/forecast/daily?city=" + City + "&key=" + APIKey + "");
		System.out.println("response: " + response.prettyPrint());
		Assert.assertTrue(response.contentType().contains("application/json"));
		Assert.assertTrue(response.getBody().asString().contains(City.split(", ")[0]));
	}

	@And("I only like to holiday on (.*) in (.*)")
	public void verify_holiday_days(String Day, String City) throws ParseException {
		String datetime;
		request = given();
		request.header("Content-Type", "application/json");
		response = request.when().get("" + BaseURL + "/v2.0/forecast/daily?city=" + City + "&key=" + APIKey + "");
		System.out.println("response: " + response.prettyPrint());
		Assert.assertTrue(response.contentType().contains("application/json"));
		ArrayList<String> AllDates = JsonPath.from(response.asString()).get("data.datetime");
		for (int i = 0; i < AllDates.size(); i++) {
			datetime = JsonPath.from(response.asString()).get("data.datetime[" + i + "]");
			System.out.println(datetime);
			SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
			Date dt1=format1.parse(datetime);
			DateFormat format2=new SimpleDateFormat("EEEE"); 
			String finalDay=format2.format(dt1);
			if (finalDay.contains(Day)) {
				System.out.println(datetime +" is most latest " +Day +", i will like to holiday on");
				break;
			}
		}
		
	}

	@When("I look up for the weather forecast for the next {int} days")
	public void verify_weather_forecast(int number_of_days) {
		request = given();
		request.header("Content-Type", "application/json");
		response = request.when().get("" + BaseURL + "/v2.0/forecast/daily?city=" + GeoLocation + "&days="
				+ number_of_days + "&key=" + APIKey + "");
		System.out.println("response: " + response.prettyPrint());
		Assert.assertTrue(response.contentType().contains("application/json"));
	}

	@And("Check If it has rained previous days")
	public void verify_rain_in_past_Days() {
		boolean flag = false;
		String weather_data = null;
		request = given();
		request.header("Content-Type", "application/json");
		response = request.when().get("" + BaseURL + "/v1.0/history/hourly/geosearch?city=" + GeoLocation
				+ "&start_date=2020-09-03&end_date=2020-09-04&key=" + APIKey + "");
		System.out.println("response: " + response.prettyPrint());
		Assert.assertTrue(response.contentType().contains("application/json"));
		ArrayList<String> ar = JsonPath.from(response.asString()).get("data.weather");
		for (int i = 0; i < ar.size(); i++) {
			weather_data = JsonPath.from(response.asString()).get("data.weather[" + i + "].description");
			System.out.println(weather_data);
			if (weather_data.contains("rain")) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println("It rained on this day");
		} else {
			System.out.println("It didn't rain on this day");
		}
	}

	@Then("I can see the temperature is between {float} to {float} degrees in Bondi Beach")
	public void verify_temperature_range(float temp_from, float temp_to) {
		request = given();
		request.header("Content-Type", "application/json");
		response = request.when().get(
				"" + BaseURL + "/v1.0/forecast/daily/geosearch?city=" + GeoLocation + "&days=1&key=" + APIKey + "");
		System.out.println("response: " + response.prettyPrint());
		Assert.assertTrue(response.contentType().contains("application/json"));
		ArrayList<Float> temp_arr = JsonPath.from(response.asString()).get("data.temp");
		float temp = Float.parseFloat(temp_arr.get(0)+"");
		Assert.assertTrue(temp >= temp_from && temp <= temp_to);
	}

	@And("I can see that it wont be snowing for the next {int} days")
	public void verify_snow_depth(int number_of_days) {
		boolean flag = false;
		int snow_depth;
		request = given();
		request.header("Content-Type", "application/json");
		response = request.when().get("" + BaseURL + "/v2.0/forecast/daily?city=" + GeoLocation + "&days="
				+ number_of_days + "&key=" + APIKey + "");
		System.out.println("response: " + response.prettyPrint());
		Assert.assertTrue(response.contentType().contains("application/json"));
		ArrayList<Integer> snow = JsonPath.from(response.asString()).get("data.snow");
		for (int i = 0; i < snow.size(); i++) {
			snow_depth = JsonPath.from(response.asString()).get("data.snow[" + i + "]");
			if (snow_depth != 0) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println("It is going to snow in next " + number_of_days + " days");
			Assert.fail();
		} else {
			System.out.println("It is not going to snow for next " + number_of_days + " days");
		}
	}
}
