package com.mng.fileoperation.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ApiModel(value = "Person Api model documentation", description = "Model")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of person object")
    private long id;

    @ApiModelProperty(value = "User name field of person object")
    private String username;

    @ApiModelProperty(value = "Password field of person object")
    private String password;
}
