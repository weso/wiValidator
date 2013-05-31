Feature: Cucumber
  In order to validate the "ITU" sources
  As a machine
  I want to be able to validate the results

  Scenario: Validate FreedomHouse Data
    
  	Given I want to check the ITU indicator "PR" for "Chile" in "2007"
  	When I check the original ITU source
    Then the original ITU value should be "39"
    
    Given I want to check the ITU indicator "CL" for "Sudan" in "2013"
  	When I check the original ITU source
    Then the original ITU value should be "5"
    
  	Given I want to check the ITU indicator "CL" for "Serbia" in "2003"
  	When I check the original ITU source
    Then it should raise an Exception