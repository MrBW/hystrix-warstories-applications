package com.codecentric.hystrix.warstories.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.codecentric.hystrix.warstories.mapper.CustomerMapper;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Configuration
public class CustomerConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        // Init ModelMapper
        mapper.addMappings(new CustomerMapper());
        return mapper;
    }
}
