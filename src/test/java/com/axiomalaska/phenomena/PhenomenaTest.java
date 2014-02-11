package com.axiomalaska.phenomena;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import ucar.units.ConversionException;
import ucar.units.Unit;

import com.axiomalaska.phenomena.Phenomena.HomelessParameter;
import com.axiomalaska.phenomena.Phenomena.IOOSParameter;
import com.axiomalaska.phenomena.Phenomena.NonStandardUnits;

public class PhenomenaTest {
    private static final Logger LOG = Logger.getLogger( PhenomenaTest.class );
	
    @Test
    public void testAllPhenomena() throws UnitCreationException{
        LOG.info("All phenomena:");
        for( Phenomenon phenomenon : Phenomena.instance().getAllPhenomena() ){
            LOG.info( phenomenon );
        }
    }

	@Test
	public void testPhenomena() throws IllegalArgumentException, IllegalAccessException, UnitCreationException{
	    List<Phenomenon> cfPhenomena = new ArrayList<Phenomenon>();
	    List<Phenomenon> ioosPhenomena = new ArrayList<Phenomenon>();
	    List<Phenomenon> homelessPhenomena = new ArrayList<Phenomenon>();
	    List<Phenomenon> nonStandardUnitsPhenomena = new ArrayList<Phenomenon>();	   

	    for( Field field : Phenomena.class.getDeclaredFields() ){
	        if( field.getType().equals( Phenomenon.class ) ){
	            if( field.isAnnotationPresent( Phenomena.CFParameter.class ) ){
	                cfPhenomena.add( (Phenomenon) field.get( Phenomena.instance() ) );
	            }
	            
                if( field.isAnnotationPresent( IOOSParameter.class ) ){
                    ioosPhenomena.add( (Phenomenon) field.get( Phenomena.instance() ) );
                }
                
                if( field.isAnnotationPresent( HomelessParameter.class ) ){
                    homelessPhenomena.add( (Phenomenon) field.get( Phenomena.instance() ) );
                }

                if( field.isAnnotationPresent( NonStandardUnits.class ) ){
                    nonStandardUnitsPhenomena.add( (Phenomenon) field.get( Phenomena.instance() ) );
                }                
	        }
	    }
	    
	    LOG.info( "CF phenomena: " + cfPhenomena.size() );
        logPhenomena( cfPhenomena );

        LOG.info( "IOOS phenomena: " + ioosPhenomena.size() );
        logPhenomena( ioosPhenomena );

        LOG.info( "Homeless phenomena: " + homelessPhenomena.size() );
        logPhenomena( homelessPhenomena );

        LOG.info( "Non-standard Units phenomena: " + nonStandardUnitsPhenomena.size() );
        logPhenomena( nonStandardUnitsPhenomena );        
	}
	
	private void logPhenomena( List<Phenomenon> phenomena ){
        for( Phenomenon phenomenon : phenomena ){
            Unit unit = phenomenon.getUnit();
            String unitString;
            if( unit == null ){
                unitString = "no unit";
            } else {
                unitString = unit.toString() + " (" + unit.getPlural() + ")";
            }

            LOG.info( phenomenon.getId() + ": " + unitString );
        }	    
	}
	
	@Test
	public void testConversion() throws UnitCreationException, ConversionException{
	    Unit cubicFeetPerSecond = UnitResolver.instance().resolveUnit("feet3.s-1");
	    Unit cubicMetersPerSecond = CustomUnits.instance().CUBIC_METERS_PER_SECOND;
	    LOG.info("10 cubic feet per second = " +
	            cubicFeetPerSecond.convertTo(10f, cubicMetersPerSecond) + " " + cubicMetersPerSecond.getSymbol());
	}
	
    @Test
    public void testUnitParsing() throws UnitCreationException, ConversionException{
        Unit metersPerSecondSlash = UnitResolver.instance().resolveUnit("m/s");
        Unit metersPerSecondDot = UnitResolver.instance().resolveUnit("m.s-1");
        assertEquals(metersPerSecondSlash, metersPerSecondDot);
    }

    @Test
    @Ignore
    public void testUnitParsing2() throws UnitCreationException, ConversionException{
        Unit degCel = UnitResolver.instance().resolveUnit("Cel");
        Unit degCelDegree = UnitResolver.instance().resolveUnit("°C");
        assertEquals(degCel, degCelDegree);
    }
    
}