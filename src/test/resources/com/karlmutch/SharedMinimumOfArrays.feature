Feature: Iterate arrays and look for a common minimum
  Exercise basic programming skills with iteration 

Scenario: Create two arrays with a shared minimum buried in the middle of both
    Given a series of strings:
    | 1, 2, 3, 6, 10, 20 | 4, 5, 6, 10, 20 |
    When searching for the minimum in two arrays
    Then the resulting minimum will be 6
