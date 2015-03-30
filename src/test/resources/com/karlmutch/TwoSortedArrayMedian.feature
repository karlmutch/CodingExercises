Feature: Search sorted arrays and look for the median value by sorted position
  Exercise knowledge on applying binary search to arrays, used by columnar data stores without indexes 

Scenario: Using two sorted arrays locate the median item and return its value
    Given multiple series of integers:
    | 1, 2, 4, 7 | 0, 5, 6, 9 |
    When we find the median item by sorted position
    Then the median items value will be 4 

Scenario: Using two sorted arrays locate the median item and return its value
    Given multiple series of integers:
    | 5, 5, 5 | 1, 1, 6, 10 |
    When we find the median item by sorted position
    Then the median items value will be 5

Scenario: Using two sorted arrays locate the median item and return its value
    Given multiple series of integers:
    | 1, 1, 3, 3 | 2, 2, 2, 2 |
    When we find the median item by sorted position
    Then the median items value will be 2

Scenario: Using two sorted arrays locate the median item and return its value
    Given multiple series of integers:
    | 1, 2, 3 | 4, 5, 6, 7 |
    When we find the median item by sorted position
    Then the median items value will be 4
    
Scenario: Using two sorted arrays locate the median item and return its value
    Given multiple series of integers:
    | 1 | 2, 3, 4, 5, 6, 7 |
    When we find the median item by sorted position
    Then the median items value will be 4
    
Scenario: Using two sorted arrays locate the median item and return its value
    Given multiple series of integers:
    | 1, 2, 3, 4, 5 | 6, 7 |
    When we find the median item by sorted position
    Then the median items value will be 4
    
Scenario: Using two sorted arrays locate the median item and return its value
    Given multiple series of integers:
    | 1, 2, 3, 5, 6, 7 | 4 |
    When we find the median item by sorted position
    Then the median items value will be 4
