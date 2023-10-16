@Registration
Feature: Digital Bank Registration Page

  Background:
    Given the following user with email "test@gmail.com" is not in the DB
    And  user navigates to Digital Bank signup page


  @Test
  Scenario: Positive case. As I user I want successfully create Digital Bank Account

    When user creates an account with following fields
      | title | firstName | lastName | gender | dob        | ssn         | email          | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-55-6798 | test@gmail.com | Tester123 | 12 Main St | City     | CA     | 99921      | US      | 2145678765 | 2146767990  | 2144567891 | true           |
    Then User should see a message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      | title | firstName | lastName | gender | dob        | ssn         | email          | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | accountNonExp | accountNonLocked | credentialsNonExp | enabled |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-55-6798 | test@gmail.com | Tester123 | 12 Main St | City     | CA     | 99921      | US      | 2145678765 | 2146767990  | 2144567891 | true          | true             | true              | true    |


  @NegativeRegistrationCases
  Scenario Outline: Negative case. Users need to provide all data

    Given  user navigates to Digital Bank signup page
    When user creates an account with following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> |
    Then User should see "<fieldWithError>" required field error message "<errorMessage>"

    Examples:
      | title | firstName | lastName | gender | dob        | ssn         | email             | password | address | locality | region | postalCode | country | homePhone | mobilePhone | workPhone | termsCheckMark | fieldWithError | errorMessage                        |
      |       |           |          |        |            |             |                   |          |         |          |        |            |         |           |             |           |                | title          | Please select an item in the list.  |
      | Mr.   |           |          |        |            |             |                   |          |         |          |        |            |         |           |             |           |                | firstName      | Please fill out this field.         |
      | Mr.   | Jack      |          |        |            |             |                   |          |         |          |        |            |         |           |             |           |                | lastName       | Please fill out this field.         |
      | Mr.   | Jack      | Test     |        |            |             |                   |          |         |          |        |            |         |           |             |           |                | gender         | Please select one of these options. |
      | Mr.   | Jack      | Test     | M      |            |             |                   |          |         |          |        |            |         |           |             |           |                | dob            | Please fill out this field.         |
      | Mr.   | Jack      | Test     | M      | 03/06/1985 |             |                   |          |         |          |        |            |         |           |             |           |                | ssn            | Please fill out this field.         |
      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 |                   |          |         |          |        |            |         |           |             |           |                | email          | Please fill out this field.         |
      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com |          |         |          |        |            |         |           |             |           |                | password       | Please fill out this field.         |
#      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com | Test123  |           |            |        |            |         |            |             |            |                | address        | Please fill out this field.                   |
#      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com | Test123  | 1 Main St |            |        |            |         |            |             |            |                | locality       | Please fill out this field.                   |
#      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com | Test123  | 1 Main St | New Jersey | NJ     |            |         |            |             |            |                | postalCode     | Please fill out this field.                   |
#      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com | Test123  | 1 Main St | New Jersey | NJ     | 90202      |         |            |             |            |                | country        | Please fill out this field.                   |
#      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com | Test123  | 1 Main St | New Jersey | NJ     | 90202      | USA     |            |             |            |                | homePhone      | Please fill out this field.                   |
#      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com | Test123  | 1 Main St | New Jersey | NJ     | 90202      | USA     | 2347633847 |             |            |                | mobilePhone    | Please fill out this field.                   |
#      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com | Test123  | 1 Main St | New Jersey | NJ     | 90202      | USA     | 2347633847 | 2347633847  |            |                | workPhone      | Please fill out this field.                   |
#      | Mr.   | Jack      | Test     | M      | 03/06/1985 | 234-56-3456 | test123@gmail.com | Test123  | 1 Main St | New Jersey | NJ     | 90202      | USA     | 2347633847 | 2347633847  | 3348765647 |                | termsCheckMark | Please check this box of you want to proceed. |
