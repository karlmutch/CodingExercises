Feature: Test Lookaside using products of values in large arrays scenarios 
  Exercise knowledge about lookaside 

Scenario: Cake Interview sample data 
    Given a series of integers:
    | 1 | 10 | -5 | 1 | -100 | 
    When searching for the largest product of any of 3 items
    Then the product will be 5000

Scenario: Cake Interview sample data 
    Given a series of integers:
    | -100 | 10 | -5 | 3 | 1 | 
    When searching for the largest product of any of 3 items
    Then the product will be 5000
    