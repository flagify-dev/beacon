package com.flagify.beacon.util;

import com.github.f4b6a3.uuid.UuidCreator;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class UuidGenerator {
    public UUID generateV7Uuid() {
        return UuidCreator.getTimeOrderedEpoch();
    }
}
