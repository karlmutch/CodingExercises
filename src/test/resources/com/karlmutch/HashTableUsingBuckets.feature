Feature: Build and scan a hash table
  Exercise knowledge about buckets and hash tables 

Scenario: Using a hash table to extract Key Value pairs from hash table
    Given multiple key value pairs:
    | 1, A | 2, B |
    When we use the key "1" to search a hash map
    Then the retrieved value will be "A" 

Scenario: Using a hash table to extract Key Value pairs from hash table
    Given multiple key value pairs:
    | 3, C | 2, B |
    When we use the key "1" to search a hash map
    Then no value will be found 
    