package com.hangduykhiem.iot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hduykhiem on 24/04/2017.
 */

public interface SensorAPI {

  @GET("api/device/{deviceId}/sensor/{sensorId}")
  Call<SensorModel> loadSensorValue(@Path("deviceId") String deviceId,
      @Path("sensorId") String SensorId);

}
