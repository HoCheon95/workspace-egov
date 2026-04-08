package kr.or.ddit.props.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
public class DataBasePropertyDto {
    private String propertyName;
    private String propertyValue;
    private String description;
}
