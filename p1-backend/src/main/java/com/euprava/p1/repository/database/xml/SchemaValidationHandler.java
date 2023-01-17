package com.euprava.p1.repository.database.xml;

import jakarta.xml.bind.ValidationEvent;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.ValidationEventLocator;

public class SchemaValidationHandler implements ValidationEventHandler {
    @Override
    public boolean handleEvent(ValidationEvent event) {
        // Ako nije u pitanju WARNING metoda vraća false
        if (event.getSeverity() != ValidationEvent.WARNING) {
            ValidationEventLocator validationEventLocator = event.getLocator();
            System.out.println("ERROR: Line "
                    + validationEventLocator.getLineNumber() + ": Col"
                    + validationEventLocator.getColumnNumber() + ": "
                    + event.getMessage());

            // Dalje izvršavanje se prekida
            return false;
        } else {
            ValidationEventLocator validationEventLocator = event.getLocator();
            System.out.println("WARNING: Line "
                    + validationEventLocator.getLineNumber() + ": Col"
                    + validationEventLocator.getColumnNumber() + ": "
                    + event.getMessage());

            // Nastavlja se dalje izvršavanje
            return true;
        }
    }
}
