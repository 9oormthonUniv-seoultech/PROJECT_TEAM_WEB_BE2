package com.pocket.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoDocument {

    private String place_name;
    private String road_address_name;
    private String x;
    private String y;
    private String category_name;
}
