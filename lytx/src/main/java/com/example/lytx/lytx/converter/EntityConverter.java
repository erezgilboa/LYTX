package com.example.lytx.lytx.converter;

import java.util.List;

public interface EntityConverter<F, DB> {

    public DB convertFromF(F f);


    public F convertFromDB(DB db);


    public List<DB> convertListFromF(List<F> f);


    public List<F> convertListFromDB(List<DB> db);


}
