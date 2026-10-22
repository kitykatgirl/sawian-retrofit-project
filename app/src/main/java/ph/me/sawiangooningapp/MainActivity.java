package ph.me.sawiangooningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView name, desc, aim, move, reload, health, weapon, gameplay;
    Button btnPrev, btnNext;
    List<Sawian> sawiansList;
    int currentSaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.textViewName);
        desc = findViewById(R.id.textViewDesc);
        aim = findViewById(R.id.textViewAim);
        move = findViewById(R.id.textViewMove);
        reload = findViewById(R.id.textViewReload);
        health = findViewById(R.id.textViewHealth);
        weapon = findViewById(R.id.textViewWeapon);
        gameplay = findViewById(R.id.textViewGameplay);

        btnPrev = findViewById(R.id.buttonPrev);
        btnNext = findViewById(R.id.buttonNext);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/kitykatgirl/sawian-db/").addConverterFactory(GsonConverterFactory.create()).build();

        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
        Call<List<Sawian>> call = jsonPlaceholderApi._getSawian();

        call.enqueue(new Callback<List<Sawian>>() {
            @Override
            public void onResponse(Call<List<Sawian>> call, Response<List<Sawian>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(
                            MainActivity.this,
                            "HTTP error: " + response.code(),
                            Toast.LENGTH_LONG
                    ).show();
                    return;
                }
                sawiansList = response.body();
                setCurrentSawian(0);
            }

            @Override
            public void onFailure(Call<List<Sawian>> call, Throwable t) {
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sawiansList == null || sawiansList.isEmpty()) {
                    return;
                }

                if (currentSaw == 0) {
                    setCurrentSawian(sawiansList.size() - 1);
                } else {
                    setCurrentSawian(currentSaw - 1);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sawiansList == null || sawiansList.isEmpty()) {
                    return;
                }

                if (currentSaw == sawiansList.size() - 1) {
                    setCurrentSawian(0);
                } else {
                    setCurrentSawian(currentSaw + 1);
                }
            }
        });
    }

    private void setCurrentSawian(int index){
        Sawian chosenSaw = sawiansList.get(index);

        name.setText(chosenSaw.getName());
        desc.setText(chosenSaw.getDescription());
        aim.setText(chosenSaw.getAim());
        move.setText(chosenSaw.getMove());
        reload.setText(chosenSaw.getReload());
        health.setText(chosenSaw.getHealth());
        weapon.setText(chosenSaw.getWeapon());
        gameplay.setText(chosenSaw.getGameplay());
        currentSaw = index;
    }
}