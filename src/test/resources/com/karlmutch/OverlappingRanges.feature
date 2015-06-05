Feature: A collection of ranges with some algorithms added as a complex interview example  
    Exercise ability to implement a data type with logic

Scenario: Create and output a simple range
    Given an empty range
    When adding a range starting at 5 and ending at 10 to the original range
    Then the resulting range will be "{5, 10}"
    
Scenario: Create a combination of ranges and check insertion without compression
    Given an empty range
    When adding a range starting at 5 and ending at 10 to the original range
    And adding a range starting at 15 and ending at 20 to the original range
    Then the resulting range will be "{5, 10}, {15, 20}"

Scenario: Create a combination of ranges a with compression doing nothing when invoked
    Given an empty range
    When adding a range starting at 5 and ending at 10 to the original range
    And adding a range starting at 15 and ending at 20 to the original range
    And merging the ranges so far
    Then the resulting range will be "{5, 10}, {15, 20}"

Scenario: Create a combination of adjacent ranges but not combined as they are not occluded
    Given an empty range
    When adding a range starting at 5 and ending at 10 to the original range
    And adding a range starting at 11 and ending at 20 to the original range
    And merging the ranges so far
    Then the resulting range will be "{5, 10}, {11, 20}"
    
Scenario: Create a combination of ranges adjacent and occluded are combined
    Given an empty range
    When adding a range starting at 5 and ending at 10 to the original range
    And adding a range starting at 10 and ending at 20 to the original range
    And merging the ranges so far
    Then the resulting range will be "{5, 20}"

Scenario: Create a combination of ranges were one completely covers another
    Given an empty range
    When adding a range starting at 5 and ending at 20 to the original range
    And adding a range starting at 10 and ending at 15 to the original range
    And merging the ranges so far
    Then the resulting range will be "{5, 20}"

Scenario: Create a combination of ranges were one completely covers another
    Given an empty range
    When adding a range starting at 10 and ending at 15 to the original range
    And adding a range starting at 5 and ending at 20 to the original range
    And merging the ranges so far
    Then the resulting range will be "{5, 20}"
