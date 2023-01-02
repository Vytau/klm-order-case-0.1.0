package com.afkl.travel.exercise.service;

import io.micrometer.core.instrument.MeterRegistry;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MetricService {

    private final MeterRegistry registry;

    private AtomicLong maxResponseGauge;
    private AtomicLong avgResponseGauge;

    private long maxResponseTime;
    private long avgResponseTime;
    private long responseCounter;

    @PostConstruct
    public void init() {
        maxResponseGauge = registry.gauge("response.max", new AtomicLong(0));
        avgResponseGauge = registry.gauge("response.avg", new AtomicLong(0));

        responseCounter = 0;
        avgResponseTime = 1;
        maxResponseTime = 0;
    }

    public void increaseResponseCounter(int status) {

        if (status >= 200 && status < 300) {
            registry.counter("counter.response.ok").increment();
        } else if (status >= 400 && status < 500) {
            registry.counter("counter.response.400").increment();
        } else if (status >= 500 && status < 600) {
            registry.counter("counter.response.500").increment();
        }

    }

    public void increaseRequestCounter() {
        registry.counter("counter.request").increment();
    }

    public void updateResponseTimes(long elapseTime) {

        if (responseCounter == 0) {
            avgResponseTime = elapseTime;
        } else {
            avgResponseTime = ((avgResponseTime * responseCounter) + elapseTime) / (responseCounter + 1);
        }
        responseCounter++;

        maxResponseTime = Math.max(maxResponseTime, elapseTime);

        maxResponseGauge.set(maxResponseTime);
        avgResponseGauge.set(avgResponseTime);
    }
}
