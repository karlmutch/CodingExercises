Feature: Counting Digits in an Integer
  Exercise knowledge of simple math operations

Scenario: Counting 1's digits in the middle of a decimal number
    Given a number 20102
    When counting the ones digits
    Then a total of 1 ones will be found
    
Scenario: Counting 1's digits at the ends of a decimal number
    Given a number 10001
    When counting the ones digits
    Then a total of 2 ones will be found
    
Scenario: Counting 1's digits at the ends and in the middle of a decimal number
    Given a number 10101
    When counting the ones digits
    Then a total of 3 ones will be found