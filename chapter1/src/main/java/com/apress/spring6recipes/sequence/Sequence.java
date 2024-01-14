package com.apress.spring6recipes.sequence;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

    @Autowired
    @Qualifier("datePrefixGenerator")
    @Resource
    @DatePrefixAnnotation
    private PrefixGenerator prefixGenerator;
    @Autowired
    private Map<String, PrefixGenerator> prefixGenerators;
    private final AtomicInteger counter = new AtomicInteger();
    private final String id;
    private final String prefix;
    private final String suffix;

    public Sequence(String id, String prefix, String suffix) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setInitial(int initial) {
        this.counter.set(initial);
    }

    public String nextValue() {
        return prefix + counter.getAndIncrement() + suffix;
    }

    @Autowired(required = false)
    public void setPrefixGenerator(ObjectProvider<PrefixGenerator> prefixGeneratorProvider) {
        this.prefixGenerator = prefixGeneratorProvider.getIfUnique();
    }

    @Autowired
    public void setPrefixGenerator(@Qualifier("datePrefixGenerator") PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    @Autowired
    public void myOwnCustomInjectionName(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }
}
