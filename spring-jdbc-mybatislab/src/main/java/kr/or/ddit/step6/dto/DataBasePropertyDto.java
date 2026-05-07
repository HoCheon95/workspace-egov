package kr.or.ddit.step6.dto;


import java.io.Serializable;

import lombok.Data;

@Data
public class DataBasePropertyDto implements Serializable{
    private String PROPERTY_NAME;
    private String PROPERTY_VALUE;
    private String DESCRIPTION;
}
