
@tag
Feature: Purchase the order from Ecommerce WebSite
  I want to use this template for my feature file

Background:
Given I landed on ecommerce page

  @Regression
  Scenario Outline: Positive Test of submitting the Order
    Given Logging in with username<name> and password<password>
    When Add product <productName> to the cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                        | password   | productName  |
      | sanjuhosmane60135@gmail.com | Sanju@0615 | ZARA COAT 3  |
    
