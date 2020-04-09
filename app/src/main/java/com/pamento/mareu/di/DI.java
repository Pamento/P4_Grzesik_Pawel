package com.pamento.mareu.di;

import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.service.FakeApiService;

public class DI {
    private static ApiService sApiService = new FakeApiService();
    public static ApiService getApiService() {return sApiService;}
    public static ApiService getNewInstanceApiService() {
        return new FakeApiService();
    }
}
