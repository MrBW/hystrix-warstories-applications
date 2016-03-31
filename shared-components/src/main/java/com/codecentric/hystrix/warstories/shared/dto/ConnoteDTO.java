package com.codecentric.hystrix.warstories.shared.dto;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ConnoteDTO {

    private Long connote;

    private boolean fallback = false; // default = false

    public boolean isFallback() {
        return fallback;
    }

    public void setFallback(boolean fallback) {
        this.fallback = fallback;
    }

    public Long getConnote() {
        return connote;
    }

    public void setConnote(Long connote) {
        this.connote = connote;
    }
}
