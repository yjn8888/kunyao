package com.kunyao.core.spring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class ConfigConfigurationSelector implements ImportSelector, Ordered {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                annotationMetadata.getAnnotationAttributes(EnableConfig.class.getName()));

        boolean multiple = attributes.getBoolean("multiple");

        if (multiple) {
            return of(attributes.getClass("multipleClass").getName());
        } else {
            return of(attributes.getClass("singleClass").getName());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private static <T> T[] of(T... values) {
        return values;
    }
}
