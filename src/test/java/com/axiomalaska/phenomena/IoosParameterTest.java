package com.axiomalaska.phenomena;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.axiomalaska.ioos.parameter.IoosParameter;
import com.axiomalaska.phenomena.IoosParameterUtil;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class IoosParameterTest {
	private static final Logger LOG = Logger.getLogger( IoosParameterTest.class );
	private static final String SALINITY_URL = "http://mmisw.org/ont/ioos/parameter/salinity";
	private static final String FAKE_URL = "http://mmisw.org/ont/ioos/parameter/fake";
	private static final String BAD_NS_URL = "http://mmisw.org/ont/asdf/parameter/depth";
	
	@Test
	public void testModel(){
		OntModel model = IoosParameterUtil.getInstance().getModel();
		ExtendedIterator<Individual> individuals = model.listIndividuals();
		while( individuals.hasNext() ){
			Individual parameter = individuals.next();
			String propName = parameter.getLocalName();
			String propTerm = IoosParameterUtil.getInstance().getPropertyValue( parameter, IoosParameter.Term );
			String propDef = IoosParameterUtil.getInstance().getPropertyValue( parameter, IoosParameter.Definition );
			LOG.debug( propName + " " + propTerm + " " + propDef );
		}		
    }
	
	@Test
	public void testGetPropertyValue(){
		String absorptionDef = IoosParameterUtil.getInstance().getPropertyValue(
		        IoosParameter.absorption, IoosParameter.Definition );
		LOG.debug( "Absorption depth: " + absorptionDef );		
		assertNotNull( absorptionDef );

		String depthUnits = IoosParameterUtil.getInstance().getPropertyValue(
		        IoosParameter.depth, IoosParameter.Units );
		LOG.debug( "Depth units: " + depthUnits );
		assertNotNull( depthUnits );
		assertEquals("meter", depthUnits );
	}
	
	@Test
	public void testVocabularyContainsTerm(){
		assertTrue( IoosParameterUtil.getInstance().vocabularyContainsParameter( SALINITY_URL ) );
		assertFalse( IoosParameterUtil.getInstance().vocabularyContainsParameter( FAKE_URL ) );
		assertFalse( IoosParameterUtil.getInstance().vocabularyContainsParameter( BAD_NS_URL ) );
	}
}