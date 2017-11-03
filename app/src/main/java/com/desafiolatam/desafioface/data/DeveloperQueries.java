package com.desafiolatam.desafioface.data;

import com.desafiolatam.desafioface.models.Developer;

import java.util.List;

/**
 * Created by Samuel on 19-10-2017.
 */

public class DeveloperQueries {

    public List<Developer> all(){
        return Developer.listAll(Developer.class);
    }




}
