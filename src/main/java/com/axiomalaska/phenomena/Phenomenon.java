package com.axiomalaska.phenomena;

import ucar.units.Unit;

/**
 * Contains information for a phenomenon. 
 * 
 * Examples of phenomena are:
 * 		Air temperature
 * 		Wind speed
 * 		Wind direction
 * 
 * @author Lance Finfrock
 */
public interface Phenomenon {
	
	/**
	 * The name of the phenomenon. For example 'Wind Speed'
	 * @return
	 */
	public String getName();

	/**
	 * The defining tag for the phenomenon. For example urn:x-ogc:def:phenomenon:IOOS:0.0.1:wind_speed
	 * 
	 * Maximum characters 100
	 * 
	 * If characters are over 100 they will be truncated to 100
	 */
	public String getId();

	/**
	 * The ucar.units.Unit that observations are measured in. For example 'm.s-1'
	 */
	public Unit getUnit();
        
        public String getTag();
}
