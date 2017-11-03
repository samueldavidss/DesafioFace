package com.desafiolatam.desafioface.views.login;

import com.desafiolatam.desafioface.models.CurrentUser;
import com.desafiolatam.desafioface.network.LoginInterceptor;
import com.desafiolatam.desafioface.network.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Samuel on 03-10-2017.
 */

public class SingIn {


    private SessionCallback callback;

    public SingIn(SessionCallback callback) {
        this.callback = callback;
    }
    public void toServer(String email, String password){

        if (email.trim().length()<=0 || password.trim().length()<= 0){

            callback.requieredField();
        }else {
                Session session =  new LoginInterceptor().get();
                Call<CurrentUser> call = session.loggin(email, password);
                call.enqueue(new Callback<CurrentUser>() {
                    @Override
                    public void onResponse(Call<CurrentUser> call, Response<CurrentUser> response) {
                        if (200==response.code()&& response.isSuccessful()){
                            CurrentUser user = response.body();
                            user.created();
                            callback.success();
                        }else {
                            callback.fail();
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentUser> call, Throwable t) {
                        callback.fail();
                    }
                });
            }
        }
    }





