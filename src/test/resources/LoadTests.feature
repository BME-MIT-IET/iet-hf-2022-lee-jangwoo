Feature: Load map
  Scenario: Load game5.txt successfully
    Given the running game
    When the user tries to load game5.txt
    Then it loads successfully

  Scenario: Load game5fail.txt unsuccessfully
    Given the running game
    When the user tries to load game5fail.txt
    Then it loads unsuccessfully