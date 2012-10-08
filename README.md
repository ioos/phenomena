phenomena
=========

This project defines a Phenomenon class for use in identifying sampled or modelled phenomena
(typically collected by sensors or modelled). The Phenomena class defines static Phenomenon fields
that conform to the IOOS standard of using CF standard names where available and IOOS parameters
when a CF name doesn't exist. There are also a number of "homeless" phenomena that are not yet in
either vocabulary and should be further examined (to be mapped to the proper existing term or proposed
as a new candidate term in the appropriate vocabulary).

This project also generates a class with static ontology members for IOOS parameters using 
the Maven schemagen plugin/Apache Jena (see com.axiomalaska.phenomena.IoosParamterUtil).
