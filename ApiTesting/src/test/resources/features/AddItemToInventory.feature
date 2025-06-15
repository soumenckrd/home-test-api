#Author:Soumen Mondal
@Regression
@addItemToInventory
Feature: Add an Item  to Inventory
 
  Scenario Outline: Add an Item  to Inventory
    Given User wants to add an new item details using POST api call for "<testCase>"
    Then User validate the api response <responseCode> for "<testCase>"
    
   Examples:
     
              | testCase            | responseCode |           
     # New ID generated based on data
              | POSITIVE            |  200         |            
     # Providing Existing ID
              | NEGATIVE            |  400         |
     # Missing Indo
              | NEGATIVE MISSINGINFO|  400         |
   

