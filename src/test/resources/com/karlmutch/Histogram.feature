Feature: Test histogram percentile calculations 
  Exercise knowledge about sorted data and data conversions 

Scenario: Base test case from the original question
    Given a series of integers:
    | 709 | 521 | 744 | 406 | 970 |
    When calculating the 80th percentile
    Then the result will be 700
