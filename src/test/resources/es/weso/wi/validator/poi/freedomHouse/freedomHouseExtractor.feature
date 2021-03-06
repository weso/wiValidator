Feature: Cucumber
  In order to validate the "FreedomHouse" sources
  As a machine
  I want to be able to validate the results

  Scenario: Validate FreedomHouse Data
    
  	Given I want to check the FreedomHouse indicator "PR" for "CHL" in "2007"
  	When I check the original "FreedomHouse" source
    Then the value should be "39"
    
    Given I want to check the FreedomHouse indicator "CL" for "SDN" in "2013"
  	When I check the original "FreedomHouse" source
    Then the value should be "5"
    
  	Given I want to check the FreedomHouse indicator "CL" for "SRB" in "2003"
  	When I check the original "FreedomHouse" source
    Then it should raise an Exception