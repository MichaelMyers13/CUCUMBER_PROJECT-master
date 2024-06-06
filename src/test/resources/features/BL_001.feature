Feature: Login as a user to the main page

  @BL_011_EM
  Scenario Outline: As a user I should be able to login
    Given "<user>" open the BL main page
    Then User put invalid credential
    Then User has to see invalid login msg
    Examples:
      | user  |
      | TEST1 |
      | TEST2 |

