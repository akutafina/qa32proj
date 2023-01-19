Feature: Demo of using step definitions from different Steps files keeping same context (same WD)

#  @demo
  Scenario Outline: Correct error message is when signing in with invalid credentials
    Given Login page is opened
    And login credentials ("<email>" and "<password>") are entered
    And All products page is opened
    Then All products page is loaded

    Examples:
      | email         | password |
      | test@test.com | 123abc   |