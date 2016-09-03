package com.codecentric.hystrix.warstories.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Fallback DTO
 * @author Benjamin Wilms (xd98870)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FallbackDTO {
    private boolean fallback;

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isFallback() {
        return fallback;
    }

    public void setFallback(boolean fallback) {
        this.fallback = fallback;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FallbackDTO{");
        sb.append("errorMsg='").append(errorMsg).append('\'');
        sb.append(", fallback=").append(fallback);
        sb.append('}');
        return sb.toString();
    }
}
