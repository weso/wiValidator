Feature: Cucumber
  In order to validate the "ITU" sources
  As a machine
  I want to be able to validate the results

  Scenario: Validate ITU Data
    
  	Given I want to check the ITU indicator "ITUH" which name is in cell "B1" and goes from column "1" to "12", for "AGO" in "2009"
  	When I check the original "ITU" source
    Then the value should be "6"
    
    Given I want to check the ITU indicator "ITUB" which name is in cell "O1" and goes from column "14" to "25", for "TGO" in "2011"
  	When I check the original "ITU" source
    Then the value should be "0.0779877471500759"
    
  	Given I want to check the ITU indicator "ITUB" which name is in cell "O1" and goes from column "14" to "25", for "TJK" in "2006"
  	When I check the original "ITU" source
    Then it should raise an Exception