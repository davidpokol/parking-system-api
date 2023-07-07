package hu.foursys.ParkingSystem.configuration;

import hu.foursys.ParkingSystem.util.RequestUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RequestUtil requestUtil() {
        return new RequestUtil();
    }
}
