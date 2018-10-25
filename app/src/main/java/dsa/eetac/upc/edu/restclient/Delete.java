package dsa.eetac.upc.edu.restclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

    public class Delete extends AppCompatActivity {

        public static final String EXTRA_MESSAGE = "dsa.eetac.upc.edu.RestClient.MESSAGE";
        private Button deletebtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_delete);

        }

        public void sendID(View view){
            Intent intent = new Intent(this, MainActivity.class);
            deletebtn = (Button) findViewById(R.id.deletebtn);
            TextView insertid = (TextView) findViewById(R.id.editText);
            String id = insertid.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, id);
            startActivity(intent);
        }
}
