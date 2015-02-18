Feature: Use the monitor features of core Java object to implement thread safe ring buffer
    Exercise knowledge of very old Java 2 feature related to the Monitor functionality 
    of Java Object, dated 'you forgot about x gotcha'

Scenario: Use a single object (Ring Buffer) to implement a producer / consumer but transfer nothing
    Given a string ""
    When running a producer and consumer
    Then the output string will be the same

Scenario: Use a single object (Ring Buffer) to implement a producer / consumer
    Given a string "0123456789"
    When running a producer and consumer
    Then the output string will be the same

Scenario: Use a single object (Ring Buffer) to implement a producer / consumer
    Given a random string 1 characters long
    When running a producer and consumer
    Then the output string will be the same

Scenario: Use a single object (Ring Buffer) to implement a producer / consumer
    Given a random string 10 characters long
    When running a producer and consumer
    Then the output string will be the same

Scenario: Use a single object (Ring Buffer) to implement a producer / consumer
    Given a random string 8192 characters long
    When running a producer and consumer
    Then the output string will be the same
