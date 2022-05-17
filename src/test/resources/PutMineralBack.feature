Feature: PutMineralBack
  Scenario: PutMineralBack is successful
    Given Load "game5.txt" successfully
    When active user "drill"
    When active user "move 0"
    When active user "mine"
    When active user "mine"
    When active user "putmineralback 1"
    Then putmineralback is successful

  Scenario: PutMineralBack is unsuccessful
    Given Load "game6.txt" successfully
    When active user "putmineralback 1"
    Then putmineralback is unsuccessful