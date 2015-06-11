Feature: Leverage several smaller techniques to implement a distributed approach to determining TopK 
    Exercise knowledge of how to combine hashing, recent advances in TopK and threading to demonstrate one approach to TopK

Scenario: Create a scenario with multiple simulated machines and aggregate individual TopK results
    Given a series of strings:
    | X | X | Y | Z | A | B | C | X | X | A | C | A | A |
    When using a distributed TopK with 5 hosts and a k of 3
    Then the sorted TopK will be
    | X | A | C |

