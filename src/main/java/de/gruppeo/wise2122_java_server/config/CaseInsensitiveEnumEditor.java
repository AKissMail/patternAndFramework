package de.gruppeo.wise2122_java_server.config;

import java.beans.PropertyEditorSupport;

/**
 * Einstellungen zur Groß- und Kleinschreibung für den Datentyp ENUM manipulieren.
 * Dies wird benötigt, wenn ENUM Werte per URL (kleingeschrieben) von der REST-API weiterverarbeitet werden sollen
 */
public class CaseInsensitiveEnumEditor extends PropertyEditorSupport {
    @SuppressWarnings("rawtypes")
    private final Class<? extends Enum> enumType;
    private final String[] enumNames;

    /**
     * Instantiates a new Case insensitive enum editor.
     *
     * @param type the type
     */
    public CaseInsensitiveEnumEditor(Class<?> type) {
        this.enumType = type.asSubclass(Enum.class);
        var values = type.getEnumConstants();
        if (values == null) {
            throw new IllegalArgumentException("Unsupported " + type);
        }
        this.enumNames = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            this.enumNames[i] = ((Enum<?>) values[i]).name();
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.isEmpty()) {
            setValue(null);
            return;
        }
        for (String n : enumNames) {
            if (n.equalsIgnoreCase(text)) {
                @SuppressWarnings("unchecked")
                var newValue = Enum.valueOf(enumType, n);
                setValue(newValue);
                return;
            }
        }
        throw new IllegalArgumentException("No enum constant " + enumType.getCanonicalName() + " equals ignore case " + text);
    }

}