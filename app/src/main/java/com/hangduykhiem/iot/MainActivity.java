package com.hangduykhiem.iot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  public SensorAPI sensorService;

  @BindView(R.id.editText)
  EditText sensorEditText;
  @BindView(R.id.editText2)
  EditText deviceEditText;
  @BindView(R.id.textView)
  TextView sensorValue;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    if (sensorService == null) {
      sensorService = RetrofitHelper.getSensorService();
    }
  }

  @OnClick(R.id.button)
  public void getSensorValue() {

    Call<SensorModel> sensorModelCall =
        sensorService.loadSensorValue(deviceEditText.getText().toString(),
            sensorEditText.getText().toString());

    sensorModelCall.enqueue(new Callback<SensorModel>() {
      @Override
      public void onResponse(Call<SensorModel> call, Response<SensorModel> response) {
        sensorValue.setText(response.body().sensorValue);
      }

      @Override
      public void onFailure(Call<SensorModel> call, Throwable t) {
        sensorValue.setText(t.getMessage());
      }
    });

  }
}
