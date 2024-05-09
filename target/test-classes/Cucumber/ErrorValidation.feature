
@tag
Feature: Error Validation
  I want to use this template for my feature file

 

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I landed on ecommerce page  
    Given Logging in with username<name> and password<password>
    Then "Incorrect email or password."message is displayed

    Examples: 
      | name                        | password  | 
      | sanjuhosmane60135@gmail.com | anju@0615 | 
    
