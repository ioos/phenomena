package com.axiomalaska.phenomena;

public class UnitCreationException extends Exception {
    private static final long serialVersionUID = 583180682480324670L;
    
    public UnitCreationException( Exception originalException ){
        super( originalException.getClass().getName() + ": " + originalException.getMessage() );
    }    
}