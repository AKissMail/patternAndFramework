package de.gruppeo.wise2122_java_server.controller;

import de.gruppeo.wise2122_java_server.model.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * Ursprünglich sollte diese Klasse genutzt werden, um Kategorien zu konvertieren.
 * Aktuell wird die Klasse nicht aktiv genutzt, bleibt aber im Repo, um diese ggf. später nutzen zu können.
 */
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public Category convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Category.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}