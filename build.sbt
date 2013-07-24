name := "wiValidator"

version := "0.0.1"

organization := "es.weso"

scalaVersion := "2.10.1"

seq(cucumberSettings : _*)

cucumberJunitReport := true 

cucumberStepsBasePackage := "es.weso.wi.validator"

cucumberMaxMemory := "512M"