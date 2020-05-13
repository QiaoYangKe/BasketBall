package com.example.basketball;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> aNumber;
    private MutableLiveData<Integer> bNumber;
    private ArrayList<HashMap<String,Integer>> list;

    public MutableLiveData<Integer> getANumber() {
        if(aNumber == null) {
            aNumber = new MutableLiveData<Integer>();
            aNumber.setValue(0);
        }
        return aNumber;
    }

    public MutableLiveData<Integer> getBNumber() {
        if(bNumber == null) {
            bNumber = new MutableLiveData<Integer>();
            bNumber.setValue(0);
        }
        return bNumber;
    }

    public void listAdd(String name, Integer number) {
        if(list == null){
            list = new ArrayList<HashMap<String,Integer>>();
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put(name,number);
        list.add(map);
    }

    public void refresh () {
        aNumber.setValue(0);
        bNumber.setValue(0);
    }

    public void addATeam(Integer n) {
        listAdd("a",n);
        aNumber.setValue(aNumber.getValue() + n);
    }

    public void addBTeam(Integer n) {
        bNumber.setValue(bNumber.getValue() + n);
        listAdd("b",n);
    }

    public void undo() {
        if(list!= null && list.size() > 0) {
            HashMap<String,Integer> map = list.get(list.size() -1);
            if(map.get("a")!=null) {
                aNumber.setValue(aNumber.getValue() - map.get("a"));
            } else {
                bNumber.setValue(bNumber.getValue() - map.get("b"));
            }
            list.remove(map);
        }
    }
}
