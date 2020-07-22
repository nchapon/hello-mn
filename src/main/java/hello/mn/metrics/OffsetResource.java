package hello.mn.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Calendar;
import java.util.TimeZone;

@Controller("/offset")
public class OffsetResource {

    private final MeterRegistry meterRegistry;

    public OffsetResource(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public long getOffset(){
        int offsetInHours = TimeZone.getDefault().getOffset(Calendar.ZONE_OFFSET) / (3600 * 1000);

        // No found a simple way to add a metric without a controller
        meterRegistry.gauge("time.offset.from.utc",offsetInHours);
        return offsetInHours;
    }

}
