package com.axiomalaska.phenomena;

import ucar.units.Unit;

public class PhenomenonImp implements Phenomenon {
	// -------------------------------------------------------------------------
	// Private Data
	// -------------------------------------------------------------------------

	private String name = "";
	private String id = "";
        private String tag = "";
	private Unit unit = null;

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------
	public PhenomenonImp() {
	}
        
        public PhenomenonImp(String name, String id, String tag, Unit unit) {
            super();
            this.name = name;
            this.id = id;
            this.tag = tag;
            this.unit = unit;
        }
	
	public PhenomenonImp(String name, String id, Unit unit) {
		super();
		this.name = name;
		this.id = id;
		this.unit = unit;
	}	

	//dimensionless phenomenon
    public PhenomenonImp(String name, String id) {
        super();
        this.name = name;
        this.id = id;
    }   
	
	// -------------------------------------------------------------------------
	// Public Members
	// -------------------------------------------------------------------------

	/**
	 * The name of the phenomenon. For example 'Wind Speed'
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * The defining tag for the phenomenon. For example urn:x-ogc:def:phenomenon:IOOS:0.0.1:wind_speed
	 * 
	 * Maximum characters 100
	 * 
	 * If characters are over 100 they will be truncated to 100
	 */
	public String getId() {
		return id;
	}

	/**
	 * The ucar.units.Unit that observations are measured in. For example 'm.s-1' 
	 */
	public Unit getUnit() {
		return unit;
	}
        
        public String getTag() {
            return tag;
        }

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public String toString(){
		return id;
	}
}
