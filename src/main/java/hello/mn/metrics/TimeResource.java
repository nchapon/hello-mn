package hello.mn.metrics;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.time.Instant;

@Controller("/time")
public class TimeResource {

    private final MeterRegistry meterRegistry;

    public TimeResource(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public Instant getTime() {

        meterRegistry.counter("time.now").increment();

        // Gauge
        //Iterable<Tag> tags=
        //meterRegistry.gauge("time.offset.from.utc",)

        return Instant.now();
    }








}
