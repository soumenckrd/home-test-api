#Author:Soumen Mondal
@Regression
@addedItemPresentInventory
Feature: Added Item Present In Inventory
 
  Scenario Outline: Added Item Present In Inventory
    Given User wants to add an new item details using POST api call for "<testCase>"
    Then User validate the api response <responseCode> for "<testCase>"
    And Validate the added item details
    
   Examples:
     
              | testCase            | responseCode |           
              | POSITIVE            |  200         |           
   

