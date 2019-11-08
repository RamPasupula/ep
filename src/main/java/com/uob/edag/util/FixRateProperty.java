package com.uob.edag.util;

import org.springframework.stereotype.Component;

@Component
public class FixRateProperty {
    private long fixRate = 500;

    public Long getFixRate() {
        return fixRate;
    }

    public void setFixRate(Long fixRate) {
        this.fixRate = fixRate;
    }
}