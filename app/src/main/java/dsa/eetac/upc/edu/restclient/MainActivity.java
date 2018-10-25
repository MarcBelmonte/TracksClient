package dsa.eetac.upc.edu.restclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Track> titles = new ArrayList<>();

    private TrackAPI myapirest;
    private final Track t = new Track();
    private Button goButton;

    private Spinner functionsSpinner;
    private int selected;
    private String token;
    private Button authenticateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        functionsSpinner = (Spinner) findViewById(R.id.questions_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.implementations, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        functionsSpinner.setAdapter(arrayAdapter);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        createTrackApi();

        functionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                if(text.equals("Show all Tracks")){
                    myapirest.getAllTracks().enqueue(tracksCallback);
                }
                else if (text.equals("Delete a Track")){
                    deleteTrack(view);
                }
                else if(text.equals("Create a new Track")){
                    createTrack(view);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       }

    public void deleteTrack(View view){
        Intent intent2 = new Intent(this, Delete.class);
        startActivity(intent2);

        Intent intent = getIntent();

        int deleteid = Integer.parseInt(intent.getStringExtra(Delete.EXTRA_MESSAGE));

        t.id = deleteid;

        myapirest.deleteTrack(t.id);
    }

    public void createTrack(View view){
        Intent intent3 = new Intent(this, Create.class);
        startActivity(intent3);

        Intent intent = getIntent();

        int id = Integer.parseInt(intent.getStringExtra(Create.EXTRA_MESSAGEID));
        String title = intent.getStringExtra(Create.EXTRA_MESSAGETITLE);
        String singer = intent.getStringExtra(Create.EXTRA_MESSAGESINGER);

        t.id = id;
        t.title = title;
        t.singer = singer;

        myapirest.addTrack(t).enqueue(tracksCallback);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (token != null) {
            authenticateButton.setEnabled(false);
        }
    }
    private void createTrackApi() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TrackAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myapirest = retrofit.create(TrackAPI.class);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data.getStringExtra("token");
        }
    }
    Callback<List<Track>> tracksCallback = new Callback<List<Track>>() {
        @Override
        public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
            if (response.isSuccessful()) {
                List<Track> datatrack = response.body();
                datatrack.addAll(response.body());
                recyclerView.setAdapter(new RecyclerViewAdapter(datatrack));
                //ArrayAdapter<Track> arrayAdapter = new ArrayAdapter<Track>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item);
                //questionsSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<List<Track>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    /*private void getTracks() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TrackAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myapi = retrofit.create(TrackAPI.class);
        Call< List<Track> > call = myapi.getTracks();

        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                for(Track track : response.body()) {
                    titles.add(track.title);
                }
                ArrayAdapter arrayAdapter = null;
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
            }
        });
    }*/

}