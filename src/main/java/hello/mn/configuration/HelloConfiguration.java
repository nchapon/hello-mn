package hello.mn.configuration;


import io.micronaut.context.annotation.ConfigurationProperties;


@ConfigurationProperties("hello-mn")
public interface HelloConfiguration {

    String getGretting();

}
