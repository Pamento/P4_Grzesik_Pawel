package com.pamento.mareu.di;

import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.service.MockApiService;

public class DI {
    private static ApiService sApiService = new MockApiService();
    public static ApiService getApiService() {return sApiService;}
    public static ApiService getNewInstanceApiService() {
        return new MockApiService();
    }
}
