Feature: Login page functionality

  @negative
  Scenario Outline: Correct error message is when signing in with invalid credentials
    Given Login page is opened
    When login credentials ("<email>" and "<password>") are entered
    And Login button is clicked
    Then error message appears

    Examples:
      | email         | password |
      | test@test.com | 123abc   |
      | test@test.com | abc123   |