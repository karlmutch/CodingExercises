Feature: Simple knowledge question concerning sets and unique items within them
    Exercise knowledge of how to combine Set collection types as building blocks

Scenario: Take a collection of values and strip duplicates
    Given a series of strings:
    | First | Second | Third | Fourth | First |
    When stripping duplicates
    Then the collection will be left with
    | First | Second | Third | Fourth |

Scenario: Take a collection of values and strip duplicates in a stable manner
    Given a series of strings:
    | First | Second | Third | Fourth | First |
    When stripping, in a stable manner, any duplicates
    Then the collection will be left with
    | First | Second | Third | Fourth |

Scenario: Take a collection of values and detect duplicates using collection conversions
    Given a series of strings:
    | First | Second | Third | Fourth | First |
    When detecting duplicates using collection length
    Then the collection will be seen to contain duplicates

Scenario: Take a collection of values and detect duplicates using collection conversions
    Given a series of strings:
    | First | Second | Third | Fourth | First |
    When detecting duplicates using collection contents
    Then the collection will be seen to contain duplicates

Scenario: Take a collection of values and detect no duplicates using collection conversions
    Given a series of strings:
    | First | Second | Third | Fourth |
    When detecting duplicates using collection length
    Then the collection will be seen to contain no duplicates

Scenario: Take a collection of values and detect no duplicates using collection conversions
    Given a series of strings:
    | First | Second | Third | Fourth |
    When detecting duplicates using collection contents
    Then the collection will be seen to contain no duplicates
