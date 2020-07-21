package hello.mn.domain;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;

@Client("fruity")
//@Retryable(attempts = "5", delay = "100ms")
@CircuitBreaker
public interface FruityServiceClient extends FruityService {
}
