Feature: Sort an array of strings using an insertion sort implementation
  Exercise knowledge about other forms of sorting that combine multiple tactics 

Scenario: Do a simple insertion sort
    Given a series of strings:
    | First | Second | Third | Fourth |
    When sorting using an insertion sort
    Then the sorted collection will be
    | First | Fourth | Second | Third |      
     
Scenario: Do a simple optimized insertion sort
    Given a series of strings:
    | First | Second | Third | Fourth |
    When sorting using an optimized insertion sort
    Then the sorted collection will be
    | First | Fourth | Second | Third | 
   
Scenario: Do a longer optimized insertion sort
    Given a series of 32000 items containing random big integer strings
    When sorting using an optimized insertion sort
    Then the result will be a sorted collection
    
Scenario: Do a longer unoptimized insertion sort
    Given a series of 32000 items containing random big integer strings
    When sorting using an insertion sort
    Then the result will be a sorted collection