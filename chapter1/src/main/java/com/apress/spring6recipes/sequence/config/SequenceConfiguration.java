package com.apress.spring6recipes.sequence.config;

import com.apress.spring6recipes.sequence.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

@Configuration
public class SequenceConfiguration {

    @Bean
    @DependsOn("datePrefixGenerator")
    public Sequence sequenceGenerator() {
        return new Sequence("A", 100000);
    }
}

//@Configuration
//@Import(PrefixConfiguration.class)
//public class SequenceConfiguration {
//
//    @Bean
//    public Sequence sequence() {
//        var seqgen = new Sequence("IT", "IT", "IT");
//        seqgen.setInitial(100000);
//        return seqgen;
//    }
//
//    @Bean
//    public Sequence sequence(PrefixGenerator prefixGenerator) {
//        return new Sequence(prefixGenerator, "A", 100000);
//    }
//
//    @Bean
//    public DatePrefixGenerator datePrefixGenerator() {
//        return new DatePrefixGenerator("yyyyMMdd");
//    }
//
//    @Bean
//    public Sequence sequenceGenerator(PrefixGenerator prefixGenerator) {
//        var generator = new Sequence("A", 100000);
//        generator.setPrefixGenerator(prefixGenerator);
//        return generator;
//    }
//}
