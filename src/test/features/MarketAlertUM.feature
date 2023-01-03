Feature: MarketAlertUM System

  Scenario: User logs in successfully
    Given the user has a valid login
    When the user logs in
    Then the system should allow the user to log in

  Scenario: User logs in with invalid credentials
    Given the user has an invalid login
    When the user logs in
    Then the system should not allow the user to log in

  Scenario: User views alerts
    Given the user is logged in
    When the user views the alerts
    Then the system should display the alerts to the user

  Scenario: User logs out
    Given the user is logged in
    When the user logs out
    Then the system should log the user out


