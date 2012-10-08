package com.axiomalaska.phenomena;

public class PhenomenonImp implements Phenomenon {
	// -------------------------------------------------------------------------
	// Private Data
	// -------------------------------------------------------------------------

	private String name = "";
	private String id = "";
	private String units = "";

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------
	public PhenomenonImp() {
	}
	
	public PhenomenonImp(String name, String id, String units) {
		super();
		this.name = name;
		this.id = id;
		this.units = units;
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
	 * The units that observations are measured in. For example 'm/s'
	 * 
	 * Maximum characters 30
	 * 
	 * If characters are over 30 they will be truncated to 30
	 */
	public String getUnits() {
		return units;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	public String toString(){
		return id;
	}
}
