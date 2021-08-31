package com.example.mstransaction.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Product {

    @Field(name = "productName")
    private String productName;

    @Field(name = "productType")
    private String productType;

    @Field(name = "condition")
    private Rules rules;
}
