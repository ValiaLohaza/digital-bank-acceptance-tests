Feature: Creating a new checking account

  Scenario: Create a standard individual checking account

    Given the user enters login "1234567@gmail.com" and password "Valia1996"
    When the user creates a new Checking account with the following data
      | checkingAccountType | accountOwnership | accountName         | initialDepositAmount |
      | Standard Checking   | Individual       | Valentyna's account | 100000.0             |
    Then the user should see the message "Successfully created new Standard Checking account named Valentyna's account" message
    And the user should see newly added account cart
      | accountName         | accountType       | ownership  | accountNumber | interestRate | balance    |
      | Valentyna's account | Standard Checking | Individual | 486131435     | 0.0%         | $100000.00 |
    And the user should see the following transactions
      | date       | category | description                | amount    | balance   |
      | 2023-09-10 | Income   | 845322140 (DPT) - Deposit  | 100000.00 | 100000.00 |

