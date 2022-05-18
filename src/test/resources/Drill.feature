Feature: Drill
  Scenario: Drill is successful
    Given Load "game5.txt" successfully
    When active user "drill"
    Then drill is successful

  Scenario: Drill is unsuccessful
    Given Load "game5.txt" successfully
    When active user "drill"
    When active user "drill"
    Then second drill is unsuccessful