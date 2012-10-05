package com.axiomalaska.ioos.parameter;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class ParameterTest {
	private static final Logger LOG = Logger.getLogger( ParameterTest.class );
	private static final String SALINITY_URL = "http://mmisw.org/ont/ioos/parameter/salinity";
	private static final String FAKE_URL = "http://mmisw.org/ont/ioos/parameter/fake";
	private static final String BAD_NS_URL = "http://mmisw.org/ont/asdf/parameter/depth";
	
	@Test
	public void testModel(){
		OntModel model = ParameterUtil.getInstance().getModel();
		ExtendedIterator<Individual> individuals = model.listIndividuals();
		while( individuals.hasNext() ){
			Individual parameter = individuals.next();
			String propName = parameter.getLocalName();
			String propTerm = ParameterUtil.getInstance().getPropertyValue( parameter, IoosParameter.Term );
			String propDef = ParameterUtil.getInstance().getPropertyValue( parameter, IoosParameter.Definition );
			LOG.debug( propName + " " + propTerm + " " + propDef );
		}		
    }
	
	@Test
	public void testGetPropertyValue(){
		String absorptionDef = ParameterUtil.getInstance().getPropertyValue(
		        IoosParameter.absorption, IoosParameter.Definition );
		LOG.debug( "Absorption depth: " + absorptionDef );		
		assertNotNull( absorptionDef );

		String depthUnits = ParameterUtil.getInstance().getPropertyValue(
		        IoosParameter.depth, IoosParameter.Units );
		LOG.debug( "Depth units: " + depthUnits );
		assertNotNull( depthUnits );
		assertEquals("meter", depthUnits );
	}
	
	@Test
	public void testVocabularyContainsTerm(){
		assertTrue( ParameterUtil.getInstance().vocabularyContainsParameter( SALINITY_URL ) );
		assertFalse( ParameterUtil.getInstance().vocabularyContainsParameter( FAKE_URL ) );
		assertFalse( ParameterUtil.getInstance().vocabularyContainsParameter( BAD_NS_URL ) );
	}
}