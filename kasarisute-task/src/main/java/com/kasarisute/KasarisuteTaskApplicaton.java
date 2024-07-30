package com.kasarisute;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
   excludeFilters = {@Filter(
   type = FilterType.CUSTOM,
   classes = {TypeExcludeFilter.class}
), @Filter(
   type = FilterType.CUSTOM,
   classes = {AutoConfigurationExcludeFilter.class}
)}
)
public class KasarisuteTaskApplicaton {
    public static void main(String[] args) {

        // TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // SpringApplication.run(KasarisuteTaskApplicaton.class, args);
    }

}
