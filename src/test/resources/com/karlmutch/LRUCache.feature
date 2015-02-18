Feature: Leverage a Least Recently Used Cache
    Exercise knowledge of how to combine collection types as building blocks, Linked Lists and Maps

Scenario: Fill a cache and check appropriate entries were ejected
    Given a series of strings:
    | First | Second | Third | Fourth |
    When adding the series to an LRU cache with a size of 4
    Then adding the item "Fifth" will result in:
    | Second | Third | Fourth | Fifth |

    
Scenario: Partially populate a cache and check no entries were ejected
    Given a series of strings:
    | First | Second | Third | Fourth |
    When adding the series to an LRU cache with a size of 5
    Then adding the item "Fifth" will result in:
    | First | Second | Third | Fourth | Fifth |
