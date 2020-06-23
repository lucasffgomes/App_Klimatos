package com.giuliomachado.klimatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.giuliomachado.klimatos.api.HTTPServiceMain;

public class MainActivity extends AppCompatActivity {

    Button irAteCidadeFavorita;
    LocationManager locationManager;
    boolean gpsEnabled = false;
    Double tvLatitude;
    Double tvLongitude;
    ImageView img;

    String chaveAPImain = "850fa19c";

    TextView tvTemp, tvCidade, tvDescri, tvUmidade, tvUpdate, tvVento, tvNascerDoSol, tvPorDoSol, tvHojeMAX, tvHojeMIN;

    TextView tvDiaMais1, tvDateMais1, tvMaxMais1, tvMinMais1, tvDescMais1;
    TextView tvDiaMais2, tvDateMais2, tvMaxMais2, tvMinMais2, tvDescMais2;
    TextView tvDiaMais3, tvDateMais3, tvMaxMais3, tvMinMais3, tvDescMais3;
    TextView tvDiaMais4, tvDateMais4, tvMaxMais4, tvMinMais4, tvDescMais4;
    TextView tvDiaMais5, tvDateMais5, tvMaxMais5, tvMinMais5, tvDescMais5;

    @Override
    protected void onStart() {
        super.onStart();

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            Toast.makeText(this, "GPS desligado", Toast.LENGTH_LONG).show();
        }
    }

    private void enableLocationSettings() {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        irAteCidadeFavorita = findViewById(R.id.ID_button_cidades_favoritas);
        irAteCidadeFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityFavorite.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().hide();

        tvTemp = findViewById(R.id.textView_temperatura_main);
        tvCidade = findViewById(R.id.textView_cidade_main);
        tvDescri = findViewById(R.id.textView12);
        tvUmidade = findViewById(R.id.textView_umidade_main);
        tvUpdate = findViewById(R.id.textView_ultimaAtualizacao_main);
        tvVento = findViewById(R.id.textView_velocidadeVento_main);
        tvNascerDoSol = findViewById(R.id.textView_sunrise_main);
        tvPorDoSol = findViewById(R.id.textView_sunset_main);
        tvHojeMAX = findViewById(R.id.textView_maxHoje_favorite);
        tvHojeMIN = findViewById(R.id.textView_minHoje_main);
        img = findViewById(R.id.imageSlugMain);

        tvDiaMais1 = findViewById(R.id.textView_diaMAIS1_main);
        tvDateMais1 = findViewById(R.id.textView_dataMAIS1_main);
        tvMaxMais1 = findViewById(R.id.textView_maxMAIS1_main);
        tvMinMais1 = findViewById(R.id.textView_minMAIS1_main);
        tvDescMais1 = findViewById(R.id.textView_descricaoMAIS1_main);

        tvDiaMais2 = findViewById(R.id.textView_diaMAIS2_main);
        tvDateMais2 = findViewById(R.id.textView_dataMAIS2_main);
        tvMaxMais2 = findViewById(R.id.textView_maxMAIS2_main);
        tvMinMais2 = findViewById(R.id.textView_minMAIS2_main);
        tvDescMais2 = findViewById(R.id.textView_descricaoMAIS2_main);

        tvDiaMais3 = findViewById(R.id.textView_diaMAIS3_main);
        tvDateMais3 = findViewById(R.id.textView_dataMAIS3_main);
        tvMaxMais3 = findViewById(R.id.textView_maxMAIS3_main);
        tvMinMais3 = findViewById(R.id.textView_minMAIS3_main);
        tvDescMais3 = findViewById(R.id.textView_descricaoMAIS3_main);

        tvDiaMais4 = findViewById(R.id.textView_diaMAIS4_main);
        tvDateMais4 = findViewById(R.id.textView_dataMAIS4_main);
        tvMaxMais4 = findViewById(R.id.textView_maxMAIS4_main);
        tvMinMais4 = findViewById(R.id.textView_minMAIS4_main);
        tvDescMais4 = findViewById(R.id.textView_descricaoMAIS4_main);

        tvDiaMais5 = findViewById(R.id.textView_diaMAIS5_main);
        tvDateMais5 = findViewById(R.id.textView_dataMAIS5_main);
        tvMaxMais5 = findViewById(R.id.textView_maxMAIS5_main);
        tvMinMais5 = findViewById(R.id.textView_minMAIS5_main);
        tvDescMais5 = findViewById(R.id.textView_descricaoMAIS5_main);

        //Log.d("Retorno de JSON: ", resposta.toString());

        HTTPServiceMain service = new HTTPServiceMain("https://api.hgbrasil.com/weather?key=" + chaveAPImain + "&lat=" + tvLatitude + "&log=" + tvLongitude + "&user_ip=remote",
                MainActivity.this,
                img, tvTemp, tvCidade, tvDescri, tvUmidade, tvUpdate, tvVento, tvNascerDoSol, tvPorDoSol, tvHojeMAX, tvHojeMIN,
                /*-----------------------------------------------------------------------*/
                tvDiaMais1, tvDateMais1, tvMaxMais1, tvMinMais1, tvDescMais1,
                /*-----------------------------------------------------------------------*/
                tvDiaMais2, tvDateMais2, tvMaxMais2, tvMinMais2, tvDescMais2,
                /*-----------------------------------------------------------------------*/
                tvDiaMais3, tvDateMais3, tvMaxMais3, tvMinMais3, tvDescMais3,
                /*-----------------------------------------------------------------------*/
                tvDiaMais4, tvDateMais4, tvMaxMais4, tvMinMais4, tvDescMais4,
                /*-----------------------------------------------------------------------*/
                tvDiaMais5, tvDateMais5, tvMaxMais5, tvMinMais5, tvDescMais5);

        service.execute();

        //--------------------------------------------------------------------------

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(false);

        Toast.makeText(this, "Localizando", Toast.LENGTH_LONG).show();
        ListenerPosition();
        //Log.d("late", tvLatitude.toString());
    }

    public void ListenerPosition() {
        final LocationListener listener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(MainActivity.this, "LatLong" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_LONG).show();
                exibirLocalizacao(location.getLatitude(), location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) { }
            @Override
            public void onProviderEnabled(String provider) { }
            @Override
            public void onProviderDisabled(String provider) { }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                40000,          // 40-second interval.
                10,             // 10 meters.
                listener);
    }

    public void exibirLocalizacao(double latitude, double longitude) {
        //Toast.makeText(MainActivity.this, latitude + " " + longitude, Toast.LENGTH_LONG).show();

        //tvLatitude.setText(String.valueOf(latitude));
        //tvLongitude.setText(String.valueOf(longitude));
    }
}