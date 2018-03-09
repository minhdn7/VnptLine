package com.vnpt.vnptline.domain.model.pojo.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MinhDN on 1/2/2018.
 */

public class TokenDevResponse {
    @Getter @Setter
    private String token;

    @Getter @Setter
    private Integer responseCode;

    @Getter @Setter
    private String responseMessage;
}
