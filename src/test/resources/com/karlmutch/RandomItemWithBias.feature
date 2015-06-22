Feature: Random select items from a collection using an intentional bias
    Exercise skills for problem solving

Scenario: Select a city from a list of cities and force the selection
	Given a series of random numbers are used:
		| .9 | .9 | .9 | .9 |
	Given a city "Auckland" of 1000 people
	Given a city "New York" of 10000 people
	When choosing a city using test specified random number
	Then "New York" will be the city selected

Scenario: Select a city from a list of cities and force the selection
	Given a series of random numbers are used:
		| .00001 | .00001 | .00001 | .00001 |
	Given a city "Auckland" of 1000 people
	Given a city "New York" of 10000 people
	When choosing a city using test specified random number
	Then "Auckland" will be the city selected
