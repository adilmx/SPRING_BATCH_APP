package com.adilmx.spring_batch_app.batch.mapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;


public class FieldMapper extends BeanWrapperFieldSetMapper {
    @Override
    public void setTargetType(Class type) {
        super.setTargetType(type);
    }
}
