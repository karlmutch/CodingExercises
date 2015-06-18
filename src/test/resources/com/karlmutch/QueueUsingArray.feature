Feature: Use an array as a backing store for a simple FIFO queue
    Exercise knowledge of arrays and indexes to implement a simple fixed capacity queue

Scenario: Perform simple all in with no pop for a FIFO
	Given an array of 3 entries backing a queue
	When pushing "A" into the queue expecting it to succeed
	When pushing "B" into the queue expecting it to succeed
	When pushing "C" into the queue expecting it to succeed
	Then the remaining queue will contain
	| A | B | C |
	
Scenario: Perform simple all in all out for a FIFO 
	Given an array of 3 entries backing a queue
	When pushing "A" into the queue expecting it to succeed
	When pushing "B" into the queue expecting it to succeed
	When pushing "C" into the queue expecting it to succeed
	When pushing "D" into the queue expecting it to fail
	Then the remaining queue will contain
	| A | B | C |

Scenario: Perform simple pushes and a single pop for a FIFO 
	Given an array of 3 entries backing a queue
	When pushing "A" into the queue expecting it to succeed
	When pushing "B" into the queue expecting it to succeed
	When pushing "C" into the queue expecting it to succeed
	When popping an item from the queue expecting it to be "A"
	When pushing "D" into the queue expecting it to succeed
	Then the remaining queue will contain
	| B | C | D |

Scenario: Perform simple all in all out for a FIFO 
	Given an array of 3 entries backing a queue
	When pushing "A" into the queue expecting it to succeed
	When pushing "B" into the queue expecting it to succeed
	When pushing "C" into the queue expecting it to succeed
	When popping an item from the queue expecting it to be "A"
	When popping an item from the queue expecting it to be "B"
	When popping an item from the queue expecting it to be "C"
	Then the remaining queue will be empty


Scenario: Perform simple all in all out for a FIFO 
	Given an array of 3 entries backing a queue
	When pushing "A" into the queue expecting it to succeed
	When pushing "B" into the queue expecting it to succeed
	When pushing "C" into the queue expecting it to succeed
	When popping an item from the queue expecting it to be "A"
	When popping an item from the queue expecting it to be "B"
	When popping an item from the queue expecting it to be "C"
	When pushing "D" into the queue expecting it to succeed
	When pushing "E" into the queue expecting it to succeed
	When pushing "F" into the queue expecting it to succeed
	Then the remaining queue will contain
	| D | E | F |
	
Scenario: Perform simple all in all out for a FIFO 
	Given an array of 3 entries backing a queue
	When pushing "A" into the queue expecting it to succeed
	When pushing "B" into the queue expecting it to succeed
	When pushing "C" into the queue expecting it to succeed
	When popping an item from the queue expecting it to be "A"
	When popping an item from the queue expecting it to be "B"
	When popping an item from the queue expecting it to be "C"
	When pushing "D" into the queue expecting it to succeed
	When pushing "E" into the queue expecting it to succeed
	When pushing "F" into the queue expecting it to succeed
	When popping an item from the queue expecting it to be "D"
	When popping an item from the queue expecting it to be "E"
	When popping an item from the queue expecting it to be "F"
	Then the remaining queue will be empty
