
@tag
Feature: Purchase the order from Ecommerce site
  I want to use this template for my feature file
  
  Background:
  Given I landed on ecommerce Page

  @tag2
  Scenario Outline: Positive Test of Submitting the Order
    Given Login with username <UserName> and password <passWord>
    When add product <productName> to cart
    And Check product <productName> in cart
    And fill details in Payment detail page with CardNo <CardNo>,CVV <CVV>,Name <Name>,CountryName <CountryName>
    Then Confirmation Message is displayed in Confirmation page
   

    Examples: 
      |  UserName 								| passWord 				| productName   | CardNo							| CVV | Name  | CountryName |
      |  jothi.lakshmi@gmail.com  | Athvika@123     | ZARA COAT 3		| 0123 4567 5678 9876 | 567 | Jothi | India				|  
