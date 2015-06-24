Feature: Traverse a series of vectors to construct tuples
  Exercise knowledge of recursion

Scenario: Check a simple set of three vectors for tuple creation
    Given a vector of integers
    | 1 | 2 | 3 |
    Given a vector of integers
    | 4 | 5 |
    Given a vector of integers
    | 6 | 7 | 8 |
    When creating the tuple set
    Then the printed tuple output will be "( 1, 4, 6 )( 1, 4, 7 )( 1, 4, 8 )( 1, 5, 6 )( 1, 5, 7 )( 1, 5, 8 )( 2, 4, 6 )( 2, 4, 7 )( 2, 4, 8 )( 2, 5, 6 )( 2, 5, 7 )( 2, 5, 8 )( 3, 4, 6 )( 3, 4, 7 )( 3, 4, 8 )( 3, 5, 6 )( 3, 5, 7 )( 3, 5, 8 )"

