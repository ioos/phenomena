package com.axiomalaska.phenomena;

import ucar.units.BaseUnit;
import ucar.units.OffsetUnit;
import ucar.units.SI;
import ucar.units.ScaledUnit;
import ucar.units.Unit;
import ucar.units.UnitName;

public class CustomUnits {
    
    //convention is to make the unit names plural...because singular sounds weird
    public Unit PERCENTAGE;
    public Unit PARTS_PER_THOUSAND;
    public Unit FEET;
    public Unit METERS_PER_SECOND;
    public Unit CUBIC_METERS_PER_SECOND;
    public Unit CUBIC_FEET_PER_SECOND;
    public Unit KILOGRAMS_PER_CUBIC_METER;
    public Unit AMPERES_PER_HOUR;
    public Unit WATTS_PER_METER;    
    public Unit WATTS_PER_SQUARE_METER;
    public Unit SIEMENS_PER_METER;
    public Unit MICROGRAMS;
    public Unit MICROGRAMS_PER_LITER;
    public Unit DEGREES_CELSIUS;
    public Unit MILLIMETERS;
    public Unit MILLIGRAMS;
    public Unit MILLIGRAMS_PER_LITER;
    public Unit MILLILITRE;
    public Unit MILLILITRE_100;
    public Unit PARTS_PER_100_MILLILITRES;
    public Unit CFU_PER_100_MILLILITRES;
    
    private static CustomUnits instance;
    
    public static CustomUnits instance() throws UnitCreationException{
        if( instance == null ){
            instance = new CustomUnits();
        }
        return instance;
    }
    
    public CustomUnits() throws UnitCreationException {
        try{

            PERCENTAGE = new ScaledUnit( 100, BaseUnit.DIMENSIONLESS )
                .clone( UnitName.newUnitName("percent", "percent", "%") );;

            PARTS_PER_THOUSAND = new ScaledUnit( 1e-3, BaseUnit.DIMENSIONLESS )
                .clone( UnitName.newUnitName("part per thousand", "parts per thousand", "ppt") );;
                
            FEET = new ScaledUnit( 0.3048006096, SI.METER )
                    .clone( UnitName.newUnitName("foot", "feet", "ft") );
            
            METERS_PER_SECOND = SI.METER.divideBy( SI.SECOND )
                .clone( UnitName.newUnitName("meter per second", "meters per second", "m.s-1") );

            CUBIC_METERS_PER_SECOND = SI.METER.raiseTo( 3 ).divideBy( SI.SECOND )
                    .clone( UnitName.newUnitName("cubic meter per second", "cubic meters per second", "m3.s-1") );

            CUBIC_FEET_PER_SECOND = FEET.raiseTo( 3 ).divideBy( SI.SECOND )
                    .clone( UnitName.newUnitName("cubic foot per second", "cubic feet per second", "ft3.s-1") );
            
            KILOGRAMS_PER_CUBIC_METER = SI.KILOGRAM.divideBy( SI.METER.raiseTo( 3 ) )
                .clone( UnitName.newUnitName("kilogram per cubic meter", "kilograms per cubic meter", "kg.m-3") );
            
            AMPERES_PER_HOUR = SI.AMPERE.divideBy( SI.HOUR )
                .clone( UnitName.newUnitName("ampere per hour", "amperes per hour", "A.h-1") );

            WATTS_PER_METER = SI.WATT.divideBy( SI.METER )
                    .clone( UnitName.newUnitName("watt per meter", "watts per meter", "W.m-1") );

            WATTS_PER_SQUARE_METER = SI.WATT.divideBy( SI.METER.raiseTo( 2 ) )
                .clone( UnitName.newUnitName("watt per square meter", "watts per square meter", "W.m-2") );

            SIEMENS_PER_METER = SI.SIEMENS.divideBy( SI.METER )
                .clone( UnitName.newUnitName("siemen per meter", "siemens per meter", "S.m-1") );

            MICROGRAMS = new ScaledUnit( 1e-9, SI.KILOGRAM )
                .clone( UnitName.newUnitName("microgram", null, "µg") );
            
            MICROGRAMS_PER_LITER = MICROGRAMS.divideBy( SI.LITER )
                .clone( UnitName.newUnitName("microgram per liter", "micrograms per liter", "10e-1mg.L-1") );
                        
            //use this instead of SI.DEGREE_CELSIUS because the plural of that one is degree celsiuses
            DEGREES_CELSIUS = new OffsetUnit( SI.KELVIN, 273.15 )
                .clone( UnitName.newUnitName("degree celsius", "degrees celsius", "Cel") );
            
            MILLIMETERS = new ScaledUnit( 1e-3, SI.METER )
                .clone( UnitName.newUnitName("millimeter", null, "mm") );
            
            // added by Sean Cowan for ASA GLOS PROJECT ---
            MILLIGRAMS = new ScaledUnit(1e-6, SI.KILOGRAM)
                .clone(UnitName.newUnitName("milligram", null, "mg") );
            
            MILLIGRAMS_PER_LITER = MILLIGRAMS.divideBy(SI.LITER)
                .clone(UnitName.newUnitName("milligram per liter", null, "mg.L-1") );
            
            MILLILITRE = new ScaledUnit(1e-3, SI.LITRE)
                .clone(UnitName.newUnitName("millilitre", "millilitres", "mL"));
            
            MILLILITRE_100 = new ScaledUnit(1e-1, SI.LITRE)
                .clone(UnitName.newUnitName("100 millilitres", null, "100mL"));

            PARTS_PER_100_MILLILITRES = BaseUnit.DIMENSIONLESS.divideBy(MILLILITRE_100)
                .clone(UnitName.newUnitName("parts per 100 millilitres", null, "#.100mL-1"));
            
            CFU_PER_100_MILLILITRES = BaseUnit.DIMENSIONLESS.divideBy(MILLILITRE_100)
                .clone(UnitName.newUnitName("cfu per 100 millilitre", null, "cfu.100mL-1"));
                    
            //-----
        } catch( Exception e ){
            throw new UnitCreationException( e );
        }
    }  
}
