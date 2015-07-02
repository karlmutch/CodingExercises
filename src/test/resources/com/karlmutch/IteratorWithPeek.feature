Feature: Iterate a collection using a custom iterator supporting a peek operation
  Simply test very simple prgramming skills, not very useful

Scenario: Peek every item in collection being iterated
    Given a series of strings:
    | First | Second | Third | Fourth |
    When we peek before iteration step numbers
    | 0 | 1 | 2 | 3 |
    Then the observed values will be
    | First | First | Second | Second | Third | Third | Fourth | Fourth |
      
Scenario: Peek First and Last items in collection being iterated
    Given a series of strings:
    | First | Second | Third | Fourth |
    When we peek before iteration step numbers
    | 0 | 3 |
    Then the observed values will be
    | First | First | Second | Third | Fourth | Fourth |
      

Scenario: Peek middle items in collection being iterated
    Given a series of strings:
    | First | Second | Third | Fourth |
    When we peek before iteration step numbers
    | 1 | 2 |
    Then the observed values will be
    | First | Second | Second | Third | Third | Fourth |
      