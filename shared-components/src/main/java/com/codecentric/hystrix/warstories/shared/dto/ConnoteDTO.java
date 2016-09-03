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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConnoteDTO{");
        sb.append("connote=").append(connote);
        sb.append("fallback=").append(this.isFallback());
        sb.append("fallback-msg=").append(this.getErrorMsg());
        sb.append('}');
        return sb.toString();
    }
}
