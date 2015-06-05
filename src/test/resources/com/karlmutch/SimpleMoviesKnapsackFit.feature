Feature: Generate an array from the product of an inputs array excluding the original input from the products at each element
  Exercise knowledge of loops and a little optimization

Scenario: Check a simple fit for two movies the same length
    Given a series of integers:
    | 1 | 1 |
    When a flight of 2 minutes long is flown
    Then the movie schedule will be full 

Scenario: Check a simple fit for two movies of different lengths
    Given a series of integers:
    | 1 | 2 |
    When a flight of 3 minutes long is flown
    Then the movie schedule will be full 

Scenario: Check bad fit for two movies the same length but three are needed
    Given a series of integers:
    | 1 | 1 |
    When a flight of 3 minutes long is flown
    Then the movie schedule will not be complete 
    
Scenario: Check bad fit for two movies with all but 1 of the movies being too long
    Given a series of integers:
    | 1 | 3 | 4 | 5 |
    When a flight of 2 minutes long is flown
    Then the movie schedule will not be complete 

Scenario: Check bad fit for two movies with all of the movies being too long
    Given a series of integers:
    | 3 | 4 | 5 |
    When a flight of 2 minutes long is flown
    Then the movie schedule will not be complete 
