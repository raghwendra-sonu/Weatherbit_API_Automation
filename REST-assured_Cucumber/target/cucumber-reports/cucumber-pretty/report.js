$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/Verify_Weather_Information.feature");
formatter.feature({
  "name": "Verify weather information",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Verify weather information for a happy holidaymaker",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I like to holiday in \u003cCity\u003e",
  "keyword": "Given "
});
formatter.step({
  "name": "I only like to holiday on \u003cDay\u003e",
  "keyword": "And "
});
formatter.step({
  "name": "I look up for the weather forecast for the next \u003cNumber_Of_Days\u003e days",
  "keyword": "When "
});
formatter.step({
  "name": "Check If it has rained previous days",
  "keyword": "And "
});
formatter.step({
  "name": "I can see the temperature is between \u003cTemp_From\u003e to \u003cTemp_To\u003e degrees in Bondi Beach",
  "keyword": "Then "
});
formatter.step({
  "name": "I can see that it wont be snowing for the next \u003cNumber_Of_Days\u003e days",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "City",
        "Number_Of_Days",
        "Temp_From",
        "Temp_To",
        "Day"
      ]
    },
    {
      "cells": [
        "Bondi, AU",
        "14",
        "10.0",
        "30.0",
        "Thursday"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Verify weather information for a happy holidaymaker",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I like to holiday in Bondi, AU",
  "keyword": "Given "
});
formatter.match({
  "location": "UserStepDefinitions.verify_holiday_city(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I only like to holiday on Thursday",
  "keyword": "And "
});
formatter.match({
  "location": "UserStepDefinitions.verify_holiday_days(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I look up for the weather forecast for the next 14 days",
  "keyword": "When "
});
formatter.match({
  "location": "UserStepDefinitions.verify_weather_forecast(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Check If it has rained previous days",
  "keyword": "And "
});
formatter.match({
  "location": "UserStepDefinitions.verify_rain_in_past_Days()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see the temperature is between 10.0 to 30.0 degrees in Bondi Beach",
  "keyword": "Then "
});
formatter.match({
  "location": "UserStepDefinitions.verify_temperature_range(float,float)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see that it wont be snowing for the next 14 days",
  "keyword": "And "
});
formatter.match({
  "location": "UserStepDefinitions.verify_snow_depth(int)"
});
formatter.result({
  "status": "passed"
});
});