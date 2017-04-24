package com.hangduykhiem.iot;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hduykhiem on 24/04/2017.
 */

public class RetrofitHelper {

  private static SensorAPI sensorService;
  private static Retrofit sensorRetrofit;

  private RetrofitHelper() {

  }

  public static SensorAPI getSensorService() {



    if (sensorRetrofit == null) {

      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


      sensorRetrofit = new Retrofit.Builder()
          .baseUrl("http://192.168.32.83:9000")
          .addConverterFactory(GsonConverterFactory.create())
          .client(client)
          .build();
    }
    if (sensorService == null) {
      sensorService = sensorRetrofit.create(SensorAPI.class);
    }

    return sensorService;
  }

}
