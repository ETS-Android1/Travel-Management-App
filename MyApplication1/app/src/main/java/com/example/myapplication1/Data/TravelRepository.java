package com.example.myapplication1.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication1.Entities.Travel;

public class TravelRepository {

    protected TravelDataSource dataBase = new TravelDataSource();

    public MutableLiveData<Boolean> getLivedata() {
        return livedata;
    }

    MutableLiveData<Boolean> livedata=new MutableLiveData<Boolean>();
    public TravelDataSource getDataBase() {
        return dataBase;
    }

    public void insertTravel(Travel travel) {
        dataBase.insertTravel(travel);
        livedata.postValue(dataBase.getIsSuccess().getValue());

    }



}
