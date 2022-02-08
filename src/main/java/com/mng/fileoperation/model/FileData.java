package com.mng.fileoperation.model;

import com.mng.fileoperation.constant.FileExtension;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@ApiModel(value = "FileData Api model documentation", description = "Model")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of fileData object")
    private Long fileId;

    @ApiModelProperty(value = "File name field of fileData object")
    private String fileName;

    @ApiModelProperty(value = "File path of fileData object")
    private String path;

    @ApiModelProperty(value = "File size field of fileData object")
    private Long size;

    @ApiModelProperty(value = "File extension field of fileData object")
    private FileExtension fileExtension;


}
