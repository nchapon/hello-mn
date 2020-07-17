package hello.mn;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class HelloClientSpec {

    @Inject
    HelloClient client;

    @Test
    void testHelloWorldResponse() {
        Assertions.assertEquals("Bonjour Micronaut", client.hello().blockingGet());
    }
}
