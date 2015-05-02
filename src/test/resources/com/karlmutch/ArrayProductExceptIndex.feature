Feature: Generate an array from the product of an inputs array excluding the original input from the products at each element
  Exercise knowledge of loops and a little optimization

Scenario: Generate products that will all be the same
    Given a series of integers:
    | 1 | 1 | 1 | 1 | 1 | 
    When the product of the array excluding individual items at their original positions is done
    Then a series of integers is produced:
    | 1 | 1 | 1 | 1 | 1 | 

Scenario: Generate products that will all be the same
    Given a series of integers:
    | 2 | 2 | 2 | 2 | 2 | 
    When the product of the array excluding individual items at their original positions is done
    Then a series of integers is produced:
    | 16 | 16 | 16 | 16 | 16 | 

Scenario: Generate products that will test that every position is done correctly by using prime numbers
    Given a series of integers:
    | 311 | 313 | 317 | 331 | 337 | 
    When the product of the array excluding individual items at their original positions is done
    Then a series of integers is produced:
    | 11067804887 | 10997084089 | 10858319621 | 10399055347 | 10213908961 | 
