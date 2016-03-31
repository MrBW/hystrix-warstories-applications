package com.codecentric.hystrix.warstories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Entity
public class Connote {

    @Id
    private Long connote;

    public Long getConnote() {
        return connote;
    }

    public void setConnote(Long connote) {
        this.connote = connote;
    }
}
