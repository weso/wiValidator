Feature: Cucumber
  In order to validate the "WorldBank" sources
  As a machine
  I want to be able to validate the results

  Scenario: Validate WorldBank Data
    
  	Given I want to check the FreedomPress indicator "AG.LND.IRIG.AG.ZS" for "CAN" in "2005"
  	When I check the original "FreedomPress" source
    Then the value should be "4.5"
    
    Given I want to check the FreedomPress indicator "AG.LND.IRIG.AG.ZS" for "BEL" in "2003"
  	When I check the original "FreedomPress" source
    Then the value should be "1.17"
    
  	Given I want to check the FreedomPress indicator "AG.LND.IRIG.AG.ZS" for "Narnia" in "1962"
  	When I check the original "FreedomPress" source
    Then it should raise an Exception