package com.axiomalaska.phenomena;

import ucar.units.BaseUnit;
import ucar.units.OffsetUnit;
import ucar.units.SI;
import ucar.units.ScaledUnit;
import ucar.units.StandardUnitDB;
import ucar.units.Unit;
import ucar.units.UnitName;

public class CustomUnits {
    
    //convention is to make the unit names plural...because singular sounds weird
    public Unit PERCENTAGE;
    public Unit PARTS_PER_THOUSAND;
    public Unit METERS_PER_SECOND;
    public Unit CUBIC_METERS_PER_SECOND;
    public Unit KILOGRAMS_PER_CUBIC_METER;
    public Unit AMPERES_PER_HOUR;
    public Unit WATTS_PER_METER;    
    public Unit WATTS_PER_SQUARE_METER;
    public Unit SIEMENS_PER_METER;
    public Unit MICROGRAMS;
    public Unit MICROGRAMS_PER_LITER;
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
    
    private Unit addUnit(Unit unit) throws UnitCreationException {
        try {
            StandardUnitDB.instance().addUnit(unit);
        } catch (Exception e) {
            throw new UnitCreationException(e);
        }
        return unit;
    }
    
    public CustomUnits() throws UnitCreationException {
        try{

            PERCENTAGE = addUnit(StandardUnitDB.instance().getByName("percent"));
            StandardUnitDB.instance().addAlias("percentage", "percent", "%", "percent");

            PARTS_PER_THOUSAND = addUnit(StandardUnitDB.instance().getByName("ppt"));
            StandardUnitDB.instance().addAlias("part per thousand", "ppt", "ppt", "parts per thousand");
                
            METERS_PER_SECOND = addUnit(SI.METER.divideBy( SI.SECOND )
                .clone( UnitName.newUnitName("meter per second", "meters per second", "m.s-1")));

            CUBIC_METERS_PER_SECOND = addUnit(SI.METER.raiseTo( 3 ).divideBy( SI.SECOND )
                    .clone( UnitName.newUnitName("cubic meter per second", "cubic meters per second", "m3.s-1")));
            
            KILOGRAMS_PER_CUBIC_METER = addUnit(SI.KILOGRAM.divideBy( SI.METER.raiseTo( 3 ) )
                .clone( UnitName.newUnitName("kilogram per cubic meter", "kilograms per cubic meter", "kg.m-3")));
            
            AMPERES_PER_HOUR = addUnit(SI.AMPERE.divideBy( SI.HOUR )
                .clone( UnitName.newUnitName("ampere per hour", "amperes per hour", "A.h-1")));

            WATTS_PER_METER = addUnit(SI.WATT.divideBy( SI.METER )
                    .clone( UnitName.newUnitName("watt per meter", "watts per meter", "W.m-1")));

            WATTS_PER_SQUARE_METER = addUnit(SI.WATT.divideBy( SI.METER.raiseTo( 2 ) )
                .clone( UnitName.newUnitName("watt per square meter", "watts per square meter", "W.m-2")));

            SIEMENS_PER_METER = addUnit(SI.SIEMENS.divideBy( SI.METER )
                .clone( UnitName.newUnitName("siemen per meter", "siemens per meter", "S.m-1")));

            MICROGRAMS = addUnit(new ScaledUnit( 1e-9, SI.KILOGRAM )
                .clone( UnitName.newUnitName("microgram", null, "µg")));
            
            MICROGRAMS_PER_LITER = addUnit(MICROGRAMS.divideBy( SI.LITER )
                .clone( UnitName.newUnitName("microgram per liter", "micrograms per liter", "10e-1mg.L-1")));
            
            MILLIMETERS = addUnit(new ScaledUnit( 1e-3, SI.METER )
                .clone( UnitName.newUnitName("millimeter", null, "mm")));
            
            // added by Sean Cowan for ASA GLOS PROJECT ---
            MILLIGRAMS = addUnit(new ScaledUnit(1e-6, SI.KILOGRAM)
                .clone(UnitName.newUnitName("milligram", null, "mg")));
            
            MILLIGRAMS_PER_LITER = addUnit(MILLIGRAMS.divideBy(SI.LITER)
                .clone(UnitName.newUnitName("milligram per liter", null, "mg.L-1")));
            
            MILLILITRE = addUnit(new ScaledUnit(1e-3, SI.LITRE)
                .clone(UnitName.newUnitName("millilitre", "millilitres", "mL")));
            
            MILLILITRE_100 = addUnit(new ScaledUnit(1e-1, SI.LITRE)
                .clone(UnitName.newUnitName("100 millilitres", null, "100mL")));

            PARTS_PER_100_MILLILITRES = addUnit(BaseUnit.DIMENSIONLESS.divideBy(MILLILITRE_100)
                .clone(UnitName.newUnitName("parts per 100 millilitres", null, "#.100mL-1")));
            
            CFU_PER_100_MILLILITRES = addUnit(BaseUnit.DIMENSIONLESS.divideBy(MILLILITRE_100)
                .clone(UnitName.newUnitName("cfu per 100 millilitre", null, "cfu.100mL-1")));
                    
            //-----
        } catch( Exception e ){
            throw new UnitCreationException( e );
        }
    }  
}
