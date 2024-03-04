package com.nhnacademy.spring.hw.condition;

import java.io.FileNotFoundException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JsonFormatCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    Environment environment = context.getEnvironment();
    String dataFilePath = environment.getProperty("filePath");
    return dataFilePath.contains(".json");
  }
}
