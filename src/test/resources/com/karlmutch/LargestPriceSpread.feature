Feature: Test trading scenarios 
  Exercise knowledge about bounds checking with lookaside 


Scenario: Price all day up 
    Given a series of integers:
    | 100 | 101 | 102 | 103 | 104 | 
    When searching for the best trade
    Then a buy at 0 minutes at a price of 100, and sell at 4 minutes at a price of 104 will be found
    
Scenario: Price all day up 
    Given a series of integers:
    | 100 | 100 | 100 | 100 | 104 | 
    When searching for the best trade
    Then a buy at 0 minutes at a price of 100, and sell at 4 minutes at a price of 104 will be found

Scenario: Price all day up 
    Given a series of integers:
    | 100 | 104 | 104 | 104 | 104 | 
    When searching for the best trade
    Then a buy at 0 minutes at a price of 100, and sell at 1 minutes at a price of 104 will be found

Scenario: Price all day down 
    Given a series of integers:
    | 100 | 99 | 98 | 97 | 96 | 
    When searching for the best trade
    Then no profitable trade will be found 
    
Scenario: Price all day down 
    Given a series of integers:
    | 100 | 98 | 98 | 98 | 96 | 
    When searching for the best trade
    Then no profitable trade will be found 

Scenario: Price all day down 
    Given a series of integers:
    | 100 | 98 | 98 | 98 | 98 | 
    When searching for the best trade
    Then no profitable trade will be found 

Scenario: Price all day down 
    Given a series of integers:
    | 100 | 100 | 98 | 98 | 98 | 
    When searching for the best trade
    Then no profitable trade will be found 

Scenario: Price all day unchanged 
    Given a series of integers:
    | 98 | 98 | 98 | 98 | 98 | 
    When searching for the best trade
    Then no profitable trade will be found 
    
Scenario: Price swings
    Given a series of integers:
    | 100 | 99 | 100 | 100 | 100 | 
    When searching for the best trade
    Then a buy at 1 minutes at a price of 99, and sell at 2 minutes at a price of 100 will be found
 
Scenario: Price swings
    Given a series of integers:
    | 99 | 99 | 100 | 100 | 100 | 
    When searching for the best trade
    Then a buy at 0 minutes at a price of 99, and sell at 2 minutes at a price of 100 will be found

Scenario: Price swings, latest is the largest
    Given a series of integers:
    | 100 | 99 | 100 | 98 | 100 | 
    When searching for the best trade
    Then a buy at 3 minutes at a price of 98, and sell at 4 minutes at a price of 100 will be found

Scenario: Price swings, earliest is the largest
    Given a series of integers:
    | 100 | 98 | 100 | 99 | 100 | 
    When searching for the best trade
    Then a buy at 1 minutes at a price of 98, and sell at 2 minutes at a price of 100 will be found

Scenario: Rolling Price swings, earliest is the largest
    Given a series of integers:
    | 100 | 98 | 120 | 95 | 101 | 
    When searching for the best trade
    Then a buy at 1 minutes at a price of 98, and sell at 2 minutes at a price of 120 will be found

Scenario: Rolling Price swings, earliest is the largest
    Given a series of integers:
    | 100 | 98 | 120 | 99 | 120 | 
    When searching for the best trade
    Then a buy at 1 minutes at a price of 98, and sell at 2 minutes at a price of 120 will be found

Scenario: Rolling Price swings, latest is the largest
    Given a series of integers:
    | 100 | 98 | 120 | 97 | 120 | 
    When searching for the best trade
    Then a buy at 3 minutes at a price of 97, and sell at 4 minutes at a price of 120 will be found

Scenario: Rolling Price swings, latest is the largest
    Given a series of integers:
    | 100 | 98 | 120 | 95 | 119 | 
    When searching for the best trade
    Then a buy at 3 minutes at a price of 95, and sell at 4 minutes at a price of 119 will be found

Scenario: Successively increasing Price swings
    Given a series of integers:
    | 100 | 95 | 120 | 100 | 125 | 
    When searching for the best trade
    Then a buy at 1 minutes at a price of 95, and sell at 4 minutes at a price of 125 will be found

Scenario: Successively increasing Price swings
    Given a series of integers:
    | 100 | 95 | 120 | 96 | 125 | 
    When searching for the best trade
    Then a buy at 1 minutes at a price of 95, and sell at 4 minutes at a price of 125 will be found

