package com.desafiolatam.desafioface.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Samuel on 03-10-2017.
 */

public class LoginInterceptor {

    public static final String BASE_URL="http://empieza.desafiolatam.com/";
    public Session get(){

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
            /*Never forget about adding the converter, otherwise you can not parse the data*/
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Session someRequest = interceptor.create(Session.class);
    /*The interceptor must return an interface, is the same interface where you wrote the methods for the request http*/
        return someRequest;


    }

}
