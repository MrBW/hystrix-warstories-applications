package com.codecentric.hystrix.warstories.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Benjamin Wilms (xd98870)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnoteDTO extends FallbackDTO {

    private Long connote;

    public Long getConnote() {
        return connote;
    }

    public void setConnote(Long connote) {
        this.connote = connote;
    }
}
