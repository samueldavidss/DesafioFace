package com.desafiolatam.desafioface.models;

import com.orm.SugarRecord;

/**
 * Created by Samuel on 02-10-2017.
 */

public class BaseSugar extends SugarRecord{


    private long server_id;

    public BaseSugar() {
    }


    public long getServer_id() {
        return server_id;
    }

    public void setServer_id(long server_id) {
        this.server_id = server_id;
    }


    public void created(){
        setServer_id(getId());
        setId(null);
        save();
    }


    @Override
    public long save() {
        return super.save();
    }
}
