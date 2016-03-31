package com.codecentric.hystrix.warstories.mapper;

import org.modelmapper.PropertyMap;
import com.codecentric.hystrix.warstories.entities.Connote;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;

import java.time.format.DateTimeFormatter;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ConnoteMapper extends PropertyMap<Connote, ConnoteDTO> {

    protected void configure() {
        map().setConnote(source.getConnote());
        map().setFallback(false); // default = false
    }
};
