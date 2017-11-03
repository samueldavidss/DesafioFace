package com.desafiolatam.desafioface.views.splash;

import com.desafiolatam.desafioface.data.CurrentUserQueries;
import com.desafiolatam.desafioface.models.CurrentUser;

import java.util.List;

/**
 * Created by Samuel on 02-10-2017.
 */

public class LoginValidation {
    private LoginCallback callback;


    public LoginValidation(LoginCallback callback) {
        this.callback = callback;
    }


    public void init(){

        if (new CurrentUserQueries().isLogged()){
            callback.signed();
        }else {
            callback.singup();
        }

    }
}
