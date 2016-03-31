package com.codecentric.hystrix.warstories.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codecentric.hystrix.warstories.service.ConnoteService;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RestController
@RequestMapping("connote/")
public class ConnoteController {

    private static final Log LOGGER = LogFactory.getLog(ConnoteController.class);
    private ConnoteService connoteService;

    @Autowired
    public ConnoteController(ConnoteService connoteService) {
        this.connoteService = connoteService;
    }

    @RequestMapping("create")
    public ConnoteDTO createConnote() {
        LOGGER.debug(LOGGER.isDebugEnabled() ? "Create new connote from legacy backend system" : null);

        return connoteService.createConnote();
    }
}
