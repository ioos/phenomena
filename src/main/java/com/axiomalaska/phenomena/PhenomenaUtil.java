package com.axiomalaska.phenomena;

import org.apache.commons.lang3.text.WordUtils;

public class PhenomenaUtil {
    public static final String getNameFromUri(Phenomenon phenomenon){
        return getNameFromUri(phenomenon.getId());
    }

    public static final String getNameFromUri(String phenomenonId){
        String[] uriParts = phenomenonId.split("/|:");
        return uriParts[uriParts.length - 1];
    }

    public static final String convertUnderscoredNameToTitleCase(String underscoredName){
        String lowercaseName = underscoredName.replace('_', ' ');
        return WordUtils.capitalize( lowercaseName );
    }
}
