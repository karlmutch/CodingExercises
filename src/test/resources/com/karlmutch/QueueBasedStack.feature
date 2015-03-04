Feature: Used a combination of two data structures to create a third
    Exercise knowledge of queues to implement a stack

Scenario: Perform simple all in all out for a FIFO
    Given a series of strings:
    | First | Second | Third | Fourth | Fifth | 5000 |
    When pushing and popping items off a stack
    Then the popped items will be:
    | First | Second | Third | Fourth | Fifth | 5000 |
