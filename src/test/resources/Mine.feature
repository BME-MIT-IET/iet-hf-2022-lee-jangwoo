Feature: Mine
  Scenario: Mine is successful
    Given Load "game5.txt" successfully
    When active user "drill"
    When active user "mine"
    Then mine is successful

  Scenario: Mine is successful
    Given Load "game5.txt" successfully
    When active user "mine"
    Then mine is unsuccessful