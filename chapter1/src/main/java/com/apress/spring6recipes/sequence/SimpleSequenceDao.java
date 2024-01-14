package com.apress.spring6recipes.sequence;

import com.apress.spring6recipes.sequence.Sequence;
import com.apress.spring6recipes.sequence.SequenceDao;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component("sequenceDao")
class SimpleSequenceDao implements SequenceDao {

    private final Map<String, Sequence> sequences = new ConcurrentHashMap<>();
    private final Map<String, AtomicInteger> values = new ConcurrentHashMap<>();

    SimpleSequenceDao() {
        sequences.put("IT", new Sequence("IT", "30", "A"));
        values.put("IT", new AtomicInteger(100000));
    }

    @Override
    public Sequence getSequence(String sequenceId) {
        return sequences.get(sequenceId);
    }

    @Override
    public int getNextValue(String sequenceId) {
        var value = values.get(sequenceId);
        return value.getAndIncrement();
    }
}
