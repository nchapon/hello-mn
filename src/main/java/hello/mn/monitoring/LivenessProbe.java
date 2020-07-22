package hello.mn.monitoring;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.management.endpoint.annotation.Endpoint;
import io.micronaut.management.endpoint.annotation.Read;

@Endpoint(id = "live",
        prefix = "custom",
        defaultEnabled = true,
        defaultSensitive = false)
public class LivenessProbe {

    @Read(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Status> returnStatus() {
        return HttpResponse.ok().body(Status.UP);
    }
}
