Feature: Test a custom sort scenarios 
  Exercise knowledge about comparators and comparables 

Scenario: Base test case from the original question
    Given a series of integers:
    | 3 | 30 | 9 | 14 | 1 | 
    When sorting to create the largest possible number from the integers
    Then the sorted list will be
    | 9 | 3 | 30 | 14 | 1 |

Scenario: Test case found on Internet
    Given a series of integers:
    | 54 | 546 | 548 | 60 | 
    When sorting to create the largest possible number from the integers
    Then the sorted list will be
    | 60 | 548 | 546 | 54 |

Scenario: Test case found on Internet
    Given a series of integers:
    | 94 | 946 | 948 | 
    When sorting to create the largest possible number from the integers
    Then the sorted list will be
    | 94 | 948 | 946 |

Scenario: Test case found on Internet
    Given a series of integers:
    | 9 | 9 | 9 | 91 | 9 | 
    When sorting to create the largest possible number from the integers
    Then the sorted list will be
    | 9 | 9 | 9 | 9 | 91 |

Scenario: Test case found on Internet
    Given a series of integers:
    | 1 | 34 | 3 | 98 | 9 | 76 | 45 | 4 | 
    When sorting to create the largest possible number from the integers
    Then the sorted list will be
    | 9 | 98 | 76 | 45 | 4 | 34 | 3 | 1 |
