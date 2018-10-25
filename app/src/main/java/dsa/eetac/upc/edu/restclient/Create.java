package dsa.eetac.upc.edu.restclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Create extends AppCompatActivity {

    public static final String EXTRA_MESSAGEID = "dsa.eetac.upc.edu.MESSAGE";
    public static final String EXTRA_MESSAGETITLE = "dsa.eetac.upc.edu.MESSAGE";
    public static final String EXTRA_MESSAGESINGER = "dsa.eetac.upc.edu.MESSAGE";

    private Button createbtn;
    private TextView id;
    private TextView title;
    private TextView singer;

    private TrackAPI myapirest;

    private Track t = new Track();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

    }
    public void sendTrack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        createbtn = (Button) findViewById(R.id.createbtn);
        id = (TextView) findViewById(R.id.insertId);
        title = (TextView) findViewById(R.id.insertTitle);
        singer = (TextView) findViewById(R.id.insertSinger);

        int newid = Integer.parseInt(id.getText().toString());
        String newtitle = title.getText().toString();
        String newsinger = singer.getText().toString();

        intent.putExtra(EXTRA_MESSAGEID, newid);
        intent.putExtra(EXTRA_MESSAGETITLE, newtitle);
        intent.putExtra(EXTRA_MESSAGESINGER, newsinger);

        t.id = newid;
        t.title = newtitle;
        t.singer = newsinger;

        startActivity(intent);


    }
}
