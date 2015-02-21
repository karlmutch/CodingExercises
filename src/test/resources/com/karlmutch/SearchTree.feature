Feature: Perform Binary Search Tree operations
  Exercise mechanical binary tree knowledge 

Scenario: Populate a single value
    Given a string "1,1"
    When populating a binary search tree
    Then the resulting levels will be:
    |1|

Scenario: Populate the simplest of trees
    Given a string "1,1 2,2 3,3"
    When populating a binary search tree
    Then the resulting levels will be:
    |2|1 3|

    
Scenario: Populate a three level tree
    Given a string "1,1 2,2 3,3 4,4 5,5 6,6 7,7"
    When populating a binary search tree
    Then the resulting levels will be:
    |4| 2   6| 1 3 5 7|

Scenario: Populate a four level tree
    Given a string "1,1 2,2 3,3 4,4 5,5 6,6 7,7 8,8 9,9 10,10"
    When populating a binary search tree
    Then the resulting levels will be:
    |5| 2       8| 1   3   6   9| 4   7   10|

Scenario: Populate a four level tree
    Given a string "1,1 2,2 3,3 4,4 5,5 6,6 7,7 8,8 9,9 10,10 11,11 12,12"
    When populating a binary search tree
    Then the resulting levels will be:
    |6| 3       9| 1   4   7   11| 2   5   8 10 12|
