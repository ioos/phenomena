package com.axiomalaska.phenomena;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import ucar.units.SI;

import com.axiomalaska.cf4j.CFStandardName;
import com.axiomalaska.cf4j.CFStandardNames;
import com.axiomalaska.ioos.parameter.IoosParameter;
import com.hp.hpl.jena.ontology.Individual;

public class Phenomena {
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CFParameter {}

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IOOSParameter {}
    
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HomelessParameter {
        public String description();
        public String source();
    }
    
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface EnglishUnits {}
    
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NonStandardUnits {}

    public static final String IOOS_MMI_URL_PREFIX = IoosParameter.NS;    
    public static final String FAKE_MMI_URL_PREFIX = IOOS_MMI_URL_PREFIX;
    public static final String CF_MMI_URL_PREFIX = "http://mmisw.org/ont/cf/parameter/";

    public static final String DEGREE = "degree";
    public static final String PERCENT = "%";
    
    private static final String convertUnderscoredNameToTitleCase( String underscoredName ){
        String lowercaseName = underscoredName.replace('_', ' ');
        return WordUtils.capitalize( lowercaseName );
    }

    private static final Phenomenon createCfParameterWithAlternateName(
            CFStandardName cfStandardName, String name ){
        PhenomenonImp sosPhen = (PhenomenonImp) createStandardCfParameter( cfStandardName );
        sosPhen.setName( name );
        return sosPhen;
    }
    
    private static final Phenomenon createCfParameterWithAlternateUnits(
            CFStandardName cfStandardName, String units ){
        PhenomenonImp sosPhen = (PhenomenonImp) createStandardCfParameter( cfStandardName );
        sosPhen.setUnits( units );
        return sosPhen;
    }
    
    private static final Phenomenon createIoosParameterWithAlternateUnits(
            Individual ioosParameter, String units ){
        PhenomenonImp sosPhen = (PhenomenonImp) createStandardIoosParameter( ioosParameter );
        sosPhen.setUnits( units );
        return sosPhen;
    }

    private static final Phenomenon createStandardCfParameter( CFStandardName cfStandardName ){
        return new PhenomenonImp(
             convertUnderscoredNameToTitleCase( cfStandardName.getName() )
            ,CF_MMI_URL_PREFIX + cfStandardName.getName()
            ,cfStandardName.getCanonicalUnits()
        );
    }
    
    private static final Phenomenon createStandardIoosParameter( Individual ioosParameter ){
        return new PhenomenonImp(
             convertUnderscoredNameToTitleCase( ioosParameter.getLocalName() )
            ,ioosParameter.getURI()
            ,IoosParameterUtil.getInstance().getPropertyValue( ioosParameter, IoosParameter.Units )
        );
    }
    
    public static final List<Phenomenon> getAllPhenomena(){
    	List<Phenomenon> p = new ArrayList<Phenomenon>();
    	
    	p.add(AIR_PRESSURE);
    	p.add(AIR_TEMPERATURE);
    	p.add(AIR_TEMPERATURE_AVERAGE);
    	p.add(AIR_TEMPERATURE_MAXIMUM);
    	p.add(AIR_TEMPERATURE_MINIMUM);
    	p.add(ALTITUDE);
    	p.add(AMMONIUM);
    	p.add(BATTERY_VOLTAGE);
    	p.add(BATTERY_VOLTAGE_MAXIMUM);
    	p.add(BATTERY_VOLTAGE_MINIMUM);
    	p.add(CHLOROPHYLL_FLOURESCENCE);
    	p.add(DEPTH_TO_WATER_LEVEL);
    	p.add(DEW_POINT_TEMPERATURE);
    	p.add(DIRECTION_OF_SEA_WATER_VELOCITY);
    	p.add(DOMINANT_WAVE_PERIOD);
    	p.add(DOWNWELLING_PHOTOSYNTHETIC_RADIATIVE_FLUX_IN_SEA_WATER);
    	p.add(FRACTIONAL_SATURATION_OF_OXYGEN_IN_SEA_WATER);
    	p.add(FUEL_MOISTURE);
    	p.add(FUEL_TEMPERATURE);
    	p.add(GRID_LATITUDE);
    	p.add(GRID_LONGITUDE);
    	p.add(HEIGHT);
    	p.add(LWE_THICKNESS_OF_PRECIPITATION_AMOUNT);
    	p.add(NITRATE);
    	p.add(NITRITE);
    	p.add(NITRITE_PLUS_NITRATE);
    	p.add(MASS_CONCENTRATION_OF_CARBON_DIOXIDE_IN_AIR);
    	p.add(MASS_CONCENTRATION_OF_CARBON_DIOXIDE_IN_SEA_WATER);
    	p.add(MASS_CONCENTRATION_OF_CHLOROPHYLL_IN_SEA_WATER);
    	p.add(MASS_CONCENTRATION_OF_OXYGEN_IN_SEA_WATER);
    	p.add(PANEL_TEMPERATURE);
    	p.add(PEAK_WAVE_DIRECTION);
    	p.add(PEAK_WAVE_PERIOD);
    	p.add(PHOSPHATE);
    	p.add(PHOTOSYNTHETICALLY_ACTIVE_RADIATION);
    	p.add(PHYCOERYTHRIN);
    	p.add(PRECIPITATION_ACCUMULATED);
    	p.add(PRECIPITATION_INCREMENT);
    	p.add(PRODUCT_OF_AIR_TEMPERATURE_AND_SPECIFIC_HUMIDITY);
    	p.add(RADIAL_SEA_WATER_VELOCITY_AWAY_FROM_INSTRUMENT);
    	p.add(REAL_DIELECTRIC_CONSTANT);
    	p.add(RELATIVE_HUMIDITY);
    	p.add(RELATIVE_HUMIDITY_AVERAGE);
    	p.add(RELATIVE_HUMIDITY_MAXIMUM);
    	p.add(RELATIVE_HUMIDITY_MINIMUM);
    	p.add(RELATIVE_PERMITTIVITY);
    	p.add(RIVER_DISCHARGE);
    	p.add(SEA_FLOOR_DEPTH_BELOW_SEA_SURFACE);
    	p.add(SEA_SURFACE_HEIGHT_ABOVE_SEA_LEVEL);
    	p.add(SEA_SURFACE_HEIGHT_ABOVE_SEA_LEVEL_PREDICTIONS);
    	p.add(SEA_SURFACE_MAXIMUM_WAVE_HEIGHT);
    	p.add(SEA_SURFACE_SWELL_WAVE_PERIOD);
    	p.add(SEA_SURFACE_SWELL_WAVE_SIGNIFICANT_HEIGHT);
    	p.add(SEA_SURFACE_SWELL_WAVE_TO_DIRECTION);
    	p.add(SEA_SURFACE_DOMINANT_WAVE_TO_DIRECTION);
    	p.add(SEA_SURFACE_WAVE_FROM_DIRECTION);
    	p.add(SEA_SURFACE_WAVE_MEAN_PERIOD);
    	p.add(SEA_SURFACE_WAVE_SIGNIFICANT_HEIGHT);
    	p.add(SEA_SURFACE_WIND_WAVE_PERIOD);
    	p.add(SEA_SURFACE_WIND_WAVE_SIGNIFICANT_HEIGHT);
    	p.add(SEA_SURFACE_WIND_WAVE_TO_DIRECTION);
    	p.add(SEA_WATER_ELECTRICAL_CONDUCTIVITY);
    	p.add(SEA_WATER_PH_REPORTED_ON_TOTAL_SCALE);
    	p.add(SEA_WATER_PRACTICAL_SALINITY);
    	p.add(SEA_WATER_SPEED);
    	p.add(SEA_WATER_TEMPERATURE);
    	p.add(SNOW_DEPTH);
    	p.add(SNOW_PILLOW);
    	p.add(SNOW_WATER_EQUIVALENT);
    	p.add(SOIL_MOISTURE_PERCENT);
    	p.add(SOIL_TEMPERATURE);
    	p.add(SOLAR_RADIATION);
    	p.add(SOLAR_RADIATION_AVERAGE);
    	p.add(SOLAR_RADIATION_MAXIMUM);
    	p.add(SOLAR_RADIATION_MINIMUM);
    	p.add(STREAMFLOW);
    	p.add(STREAM_GAGE_HEIGHT);
    	p.add(TOA_INCOMING_SHORTWAVE_FLUX);
    	p.add(TOA_OUTGOING_SHORTWAVE_FLUX);
    	p.add(TURBIDITY);
    	p.add(VISIBILITY_IN_AIR);
    	p.add(WATER_SURFACE_HEIGHT_ABOVE_REFERENCE_DATUM);
    	p.add(WATER_TEMPERATURE_INTRAGRAVEL);
    	p.add(WATER_VOLUME_TRANSPORT_INTO_SEA_WATER_FROM_RIVERS);
    	p.add(WAVE_DIRECTIONAL_SPREAD);
    	p.add(WEBCAM);
    	p.add(WIND_FROM_DIRECTION);
    	p.add(WIND_GENERATOR_CURRENT);
    	p.add(WIND_GUST_FROM_DIRECTION);
    	p.add(WIND_SPEED);
    	p.add(WIND_SPEED_OF_GUST);
    	p.add(WIND_VERTICAL_VELOCITY);
    	
    	return p;
    }
    
    @CFParameter
    public static final Phenomenon AIR_PRESSURE = createStandardCfParameter( 
    		CFStandardNames.AIR_PRESSURE );
    
    @CFParameter
    @NonStandardUnits
    public static final Phenomenon AIR_TEMPERATURE = createCfParameterWithAlternateUnits(
         CFStandardNames.AIR_TEMPERATURE
        ,SI.DEGREE_CELSIUS.getSymbol()
    );
    
    @HomelessParameter(description="",source="NERRS, RAWS, SnoTel")
    public static final Phenomenon AIR_TEMPERATURE_AVERAGE = new PhenomenonImp(
        "Air Temperature Average"
       ,FAKE_MMI_URL_PREFIX + "air_temperature_average"
       ,SI.DEGREE_CELSIUS.getSymbol()
    );
    
    @HomelessParameter(description="",source="HADS, RAWS, SnoTel")
    public static final Phenomenon AIR_TEMPERATURE_MAXIMUM = new PhenomenonImp(
        "Air Temperature Maximum"
       ,FAKE_MMI_URL_PREFIX + "air_temperature_maximum"
       ,SI.DEGREE_CELSIUS.getSymbol()
    );

    @HomelessParameter(description="",source="HADS, RAWS, SnoTel")
    public static final Phenomenon AIR_TEMPERATURE_MINIMUM = new PhenomenonImp(
        "Air Temperature Minimum"
       ,FAKE_MMI_URL_PREFIX + "air_temperature_minimum"
       ,SI.DEGREE_CELSIUS.getSymbol()
    );

    @CFParameter
    public static final Phenomenon ALTITUDE = createStandardCfParameter( CFStandardNames.ALTITUDE );
    
    @IOOSParameter
    public static final Phenomenon AMMONIUM =
        createStandardIoosParameter( IoosParameter.ammonium );
    
    @IOOSParameter
    public static final Phenomenon BATTERY_VOLTAGE =
        createStandardIoosParameter( IoosParameter.battery_voltage );

    @HomelessParameter(description="",source="SnoTel")
    public static final Phenomenon BATTERY_VOLTAGE_MAXIMUM = new PhenomenonImp(
        "Battery Voltage Maximum"
       ,FAKE_MMI_URL_PREFIX + "battery_voltage_maximum"
       ,SI.VOLT.getSymbol()
    );
    
    @HomelessParameter(description="",source="SnoTel")
    public static final Phenomenon BATTERY_VOLTAGE_MINIMUM = new PhenomenonImp(
        "Battery Voltage Maximum"
       ,FAKE_MMI_URL_PREFIX + "battery_voltage_minimum"
       ,SI.VOLT.getSymbol()
    );
    
    @IOOSParameter
    public static final Phenomenon CHLOROPHYLL_FLOURESCENCE =
        createStandardIoosParameter( IoosParameter.chlorophyll_flourescence );

    @HomelessParameter(description="",source="USGS")
    public static final Phenomenon DEPTH_TO_WATER_LEVEL = new PhenomenonImp(
         "Depth to Water Level"
        ,FAKE_MMI_URL_PREFIX + "depth_to_water_level"
        ,SI.METER.getSymbol()
    );

    @CFParameter
    @NonStandardUnits
    public static final Phenomenon DEW_POINT_TEMPERATURE = createCfParameterWithAlternateUnits(
         CFStandardNames.DEW_POINT_TEMPERATURE
        ,SI.DEGREE_CELSIUS.getSymbol()
    );

    @CFParameter
    public static final Phenomenon DIRECTION_OF_SEA_WATER_VELOCITY =
        createStandardCfParameter( CFStandardNames.DIRECTION_OF_SEA_WATER_VELOCITY );
    
    @HomelessParameter(description="",source="NOAA NOS CO-OPS, NDBC")
    public static final Phenomenon DOMINANT_WAVE_PERIOD = new PhenomenonImp(
         "Sea Surface Dominant Wave Period"
        ,FAKE_MMI_URL_PREFIX + "sea_surface_dominant_wave_period"
        ,SI.SECOND.getSymbol()
    );
    
    @CFParameter
    public static final Phenomenon DOWNWELLING_PHOTOSYNTHETIC_RADIATIVE_FLUX_IN_SEA_WATER =
        createStandardCfParameter( CFStandardNames.DOWNWELLING_PHOTOSYNTHETIC_RADIATIVE_FLUX_IN_SEA_WATER );
    
    @CFParameter
    public static final Phenomenon FRACTIONAL_SATURATION_OF_OXYGEN_IN_SEA_WATER =
        createStandardCfParameter( CFStandardNames.FRACTIONAL_SATURATION_OF_OXYGEN_IN_SEA_WATER );
    
    @HomelessParameter(description="",source="HADS, RAWS")
    public static final Phenomenon FUEL_MOISTURE = new PhenomenonImp(
         "Fuel Moisture"
        ,FAKE_MMI_URL_PREFIX + "fuel_moisture"
        ,PERCENT
    );

    @HomelessParameter(description="",source="HADS, RAWS")
    public static final Phenomenon FUEL_TEMPERATURE = new PhenomenonImp(
         "Fuel Temperature"
        ,FAKE_MMI_URL_PREFIX + "fuel_temperature"
        ,SI.DEGREE_CELSIUS.getSymbol()
    );

    @CFParameter
    public static final Phenomenon GRID_LATITUDE = createStandardCfParameter( CFStandardNames.GRID_LATITUDE );

    @CFParameter
    public static final Phenomenon GRID_LONGITUDE = createStandardCfParameter( CFStandardNames.GRID_LONGITUDE );
    
    @CFParameter
    public static final Phenomenon HEIGHT = createStandardCfParameter( CFStandardNames.HEIGHT );        
    
    @CFParameter
    public static final Phenomenon LWE_THICKNESS_OF_PRECIPITATION_AMOUNT = createCfParameterWithAlternateName(
         CFStandardNames.LWE_THICKNESS_OF_PRECIPITATION_AMOUNT
        ,"Precipitation"
    );    

    @CFParameter
    public static final Phenomenon MASS_CONCENTRATION_OF_CARBON_DIOXIDE_IN_AIR =
        createStandardCfParameter( CFStandardNames.MASS_CONCENTRATION_OF_CARBON_DIOXIDE_IN_AIR );    
    
    @HomelessParameter(description="",source="")
    public static final Phenomenon MASS_CONCENTRATION_OF_CARBON_DIOXIDE_IN_SEA_WATER = new PhenomenonImp(
        "Mass Concentration of Carbon Dioxide in Sea Water"
       ,FAKE_MMI_URL_PREFIX + "mass_concentration_of_carbon_dioxide_in_sea_water"
       ,SI.KILOGRAM.getSymbol() + "/" + SI.METER.getSymbol() + "³"
    );
    
    @CFParameter
    public static final Phenomenon MASS_CONCENTRATION_OF_CHLOROPHYLL_IN_SEA_WATER =
        createStandardCfParameter( CFStandardNames.MASS_CONCENTRATION_OF_CHLOROPHYLL_IN_SEA_WATER );

    @CFParameter
    public static final Phenomenon MASS_CONCENTRATION_OF_OXYGEN_IN_SEA_WATER =
        createStandardCfParameter( CFStandardNames.MASS_CONCENTRATION_OF_OXYGEN_IN_SEA_WATER );
    
    @IOOSParameter
    public static final Phenomenon NITRATE =
        createStandardIoosParameter( IoosParameter.nitrate );
    
    @IOOSParameter
    public static final Phenomenon NITRITE =
        createStandardIoosParameter( IoosParameter.nitrite );
    
    @IOOSParameter
    public static final Phenomenon NITRITE_PLUS_NITRATE =
        createStandardIoosParameter( IoosParameter.nitrite_plus_nitrate );
    
    @HomelessParameter(description="",source="")
    public static final Phenomenon PANEL_TEMPERATURE = new PhenomenonImp(
         "Panel Temperature"
        ,FAKE_MMI_URL_PREFIX + "panel_temperature"
        ,SI.DEGREE_CELSIUS.getSymbol()
    );
    
    @IOOSParameter
    public static final Phenomenon PEAK_WAVE_DIRECTION =
        createStandardIoosParameter( IoosParameter.peak_wave_direction );

    @IOOSParameter
    public static final Phenomenon PEAK_WAVE_PERIOD =
        createStandardIoosParameter( IoosParameter.peak_wave_period );

    @IOOSParameter
    public static final Phenomenon PHOSPHATE =
        createStandardIoosParameter( IoosParameter.phosphate );
    
    @HomelessParameter(description="Solar radiation from 400 to 700 nanometers",source="HADS")
    public static final Phenomenon PHOTOSYNTHETICALLY_ACTIVE_RADIATION = new PhenomenonImp(
         "Photosynthetically Active Radiation"
        ,FAKE_MMI_URL_PREFIX + "photosynthetically_active_radiation"
        ,SI.WATT.getSymbol() + "/" + SI.METER.getSymbol() + "²"
    );
    
    @HomelessParameter(description="",source="")
    public static final Phenomenon PHYCOERYTHRIN = new PhenomenonImp(
         "Phycoerythrin"
        ,FAKE_MMI_URL_PREFIX + "phycoerythrin"
        ,"RFU"
    );

    @IOOSParameter
    public static final Phenomenon PRECIPITATION_ACCUMULATED =
        createStandardIoosParameter( IoosParameter.precipitation_accumulated );

    @HomelessParameter(description="",source="HADS, NERRS, SnoTel")
    public static final Phenomenon PRECIPITATION_INCREMENT = new PhenomenonImp(
        "Precipitation Increment"
       ,FAKE_MMI_URL_PREFIX + "precipitation_increment"
       ,SI.METER.getSymbol()
    );

    @CFParameter
    public static final Phenomenon PRODUCT_OF_AIR_TEMPERATURE_AND_SPECIFIC_HUMIDITY =
        createStandardCfParameter( CFStandardNames.PRODUCT_OF_AIR_TEMPERATURE_AND_SPECIFIC_HUMIDITY );

    @CFParameter
    public static final Phenomenon RADIAL_SEA_WATER_VELOCITY_AWAY_FROM_INSTRUMENT =
        createStandardCfParameter( CFStandardNames.RADIAL_SEA_WATER_VELOCITY_AWAY_FROM_INSTRUMENT );
    
    @HomelessParameter(description="",source="SnoTel")
    public static final Phenomenon REAL_DIELECTRIC_CONSTANT = new PhenomenonImp(
        "Real Dielectric constant"
       ,FAKE_MMI_URL_PREFIX + "real_dielectric_constant"
       ,"units"
    );
    
    @CFParameter
    @NonStandardUnits
    public static final Phenomenon RELATIVE_HUMIDITY = createCfParameterWithAlternateUnits(
         CFStandardNames.RELATIVE_HUMIDITY
        ,PERCENT
    );

    @HomelessParameter(description="",source="NERRS, RAWS, SnoTel")
    public static final Phenomenon RELATIVE_HUMIDITY_AVERAGE = new PhenomenonImp(
        "Relative Humidity Average"
       ,FAKE_MMI_URL_PREFIX + "relative_humidity_average"
       ,PERCENT
    );
    
    @HomelessParameter(description="",source="RAWS, SnoTel")
    public static final Phenomenon RELATIVE_HUMIDITY_MAXIMUM = new PhenomenonImp(
        "Relative Humidity Maximum"
       ,FAKE_MMI_URL_PREFIX + "relative_humidity_maximum"
       ,PERCENT
    );
    
    @HomelessParameter(description="",source="RAWS, SnoTel")
    public static final Phenomenon RELATIVE_HUMIDITY_MINIMUM = new PhenomenonImp(
        "Relative Humidity Minimum"
       ,FAKE_MMI_URL_PREFIX + "relative_humidity_minimum"
       ,PERCENT
    );
    
    @HomelessParameter(description="",source="")
    public static final Phenomenon RELATIVE_PERMITTIVITY = new PhenomenonImp(
         "Relative Permittivity"
        ,FAKE_MMI_URL_PREFIX + "relative_permittivity"
    );

    @IOOSParameter
    @EnglishUnits
    public static final Phenomenon RIVER_DISCHARGE = createIoosParameterWithAlternateUnits(
         IoosParameter.river_discharge
        ,"cfs"
    );

    @CFParameter
    public static final Phenomenon SEA_FLOOR_DEPTH_BELOW_SEA_SURFACE =
        createStandardCfParameter( CFStandardNames.SEA_FLOOR_DEPTH_BELOW_SEA_SURFACE );

    @CFParameter
    public static final Phenomenon SEA_SURFACE_HEIGHT_ABOVE_SEA_LEVEL =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_HEIGHT_ABOVE_SEA_LEVEL );
    
    //not used in the sensor-web-harvester
    @HomelessParameter(description="",source="")
    public static final Phenomenon SEA_SURFACE_HEIGHT_ABOVE_SEA_LEVEL_PREDICTIONS = new PhenomenonImp(
        "Sea Surface Height Above Sea Level Predictions"
       ,FAKE_MMI_URL_PREFIX + "sea_surface_height_above_sea_level_predictions"
       ,SI.METER.getSymbol()
    );
    
    @HomelessParameter(description="",source="")
    public static final Phenomenon SEA_SURFACE_MAXIMUM_WAVE_HEIGHT = new PhenomenonImp(
         "Sea Surface Maximum Wave Height"
        ,FAKE_MMI_URL_PREFIX + "sea_surface_maximum_wave_height"
        ,SI.METER.getSymbol()
    ); 
    
    @CFParameter
    public static final Phenomenon SEA_SURFACE_SWELL_WAVE_PERIOD =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_SWELL_WAVE_PERIOD );

    @CFParameter
    public static final Phenomenon SEA_SURFACE_SWELL_WAVE_SIGNIFICANT_HEIGHT =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_SWELL_WAVE_SIGNIFICANT_HEIGHT );
    
    @CFParameter
    public static final Phenomenon SEA_SURFACE_SWELL_WAVE_TO_DIRECTION =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_SWELL_WAVE_TO_DIRECTION );
    
	@HomelessParameter(description = "", source = "NOAA NOS CO-OPS")
	public static final Phenomenon SEA_SURFACE_DOMINANT_WAVE_TO_DIRECTION = new PhenomenonImp(
			"Sea Surface Dominant Wave To Direction"
			,FAKE_MMI_URL_PREFIX + "sea_surface_dominant_wave_to_direction"
			,DEGREE
	);
    
    @CFParameter
    public static final Phenomenon SEA_SURFACE_WAVE_FROM_DIRECTION =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_WAVE_FROM_DIRECTION );
    
    @HomelessParameter(description="",source="NOAA NOS CO-OPS")
    public static final Phenomenon SEA_SURFACE_WAVE_MEAN_PERIOD = new PhenomenonImp(
        "Sea Surface Wave Mean Period"
        ,FAKE_MMI_URL_PREFIX + "sea_surface_wave_mean_period"
        ,SI.SECOND.getSymbol()
    );
    
    @CFParameter
    public static final Phenomenon SEA_SURFACE_WAVE_SIGNIFICANT_HEIGHT =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_WAVE_SIGNIFICANT_HEIGHT );
    
    @CFParameter
    public static final Phenomenon SEA_SURFACE_WIND_WAVE_PERIOD =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_WIND_WAVE_PERIOD );
        
    @CFParameter
    public static final Phenomenon SEA_SURFACE_WIND_WAVE_SIGNIFICANT_HEIGHT =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_WIND_WAVE_SIGNIFICANT_HEIGHT );

    @CFParameter
    public static final Phenomenon SEA_SURFACE_WIND_WAVE_TO_DIRECTION =
        createStandardCfParameter( CFStandardNames.SEA_SURFACE_WIND_WAVE_TO_DIRECTION );

    @CFParameter
    public static final Phenomenon SEA_WATER_ELECTRICAL_CONDUCTIVITY =
        createStandardCfParameter( CFStandardNames.SEA_WATER_ELECTRICAL_CONDUCTIVITY );
    
    @CFParameter
    public static final Phenomenon SEA_WATER_PH_REPORTED_ON_TOTAL_SCALE = createCfParameterWithAlternateName(
         CFStandardNames.SEA_WATER_PH_REPORTED_ON_TOTAL_SCALE
        ,"Sea Water Acidity"
    );
    
    @CFParameter
    @NonStandardUnits
    public static final Phenomenon SEA_WATER_PRACTICAL_SALINITY = createCfParameterWithAlternateUnits(
         CFStandardNames.SEA_WATER_PRACTICAL_SALINITY
        ,"PSU"
    );
    
    @CFParameter
    public static final Phenomenon SEA_WATER_SPEED =
        createStandardCfParameter( CFStandardNames.SEA_WATER_SPEED );
    
    @CFParameter
    @NonStandardUnits
    public static final Phenomenon SEA_WATER_TEMPERATURE =
        createCfParameterWithAlternateUnits(
             CFStandardNames.SEA_WATER_TEMPERATURE
            ,SI.DEGREE_CELSIUS.getSymbol()
    );

    @HomelessParameter(description="",source="HADS, RAWS, SnoTel")
    public static final Phenomenon SNOW_DEPTH = new PhenomenonImp(
        "Snow Depth"
       ,FAKE_MMI_URL_PREFIX + "snow_depth"
       ,SI.METER.getSymbol()
    );

    @HomelessParameter(description="",source="RAWS")
    public static final Phenomenon SNOW_PILLOW = new PhenomenonImp(
        "Snow Pillow"
       ,FAKE_MMI_URL_PREFIX + "snow_pillow"
       ,SI.METER.getSymbol()
    );
    
    @HomelessParameter(description="",source="HADS, SnoTel")
    public static final Phenomenon SNOW_WATER_EQUIVALENT = new PhenomenonImp(
        "Snow Water Equivalent"
       ,FAKE_MMI_URL_PREFIX + "snow_water_equivalent"
       ,SI.METER.getSymbol()
    );

    @HomelessParameter(description="",source="RAWS, SnoTel")
    public static final Phenomenon SOIL_MOISTURE_PERCENT = new PhenomenonImp(
         "Soil Moisture Percent"
        ,FAKE_MMI_URL_PREFIX + "soil_moisture_percent"
        ,PERCENT
    );

    @CFParameter
    @NonStandardUnits
    public static final Phenomenon SOIL_TEMPERATURE = createCfParameterWithAlternateUnits(
         CFStandardNames.SOIL_TEMPERATURE
        ,SI.DEGREE_CELSIUS.getSymbol()
    );
    
    @HomelessParameter(description="",source="HADS, NERRS, RAWS, SnoTel")
    public static final Phenomenon SOLAR_RADIATION = new PhenomenonImp(
        "Solar Radiation"
       ,FAKE_MMI_URL_PREFIX + "solar_radiation"
       ,SI.WATT.getSymbol() + "/" + SI.METER.getSymbol() + "²"
    );
    
    @HomelessParameter(description="",source="SnoTel")
    public static final Phenomenon SOLAR_RADIATION_AVERAGE = new PhenomenonImp(
        "Solar Radiation Average"
       ,FAKE_MMI_URL_PREFIX + "solar_radiation_average"
       ,SI.WATT.getSymbol() + "/" + SI.METER.getSymbol() + "²"
    );
    
    @HomelessParameter(description="",source="SnoTel")
    public static final Phenomenon SOLAR_RADIATION_MAXIMUM = new PhenomenonImp(
        "Solar Radiation Maximum"
       ,FAKE_MMI_URL_PREFIX + "solar_radiation_maximum"
       ,SI.WATT.getSymbol() + "/" + SI.METER.getSymbol() + "²"
    );
    
    @HomelessParameter(description="",source="SnoTel")
    public static final Phenomenon SOLAR_RADIATION_MINIMUM = new PhenomenonImp(
        "Solar Radiation Maximum"
       ,FAKE_MMI_URL_PREFIX + "solar_radiation_minimum"
       ,SI.WATT.getSymbol() + "/" + SI.METER.getSymbol() + "²"
    );
    
    @HomelessParameter(description="",source="USGS WATER")
    public static final Phenomenon STREAMFLOW = new PhenomenonImp(
        "Streamflow"
       ,FAKE_MMI_URL_PREFIX + "streamflow"
       ,"cfs"
    );
    
    @HomelessParameter(description="",source="USGS WATER, SnoTel, HADS")
    public static final Phenomenon STREAM_GAGE_HEIGHT = new PhenomenonImp(
        "Stream Gage Height"
       ,FAKE_MMI_URL_PREFIX + "stream_gage_height"
       ,SI.METER.getSymbol()
    );
    
    @CFParameter
    public static final Phenomenon TOA_INCOMING_SHORTWAVE_FLUX =
        createStandardCfParameter( CFStandardNames.TOA_INCOMING_SHORTWAVE_FLUX );
    
    @CFParameter
    public static final Phenomenon TOA_OUTGOING_SHORTWAVE_FLUX =
        createStandardCfParameter( CFStandardNames.TOA_OUTGOING_SHORTWAVE_FLUX );

    @IOOSParameter
    public static final Phenomenon TURBIDITY =
        createStandardIoosParameter( IoosParameter.turbidity );
        
    @CFParameter
    public static final Phenomenon VISIBILITY_IN_AIR =
        createStandardCfParameter( CFStandardNames.VISIBILITY_IN_AIR );
    
    @CFParameter
    public static final Phenomenon WATER_SURFACE_HEIGHT_ABOVE_REFERENCE_DATUM =
        createStandardCfParameter( CFStandardNames.WATER_SURFACE_HEIGHT_ABOVE_REFERENCE_DATUM );

    @HomelessParameter(description="",source="USGS")
    public static final Phenomenon WATER_TEMPERATURE_INTRAGRAVEL = new PhenomenonImp(
        "Water Temperature Intragravel"
       ,FAKE_MMI_URL_PREFIX + "water_temperature_intragravel"
       ,SI.DEGREE_CELSIUS.getSymbol()
    );
    
    @CFParameter
    public static final Phenomenon WATER_VOLUME_TRANSPORT_INTO_SEA_WATER_FROM_RIVERS =
        createStandardCfParameter( CFStandardNames.WATER_VOLUME_TRANSPORT_INTO_SEA_WATER_FROM_RIVERS );
    
    @IOOSParameter
    public static final Phenomenon WAVE_DIRECTIONAL_SPREAD =
        createStandardIoosParameter( IoosParameter.wave_directional_spread );
    
    //Used in AOOS
    @HomelessParameter(description="",source="")
    public static final Phenomenon WEBCAM = new PhenomenonImp(
        "Webcam"
        ,FAKE_MMI_URL_PREFIX + "webcam"
    );
    
    @CFParameter
    public static final Phenomenon WIND_FROM_DIRECTION =
        createStandardCfParameter( CFStandardNames.WIND_FROM_DIRECTION );
    
    //Used in AOOS
    @HomelessParameter(description="",source="")
    public static final Phenomenon WIND_GENERATOR_CURRENT = new PhenomenonImp(
         "Wind Generator Current"
        ,FAKE_MMI_URL_PREFIX + "wind_generator_current"
        ,SI.AMPERE.getSymbol() + "/" + SI.HOUR.getSymbol()
    );

    @IOOSParameter
    public static final Phenomenon WIND_GUST_FROM_DIRECTION =
        createStandardIoosParameter( IoosParameter.wind_gust_from_direction );
    
    @CFParameter
    public static final Phenomenon WIND_SPEED =
        createStandardCfParameter( CFStandardNames.WIND_SPEED );
    
    @CFParameter
    public static final Phenomenon WIND_SPEED_OF_GUST =
        createStandardCfParameter( CFStandardNames.WIND_SPEED_OF_GUST );
    
    @HomelessParameter(description="",source="NOAA NOS CO-OPS")
    public static final Phenomenon WIND_VERTICAL_VELOCITY = new PhenomenonImp(
        "Wind Vertical Velocity"
       ,FAKE_MMI_URL_PREFIX + "wind_vertical_velocity"
       ,SI.METER.getSymbol() + "/" + SI.SECOND.getSymbol()
    );
}
