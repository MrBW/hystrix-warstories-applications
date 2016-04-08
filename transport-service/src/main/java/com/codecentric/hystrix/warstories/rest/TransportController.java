package com.codecentric.hystrix.warstories.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codecentric.hystrix.warstories.dto.ShippmentDTO;
import com.codecentric.hystrix.warstories.service.ConnoteRemoteService;
import com.codecentric.hystrix.warstories.service.CustomerRemoteService;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;
import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RestController
public class TransportController {

    private static final Log LOGGER = LogFactory.getLog(TransportController.class);

    private ConnoteRemoteService connoteRemoteService;

    private CustomerRemoteService customerRemoteService;

    @Autowired
    public TransportController(ConnoteRemoteService connoteRemoteService, CustomerRemoteService customerRemoteService) {
        this.connoteRemoteService = connoteRemoteService;
        this.customerRemoteService = customerRemoteService;
    }

    @RequestMapping("/create/{accountnumber}")
    public ResponseEntity<ShippmentDTO> createShippment(@PathVariable long accountnumber) {
        ConnoteDTO connote = null;
        CustomerDTO customer = null;
        try {
            // create new Connote from remote service
            connote = connoteRemoteService.createConnote();

            // search for customer by remote service
            customer = customerRemoteService.findCustomer(accountnumber);

        } catch (Exception e) {
            String msg = "Unable to create shippment";
            LOGGER.error(msg, e);
            return new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ShippmentDTO shippmentDTO = new ShippmentDTO(connote, customer);
        // All ok, finished
        return new ResponseEntity(shippmentDTO, HttpStatus.OK);

    }
}
