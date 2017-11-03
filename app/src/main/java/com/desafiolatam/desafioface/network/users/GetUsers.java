package com.desafiolatam.desafioface.network.users;

/**
 * Created by Samuel on 11-10-2017.
 */


import android.os.AsyncTask;
import android.util.Log;

import com.desafiolatam.desafioface.models.Developer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class GetUsers extends AsyncTask <Map<String,String>,Integer,Integer >{

    private int addicionalPages;
    private int result;
    private Map<String, String> queryMap;
    private final Users request = new UserInterceptor().get();

    public GetUsers(int addicionalPages) {
        this.addicionalPages = addicionalPages;
    }

    @Override
    protected Integer doInBackground(Map<String, String>... params) {
       queryMap = params[0];
        if (addicionalPages < 0 ) {
            while (200 == Connect()) {
                increasesPage();
            }
        }else{
                while (addicionalPages >=0){
                    addicionalPages --;
                    Connect();
                    increasesPage();
                }
            }

        return null;
    }


    private void increasesPage (){

        int page = Integer.parseInt(queryMap.get("page"));
        page++;
        queryMap.put("page", String.valueOf(page));

    }

    private int Connect (){

        int code = 666;
        Call<Developer[]> call = request.get(queryMap);
        try {
            Response<Developer[]> response = call.execute();
            code = response.code();
            if(200== code && response.isSuccessful()){
                Developer[] developers = response.body();
                if (developers != null && developers.length > 0){
                    Log.d("Developers",String.valueOf(developers.length));
                    for (Developer serDev: developers) {
                        List<Developer> localDev =  Developer.find(Developer.class,"serverId = ?", String.valueOf(serDev.getId()));
                        if (localDev != null && localDev.size() > 0){
                            Developer local = localDev.get(0);
                            local.setEmail(serDev.getEmail());
                            local.setPhoto_url(serDev.getPhoto_url());
                            local.save();
                        }else {

                            serDev.created();
                        }

                    }

                }
            }else {
                code = 777;
            }


        }catch (IOException e ){

            e.printStackTrace();
            code=888;
        }
        result = code;
        return result;

    }


}
