package com.desafiolatam.desafioface.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Samuel on 02-11-2017.
 */

public class IdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
      String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", token);
    }
}
