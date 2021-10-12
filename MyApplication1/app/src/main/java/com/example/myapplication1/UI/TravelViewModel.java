package com.example.myapplication1.UI;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication1.Data.TravelRepository;
import com.example.myapplication1.Entities.Travel;

public class TravelViewModel extends ViewModel {
    private TravelRepository TravelRepository=new TravelRepository();
    public MutableLiveData<Boolean> getLivedata() {
        return livedata;
    }

    MutableLiveData<Boolean> livedata=new MutableLiveData<Boolean>();

    public MutableLiveData<Boolean> getIsSuccess() {
        return TravelRepository.getDataBase().getIsSuccess();
    }
    public void insertTravel( Travel travel) {
        TravelRepository.insertTravel(travel);
        livedata.postValue(TravelRepository.getLivedata().getValue());
    }

}
