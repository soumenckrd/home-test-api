#Author:Soumen Mondal

@Regression
@getInventoryDetailsById
Feature: Get Inventory Details By ID
 
  Scenario Outline: Get Inventory Details By ID
    Given User wants to fetch the Inventory details filtering by "<Id>" using GET api call 
    And User validate the api response
    Then User validate the response contains correct Id "<Id>" Name "<Name>" Price "<Price>" and Image "<Image>"
    
    
    Examples:
             | Id |    Name           | Price | Image    |
             | 3  |Baked Rolls x 8    | $10   | roll.png |

