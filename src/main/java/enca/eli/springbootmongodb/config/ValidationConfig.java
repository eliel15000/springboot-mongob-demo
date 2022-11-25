package enca.eli.springbootmongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    // This EventListener is going to trigger before saving any data to the database
    // and while saving the data, this is going to trigger is the user send any null values.
    // so, this is going to trigger a ConstraintViolationException
    @Bean
    public ValidatingMongoEventListener validationMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    // This LocalValidatorFactoryBean is the implementation class for the validation.
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
