package com.giuliomachado.klimatos.api;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.giuliomachado.klimatos.MainActivity;
import com.giuliomachado.klimatos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HTTPServiceMain extends AsyncTask<Void, Void, String> {

    private String latitude;
    private String longitude;

    private TextView realtime;
    private TextView cidade;
    private TextView descricaoDia;
    private TextView umidade;
    private TextView ultimaAtualizacao;
    private TextView velocidadeVento;
    private TextView nascerDoSol;
    private TextView porDoSol;
    private TextView hojeMAX;
    private TextView hojeMIN;

    private ImageView fundoAlterar;

    private TextView diaMais_1;
    private TextView dataMais_1;
    private TextView maximaMais_1;
    private TextView minimaMais_1;
    private TextView descricaoMais_1;

    private TextView diaMais_2;
    private TextView dataMais_2;
    private TextView maximaMais_2;
    private TextView minimaMais_2;
    private TextView descricaoMais_2;

    private TextView diaMais_3;
    private TextView dataMais_3;
    private TextView maximaMais_3;
    private TextView minimaMais_3;
    private TextView descricaoMais_3;

    private TextView diaMais_4;
    private TextView dataMais_4;
    private TextView maximaMais_4;
    private TextView minimaMais_4;
    private TextView descricaoMais_4;

    private TextView diaMais_5;
    private TextView dataMais_5;
    private TextView maximaMais_5;
    private TextView minimaMais_5;
    private TextView descricaoMais_5;

    private String periodo;
    private String img;
    private MainActivity mainActivity;

    public HTTPServiceMain(String URL,
                           MainActivity backgroundBody,
                           ImageView imagem,

                           TextView tvTemp,
                           TextView tvCidade,
                           TextView tvDesc,
                           TextView tvUmidade,
                           TextView tvUpdate,
                           TextView tvVento,
                           TextView tvNascerDoSol,
                           TextView tvPorDoSol,
                           TextView tvHojeMAX,
                           TextView tvHojeMIN,
            /*----------------------------------*/
                           TextView tvDiaMais1,
                           TextView tvDateMais1,
                           TextView tvMaxMais1,
                           TextView tvMinMais1,
                           TextView tvDescMais1,
            /*----------------------------------*/
                           TextView tvDiaMais2,
                           TextView tvDateMais2,
                           TextView tvMaxMais2,
                           TextView tvMinMais2,
                           TextView tvDescMais2,
            /*----------------------------------*/
                           TextView tvDiaMais3,
                           TextView tvDateMais3,
                           TextView tvMaxMais3,
                           TextView tvMinMais3,
                           TextView tvDescMais3,
            /*----------------------------------*/
                           TextView tvDiaMais4,
                           TextView tvDateMais4,
                           TextView tvMaxMais4,
                           TextView tvMinMais4,
                           TextView tvDescMais4,
            /*----------------------------------*/
                           TextView tvDiaMais5,
                           TextView tvDateMais5,
                           TextView tvMaxMais5,
                           TextView tvMinMais5,
                           TextView tvDescMais5){

        realtime = tvTemp;
        cidade = tvCidade;
        descricaoDia = tvDesc;
        umidade = tvUmidade;
        ultimaAtualizacao = tvUpdate;
        velocidadeVento = tvVento;
        nascerDoSol = tvNascerDoSol;
        porDoSol = tvPorDoSol;
        hojeMAX = tvHojeMAX;
        hojeMIN = tvHojeMIN;

        mainActivity = backgroundBody;
        fundoAlterar = imagem;

        diaMais_1 = tvDiaMais1;
        dataMais_1 = tvDateMais1;
        maximaMais_1 = tvMaxMais1;
        minimaMais_1 = tvMinMais1;
        descricaoMais_1 = tvDescMais1;

        diaMais_2 = tvDiaMais2;
        dataMais_2 = tvDateMais2;
        maximaMais_2 = tvMaxMais2;
        minimaMais_2 = tvMinMais2;
        descricaoMais_2 = tvDescMais2;

        diaMais_3 = tvDiaMais3;
        dataMais_3 = tvDateMais3;
        maximaMais_3 = tvMaxMais3;
        minimaMais_3 = tvMinMais3;
        descricaoMais_3 = tvDescMais3;

        diaMais_4 = tvDiaMais4;
        dataMais_4 = tvDateMais4;
        maximaMais_4 = tvMaxMais4;
        minimaMais_4 = tvMinMais4;
        descricaoMais_4 = tvDescMais4;

        diaMais_5 = tvDiaMais5;
        dataMais_5 = tvDateMais5;
        maximaMais_5 = tvMaxMais5;
        minimaMais_5 = tvMinMais5;
        descricaoMais_5 = tvDescMais5;

        latitude = URL;
        longitude = URL;

    }


    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder respostaMain = new StringBuilder();
        try {
            URL urlLatitude = new URL(latitude + longitude);
            //URL urlLongitude = new URL(longitude);
            HttpURLConnection connection = (HttpURLConnection) urlLatitude.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(urlLatitude.openStream());
            while (scanner.hasNext()) {
                respostaMain.append(scanner.next() + " "); // PEGAR ESPAÇOS ENTRE PALAVRAS
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("rJSON: ", respostaMain.toString());
        return respostaMain.toString();
    }

    @Override
    protected void onPostExecute(String previsaotempoMain) {
        super.onPostExecute(String.valueOf(previsaotempoMain));

        try {
            JSONObject jsonObject = new JSONObject(previsaotempoMain);
            JSONObject result = jsonObject.getJSONObject("results");

            int temp = result.getInt("temp");
            realtime.setText(Integer.toString(temp) + "º");
            String city = result.getString("city");
            cidade.setText(city);
            String descricao = result.getString("description");
            descricaoDia.setText(descricao);
            int umi = result.getInt("humidity");
            umidade.setText("Umidade em " + Integer.toString(umi) + "%");
            String time = result.getString("time");
            ultimaAtualizacao.setText("Atualizado às " + time);
            String vento = result.getString("wind_speedy");
            velocidadeVento.setText(vento);
            String nascerSol = result.getString("sunrise");
            nascerDoSol.setText(nascerSol);
            String poenteSol = result.getString("sunset");
            porDoSol.setText(poenteSol);

            periodo = result.getString("currently");
            img = result.getString("img_id");

            verficarIcone( periodo, img, mainActivity);

            JSONArray arrayForecast = result.getJSONArray("forecast");

            JSONObject listaItens0 = (JSONObject) arrayForecast.get(0);

            hojeMAX.setText(listaItens0.getString("max") + "º");
            hojeMIN.setText(listaItens0.getString("min") + "º");

            // FORECAST - posição 1 (DIA+1)
            JSONObject listaItens1 = (JSONObject) arrayForecast.get(1);

            String weekday1 = listaItens1.getString("weekday");
            diaMais_1.setText(weekday1);
            String date1 = listaItens1.getString("date");
            dataMais_1.setText(date1);
            int max1 = listaItens1.getInt("max");
            maximaMais_1.setText(Integer.toString(max1) + "º");
            int min1 = listaItens1.getInt("min");
            minimaMais_1.setText(Integer.toString(min1) + "º");
            String description1 = listaItens1.getString("description");
            descricaoMais_1.setText(description1);

            // FORECAST - posição 2 (DIA+2)
            JSONObject listaItens2 = (JSONObject) arrayForecast.get(2);

            String weekday2 = listaItens2.getString("weekday");
            diaMais_2.setText(weekday2);
            String date2 = listaItens2.getString("date");
            dataMais_2.setText(date2);
            int max2 = listaItens2.getInt("max");
            maximaMais_2.setText(Integer.toString(max2) + "º");
            int min2 = listaItens2.getInt("min");
            minimaMais_2.setText(Integer.toString(min2) + "º");
            String description2 = listaItens2.getString("description");
            descricaoMais_2.setText(description2);

            // FORECAST - posição 3 (DIA+3)
            JSONObject listaItens3 = (JSONObject) arrayForecast.get(3);

            String weekday3 = listaItens3.getString("weekday");
            diaMais_3.setText(weekday3);
            String date3 = listaItens3.getString("date");
            dataMais_3.setText(date3);
            int max3 = listaItens3.getInt("max");
            maximaMais_3.setText(Integer.toString(max3) + "º");
            int min3 = listaItens3.getInt("min");
            minimaMais_3.setText(Integer.toString(min3) + "º");
            String description3 = listaItens3.getString("description");
            descricaoMais_3.setText(description3);

            // FORECAST - posição 4 (DIA+4)
            JSONObject listaItens4 = (JSONObject) arrayForecast.get(4);

            String weekday4 = listaItens4.getString("weekday");
            diaMais_4.setText(weekday4);
            String date4 = listaItens4.getString("date");
            dataMais_4.setText(date4);
            int max4 = listaItens4.getInt("max");
            maximaMais_4.setText(Integer.toString(max4) + "º");
            int min4 = listaItens4.getInt("min");
            minimaMais_4.setText(Integer.toString(min4) + "º");
            String description4 = listaItens4.getString("description");
            descricaoMais_4.setText(description4);

            // FORECAST - posição 5 (DIA+5)
            JSONObject listaItens5 = (JSONObject) arrayForecast.get(5);

            String weekday5 = listaItens5.getString("weekday");
            diaMais_5.setText(weekday5);
            String date5 = listaItens5.getString("date");
            dataMais_5.setText(date5);
            int max5 = listaItens5.getInt("max");
            maximaMais_5.setText(Integer.toString(max5) + "º");
            int min5 = listaItens5.getInt("min");
            minimaMais_5.setText(Integer.toString(min5) + "º");
            String description5 = listaItens5.getString("description");
            descricaoMais_5.setText(description5);

        } catch (JSONException e) {
        }
    }

    public void verficarIcone(String periodo, String img, MainActivity mainActivity){

        Resources res = mainActivity.getApplicationContext().getResources();
        String caminho;

        if ( periodo.equals("dia") ){  //DIA

            caminho = "t" + img + "";
            String pacote = mainActivity.getApplicationContext().getPackageName();
            int i = res.getIdentifier( caminho, "drawable", pacote );

            ListView backGround = mainActivity.findViewById(R.id.bodyMain);
            backGround.setBackgroundResource(R.color.colorAccent);

            ImageView imagem = mainActivity.findViewById(R.id.imageSlugMain);
            imagem.setImageResource(i);

        } else {  //NOITE

            caminho = "t" + img;
            String pacote = mainActivity.getPackageName();
            int i = res.getIdentifier( caminho, "drawable", pacote );

            ListView backGround = mainActivity.findViewById(R.id.bodyMain);
            backGround.setBackgroundResource(R.color.fundoNoite);

            ImageView imagem = mainActivity.findViewById(R.id.imageSlugMain);
            imagem.setImageResource(i);

        }
    }
}
