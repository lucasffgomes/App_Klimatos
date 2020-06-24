package com.giuliomachado.klimatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.giuliomachado.klimatos.api.HTTPServiceFavorite;

public class ActivityFavorite extends AppCompatActivity {

    Button escreverCidade;
    Button BOTAOretornarParaHome;

    String chaveAPI = "7687cfb6";

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        BOTAOretornarParaHome = findViewById(R.id.button_voltar);
        BOTAOretornarParaHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFavorite.this, MainActivity.class );
                startActivity(intent);
            }
        });

        getSupportActionBar().hide();

        final EditText etPrevisao = findViewById(R.id.editText_adicionarCidade);
        etPrevisao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    esconderTeclado(v);
                }
            }
        });
        final TextView tvResposta = findViewById(R.id.textView_temperatura_favorite);
        final TextView tvCidade = findViewById(R.id.textView_cidade_favorite);
        final TextView tvDescri = findViewById(R.id.textView13);
        final TextView tvUmidade = findViewById(R.id.textView_umidade_favorite);
        final TextView tvUpdate = findViewById(R.id.textView_ultimaAtualizacao_favorite);
        final TextView tvVento = findViewById(R.id.textView_velocidadeVento_favorite);
        final TextView tvNascerDoSol = findViewById(R.id.textView_sunrise_favorite);
        final TextView tvPorDoSol = findViewById(R.id.textView_sunset_favorite);
        final TextView tvHojeMAX = findViewById(R.id.textView_maxHoje_favorite);
        final TextView tvHojeMIN = findViewById(R.id.textView_minHoje_favorite);

        final TextView tvDiaMais1 = findViewById(R.id.textView_diaMAIS1_favorite);
        final TextView tvDateMais1 = findViewById(R.id.textView_dataMAIS1_favorite);
        final TextView tvMaxMais1 = findViewById(R.id.textView_maxMAIS1_favorite);
        final TextView tvMinMais1 = findViewById(R.id.textView_minMAIS1_favorite);
        final TextView tvDescMais1 = findViewById(R.id.textView_descricaoMAIS1_favorite);

        final TextView tvDiaMais2 = findViewById(R.id.textView_diaMAIS2_favorite);
        final TextView tvDateMais2 = findViewById(R.id.textView_dataMAIS2_favorite);
        final TextView tvMaxMais2 = findViewById(R.id.textView_maxMAIS2_favorite);
        final TextView tvMinMais2 = findViewById(R.id.textView_minMAIS2_favorite);
        final TextView tvDescMais2 = findViewById(R.id.textView_descricaoMAIS2_favorite);

        final TextView tvDiaMais3 = findViewById(R.id.textView_diaMAIS3_favorite);
        final TextView tvDateMais3 = findViewById(R.id.textView_dataMAIS3_favorite);
        final TextView tvMaxMais3 = findViewById(R.id.textView_maxMAIS3_favorite);
        final TextView tvMinMais3 = findViewById(R.id.textView_minMAIS3_favorite);
        final TextView tvDescMais3 = findViewById(R.id.textView_descricaoMAIS3_favorite);

        final TextView tvDiaMais4 = findViewById(R.id.textView_diaMAIS4_favorite);
        final TextView tvDateMais4 = findViewById(R.id.textView_dataMAIS4_favorite);
        final TextView tvMaxMais4 = findViewById(R.id.textView_maxMAIS4_favorite);
        final TextView tvMinMais4 = findViewById(R.id.textView_minMAIS4_favorite);
        final TextView tvDescMais4 = findViewById(R.id.textView_descricaoMAIS4_favorite);

        final TextView tvDiaMais5 = findViewById(R.id.textView_diaMAIS5_favorite);
        final TextView tvDateMais5 = findViewById(R.id.textView_dataMAIS5_favorite);
        final TextView tvMaxMais5 = findViewById(R.id.textView_maxMAIS5_favorite);
        final TextView tvMinMais5 = findViewById(R.id.textView_minMAIS5_favorite);
        final TextView tvDescMais5 = findViewById(R.id.textView_descricaoMAIS5_favorite);
        img = findViewById(R.id.imageView5);

        escreverCidade = findViewById(R.id.button_adicionar);
        escreverCidade.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                HTTPServiceFavorite service = new HTTPServiceFavorite("https://api.hgbrasil.com/weather?key=" + chaveAPI + "&city_name=" + etPrevisao.getText(),
                        ActivityFavorite.this,
                        img,
                        tvResposta, tvCidade, tvDescri, tvUmidade, tvUpdate, tvVento, tvNascerDoSol, tvPorDoSol, tvHojeMAX, tvHojeMIN,
                        /*-------------------------------------------------------------------*/
                        tvDiaMais1, tvDateMais1, tvMaxMais1, tvMinMais1, tvDescMais1,
                        /*-------------------------------------------------------------------*/
                        tvDiaMais2, tvDateMais2, tvMaxMais2, tvMinMais2, tvDescMais2,
                        /*-------------------------------------------------------------------*/
                        tvDiaMais3, tvDateMais3, tvMaxMais3, tvMinMais3, tvDescMais3,
                        /*-------------------------------------------------------------------*/
                        tvDiaMais4, tvDateMais4, tvMaxMais4, tvMinMais4, tvDescMais4,
                        /*-------------------------------------------------------------------*/
                        tvDiaMais5, tvDateMais5, tvMaxMais5, tvMinMais5, tvDescMais5);

                service.execute();
                apagarCampos();
                esconderTeclado(v);
            }

            public void apagarCampos(){
                etPrevisao.getText().clear();
            }
        });
    }

    public void esconderTeclado(View v) {
        if(v!=null)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
