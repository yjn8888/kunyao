package com.kunyao.message.rabbitmq.autoconfigure.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class RabbitConfigConfigurationSelector implements ImportSelector, Ordered {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                annotationMetadata.getAnnotationAttributes(EnableRabbitConfig.class.getName()));

        boolean multiple = attributes.getBoolean("multiple");

        if (multiple) {
            return of(RabbitConfigConfiguration.Multiple.class.getName());
        } else {
            return of(RabbitConfigConfiguration.Single.class.getName());
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
