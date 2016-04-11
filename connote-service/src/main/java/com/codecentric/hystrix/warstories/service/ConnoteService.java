package com.codecentric.hystrix.warstories.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codecentric.hystrix.warstories.entities.Connote;
import com.codecentric.hystrix.warstories.mapper.ConnoteMapper;
import com.codecentric.hystrix.warstories.repository.ConnoteRepository;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;
import com.codecentric.hystrix.warstories.shared.utils.ChaosMonkey;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Service
public class ConnoteService {

    private static final Log LOGGER = LogFactory.getLog(ConnoteService.class);

    private ConnoteRepository connoteRepository;

    private ChaosMonkey chaosMonkey;

    private ModelMapper modelMapper;

    // Fallback Write-Cache
    private Map<Long, ConnoteDTO> fallbackCache = Collections.synchronizedMap(new HashMap<Long, ConnoteDTO>());

    // use simulated legacy system?
    private DynamicBooleanProperty chaosMonkeyActive =
        DynamicPropertyFactory.getInstance().getBooleanProperty("chaos.monkey.active", false);

    @Autowired
    public ConnoteService(ConnoteRepository connoteRepository, ChaosMonkey chaosMonkey) {
        this.connoteRepository = connoteRepository;
        this.chaosMonkey = chaosMonkey;

        // Init ModelMapper
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new ConnoteMapper());
    }

    @HystrixCommand(fallbackMethod = "fallbackCache", commandKey = "ConnoteServiceCmdKey", threadPoolKey = "ConnoteServiceThreadPool")
    public ConnoteDTO createConnote() {

        Long connote = createRandomConnote("33");

        LOGGER.debug(LOGGER.isDebugEnabled() ? "Chaos Monkey is active: " + chaosMonkeyActive.get() : null);

        if (chaosMonkeyActive.get()) {

            // call the monkey to get in trouble
            chaosMonkey.iWantTrouble();
        }
        // Create new connote object in db
        Connote con = new Connote();
        con.setConnote(connote);

        // save via JPA repository
        Connote connoteCreated = connoteRepository.save(con);

        return modelMapper.map(connoteCreated, ConnoteDTO.class);

    }

    /***
     * Fallback Cache used by Hstrix, just to simulate temp. storage
     */
    private ConnoteDTO fallbackCache(Throwable throwable) {

        LOGGER.debug(LOGGER.isDebugEnabled() ? "Fallback Connote" : null);

        ConnoteDTO dto = new ConnoteDTO();
        dto.setFallback(true);

        String msg = "Connote > Legacy: ";

        if (throwable != null)
            dto.setErrorMsg(msg + throwable.getMessage());
        else
            dto.setErrorMsg(msg + "Timeout");

        dto.setConnote(createRandomConnote("99"));

        return dto;

    }

    /***
     * Creates new random and unique connote
     * @param range connote range starts with
     * @return connote between 100000 - 999999
     */
    private Long createRandomConnote(String range) {
        Long connote;

        do {

            connote = NumberUtils.createLong(range + randomNumber());

        } while (isUnique(connote));

        return connote;
    }

    private boolean isUnique(Long connote) {
        // 1. Search in repository

        Connote conRepo = connoteRepository.findOne(connote);

        if (conRepo != null)
            return false; // found by connote id

        // 2. Search in fallbackCache
        return fallbackCache.containsKey(connote);
    }

    private long randomNumber() {
        return RandomUtils.nextLong(1000000, 9999999);
    }
}
