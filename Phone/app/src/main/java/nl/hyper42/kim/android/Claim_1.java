package nl.hyper42.kim.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kamere on 4/18/2018.
 */

public class Claim_1 extends AppCompatActivity {
    String[] claims = {"Are you older than 18 years old?",
                        "Are you an EU citizen?","Are you you flying outside EU?",
                        "Which level of SkyBlue are you?"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim1);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        int claim_id = getIntent().getIntExtra("claim_id", -1);
        TextView claimText = findViewById(R.id.claim_text_details);
        claimText.setText(claims[claim_id-1]);//Index from 0 in array


        //Find confirm button, when clicked, the activity ends and go back to prev. screen
        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
