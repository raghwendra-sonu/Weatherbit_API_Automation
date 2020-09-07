Feature: Verify weather information

	Scenario Outline: Verify weather information for a happy holidaymaker
	Given I like to holiday in <City>
	And I only like to holiday on <Day>
	When I look up for the weather forecast for the next <Number_Of_Days> days
	And Check If it has rained previous days
	Then I can see the temperature is between <Temp_From> to <Temp_To> degrees in Bondi Beach
	And I can see that it wont be snowing for the next <Number_Of_Days> days
	
	Examples:
		|City     |Number_Of_Days|Temp_From|Temp_To|Day     |
		|Bondi, AU|14            |10.0     |30.0   |Thursday|