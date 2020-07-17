package hello.mn.configuration;


import io.micronaut.context.annotation.ConfigurationProperties;


@ConfigurationProperties("hello")
public interface HelloConfiguration {

    String getGretting();

}
