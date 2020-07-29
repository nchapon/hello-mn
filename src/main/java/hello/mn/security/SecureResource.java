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
    public Single<String> get(Authentication auth){
        return Single.just(auth.getName());
    }

    @Get("/admin")
    @Secured("mn-admin")
    public Single<Authentication> getAdmin(Authentication auth){
        return Single.just(auth);
    }



}
