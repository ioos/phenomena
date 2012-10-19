package com.axiomalaska.phenomena;

import java.util.HashMap;
import java.util.Map;

import ucar.units.SI;
import ucar.units.Unit;
import ucar.units.UnitFormat;
import ucar.units.UnitFormatManager;

/**
 * Class for resolving a string to a ucar.units.Unit. String parsing isn't always reliable in ucar.unit
 * (for instance, "degree" returns a ScaledUnit with a base unit of radian instead of a DerivedUnit
 * based on degree), so we detect certain special cases here before falling back to string parsing.
 */
public class UnitResolver {    
    private final UnitFormat unitFormat = UnitFormatManager.instance();
    private final CustomUnits customUnits;
    private final Map<String,Unit> unitMap;
    
    private static UnitResolver instance;
    
    private UnitResolver() throws UnitCreationException{
        customUnits = CustomUnits.instance();
        
        unitMap = new HashMap<String,Unit>();
        unitMap.put( "degree", SI.ARC_DEGREE );
        unitMap.put( "degrees from N", SI.ARC_DEGREE );
        unitMap.put( "degrees_true", SI.ARC_DEGREE );
        unitMap.put( "W m-2", customUnits.WATTS_PER_SQUARE_METER );
        unitMap.put( "ug L-1", customUnits.MICROGRAMS_PER_LITER );
        unitMap.put( "ug L-1 as P", customUnits.MICROGRAMS_PER_LITER );
        unitMap.put( "ug L-1 as N", customUnits.MICROGRAMS_PER_LITER );
        unitMap.put( "S m-1", customUnits.SIEMENS_PER_METER );
        unitMap.put( "m s-1", customUnits.METERS_PER_SECOND );
        unitMap.put( "m3 s-1", customUnits.CUBIC_METERS_PER_SECOND );
        unitMap.put( "kg m-3", customUnits.KILOGRAMS_PER_CUBIC_METER );
        unitMap.put( "mm", customUnits.MILLIMETERS );
    }
    
    public static UnitResolver instance() throws UnitCreationException{
        if( instance == null ){
            instance = new UnitResolver();
        }

        return instance;
    }
    
    /**
     * Resolves a string to a ucar.units.Unit.
     * 
     * @param unit
     * @return the resolved Unit
     * @throws UnitCreationException 
     */
    public Unit resolveUnit( String unitString ) throws UnitCreationException{
        Unit unit;              
        unitString = unitString.trim();
        
        //if we have a mapped unit for this string, return it
        if( unitMap.get( unitString ) != null ){
            return unitMap.get( unitString );
        }        
        
        //fall back on string parsing 
        try{
            unit = unitFormat.parse( unitString ); 
        } catch( Exception e ){
            throw new UnitCreationException( e );            
        }
        return unit;
    }
}
