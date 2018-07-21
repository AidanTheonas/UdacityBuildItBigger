package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.servicebomba.jokesjavalibrary.TellJoke;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com"
        )
)
public class MyEndpoint {

    @ApiMethod(name = "retrieveJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();
        TellJoke tellJoke = new TellJoke();
        String joke = tellJoke.getRandomJoke();
        response.setData(joke);

        return response;
    }

}
