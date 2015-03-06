Feature: Used a combination of two data structures to create a third
    Exercise knowledge of queues to implement a stack

Scenario: Perform simple all in all out for a FIFO waiting at the start
    Given a series of strings:
    | 2000 | First | Second | Third | Fourth | Fifth |
    When pushing and popping items off a stack
    Then the popped items will be:
    | 2000 | First | Second | Third | Fourth | Fifth |
    
Scenario: Perform simple all in all out for a FIFO
    Given a series of strings:
    | First | Second | Third | Fourth | Fifth | 4000 |
    When pushing and popping items off a stack
    Then the popped items will be:
    | First | Second | Third | Fourth | Fifth | 4000 |

Scenario: Perform simple all in all out for a FIFO without waiting
    Given a series of strings:
    | First | Second | Third | Fourth | Fifth |
    When pushing and popping items off a stack
    Then the popped items will be:
    | First | Second | Third | Fourth | Fifth |
    
Scenario: Perform simple all in all out for a FIFO with waiting in the middle
    Given a series of strings:
    | First | Second | 3000 | Third | Fourth | 
    When pushing and popping items off a stack
    Then the popped items will be:
    | First | Second | 3000 | Third | Fourth | 
    