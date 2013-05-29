Feature: Cucumber
  In order to validate the "WorldBank" sources
  As a machine
  I want to be able to validate the results

  Scenario: Validate WorldBank Data
    
  	Given I want to check the indicator "AG.LND.IRIG.AG.ZS" for "AFG" in "2001"
  	When I check the original WorlBank source
    Then the value should be "5.6684237014277"
    
    Given I want to check the indicator "AG.LND.IRIG.AG.ZS" for "HRV" in "2010"
  	When I check the original WorlBank source
    Then the value should be "1.08711950817214"
    
  	Given I want to check the indicator "AG.LND.IRIG.AG.ZS" for "NOC" in "1962"
  	When I check the original WorlBank source
    Then it should raise an Exception