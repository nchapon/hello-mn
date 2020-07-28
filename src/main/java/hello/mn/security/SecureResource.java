package hello.mn.security;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.reactivex.Single;

@Secured("isAuthenticated()")
@Controller("/secure")
public class SecureResource {

    @Get
    @Secured("mn-user")
    public Single<Authentication> get(Authentication auth){
        return Single.just(auth);
    }

    @Get("/admin")
    @Secured("mn-admin")
    public Single<String> getAdmin(Authentication auth){
        return Single.just("You're admin !");
    }



}
