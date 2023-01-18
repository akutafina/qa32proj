Feature: Demo of using step definitions from different Steps files keeping same context (same WD)

  Scenario Outline: Correct error message is when signing in with invalid credentials
    Given Login page is opened
    And All products page is opened
    Then All products page is loaded
    And Login page is opened
    And login credentials ("<email>" and "<password>") are entered

    Examples:
      | email         | password |
      | test@test.com | 123abc   |