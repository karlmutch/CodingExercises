Feature: Prime Number Finder
  Exercise knowledge of tip and tricks around testing for prime numbers


Scenario: Print Simple Prime Sequence
    Given a number range from 1 to 20
    When requesting the known primes
    Then the sequence of numbers "2, 3, 5, 7, 11, 13, 17, 19" should be seen
    
Scenario: Print Range bound Prime Sequence
    Given a number range from 18 to 100
    When requesting the known primes
	Then the sequence of numbers "19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97" should be seen