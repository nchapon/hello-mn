package hello.mn.domain;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Recoverable;
import io.micronaut.retry.annotation.Retryable;

@Client("fruity")
@Retryable(attempts = "5", delay = "100ms")
@Recoverable(api=FruityService.class)
public interface FruityServiceClient extends FruityService {
}
