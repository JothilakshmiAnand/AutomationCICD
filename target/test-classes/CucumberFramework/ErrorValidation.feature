@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative Scenarion for Login
    Given I landed on ecommerce Page
    When Login with username <UserName> and password <passWord>
    Then Error message is displayed

    Examples: 
      |  UserName 								| passWord 				| 
      |  jothi.lakshmi@gmail.com  | Athvika@12     |
