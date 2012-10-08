package com.axiomalaska.phenomena;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.axiomalaska.phenomena.Phenomena.EnglishUnits;
import com.axiomalaska.phenomena.Phenomena.HomelessParameter;
import com.axiomalaska.phenomena.Phenomena.IOOSParameter;
import com.axiomalaska.phenomena.Phenomena.NonStandardUnits;

public class PhenomenaTest {
    private static final Logger LOG = Logger.getLogger( PhenomenaTest.class );
	
	@Test
	public void testPhenomena() throws IllegalArgumentException, IllegalAccessException{
	    List<Phenomenon> cfPhenomena = new ArrayList<Phenomenon>();
	    List<Phenomenon> ioosPhenomena = new ArrayList<Phenomenon>();
	    List<Phenomenon> homelessPhenomena = new ArrayList<Phenomenon>();
	    List<Phenomenon> englishUnitsPhenomena = new ArrayList<Phenomenon>();
	    List<Phenomenon> nonStandardUnitsPhenomena = new ArrayList<Phenomenon>();
	    
	    for( Field field : Phenomena.class.getDeclaredFields() ){
	        if( field.getType().equals( Phenomenon.class ) ){
	            if( field.isAnnotationPresent( Phenomena.CFParameter.class ) ){
	                cfPhenomena.add( (Phenomenon) field.get(null) );
	            }
	            
                if( field.isAnnotationPresent( IOOSParameter.class ) ){
                    ioosPhenomena.add( (Phenomenon) field.get(null) );
                }
                
                if( field.isAnnotationPresent( HomelessParameter.class ) ){
                    homelessPhenomena.add( (Phenomenon) field.get(null) );
                }

                if( field.isAnnotationPresent( EnglishUnits.class ) ){
                    englishUnitsPhenomena.add( (Phenomenon) field.get(null) );
                }

                if( field.isAnnotationPresent( NonStandardUnits.class ) ){
                    nonStandardUnitsPhenomena.add( (Phenomenon) field.get(null) );
                }                
	        }
	    }
	    
	    LOG.info( "CF phenomena: " + cfPhenomena.size() );
	    for( Phenomenon phenomenon : cfPhenomena ){
	        LOG.info( phenomenon.getId() + ": " + phenomenon.getUnits() );
	    }

        LOG.info( "IOOS phenomena: " + ioosPhenomena.size() );
        for( Phenomenon phenomenon : ioosPhenomena ){
            LOG.info( phenomenon.getId() + ": " + phenomenon.getUnits() );
        }

        LOG.info( "Homeless phenomena: " + homelessPhenomena.size() );
        for( Phenomenon phenomenon : homelessPhenomena ){
            LOG.info( phenomenon.getId() + ": " + phenomenon.getUnits() );
        }
        
        LOG.info( "English Units phenomena: " + englishUnitsPhenomena.size() );
        for( Phenomenon phenomenon : englishUnitsPhenomena ){
            LOG.info( phenomenon.getId() + ": " + phenomenon.getUnits() );
        }
        
        LOG.info( "Non-standard Units phenomena: " + nonStandardUnitsPhenomena.size() );
        for( Phenomenon phenomenon : nonStandardUnitsPhenomena ){
            LOG.info( phenomenon.getId() + ": " + phenomenon.getUnits() );
        }
	}
}