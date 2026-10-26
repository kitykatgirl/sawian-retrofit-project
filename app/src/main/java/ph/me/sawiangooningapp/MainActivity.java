
package ph.me.sawiangooningapp;

import android.os.Bundle;
import android.widget.ListView;
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

    private SawianAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        ListView listView = findViewById(R.id.listViewSawians);

        adapter = new SawianAdapter(this, new java.util.ArrayList<>());
        listView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/kitykatgirl/sawian-db/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi api =
                retrofit.create(JsonPlaceholderApi.class);

        Call<List<Sawian>> call = api._getSawian();

        call.enqueue(new Callback<List<Sawian>>() {
            @Override
            public void onResponse(
                    Call<List<Sawian>> call,
                    Response<List<Sawian>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(
                            MainActivity.this,
                            "HTTP error: " + response.code(),
                            Toast.LENGTH_LONG
                    ).show();
                    return;
                }

                List<Sawian> sawianList = response.body();

                if (sawianList != null) {
                    adapter.addAll(sawianList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(
                    Call<List<Sawian>> call,
                    Throwable t) {

                Toast.makeText(
                        MainActivity.this,
                        "Network error: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
