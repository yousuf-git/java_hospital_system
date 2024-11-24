// package com.project.hospital.configs;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;

// import java.util.Arrays;
// import java.util.Collections;

// @Configuration
// public class CorsConfig {

//     @Bean
//     public CorsFilter corsFilter() {
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         CorsConfiguration config = new CorsConfiguration();
        
//         // Allow credentials (e.g., cookies, Authorization header)
//         config.setAllowCredentials(true);
        
//         // Allow all origins (avoid in production; define a proper list)
//         config.setAllowedOrigins(Collections.singletonList("*"));
        
//         // Define allowed headers
//         config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        
//         // Define allowed HTTP methods
//         config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        
//         // Apply this CORS configuration to all endpoints
//         source.registerCorsConfiguration("/**", config);
        
//         // Return the CORS filter
//         return new CorsFilter(source);
//     }
// }

