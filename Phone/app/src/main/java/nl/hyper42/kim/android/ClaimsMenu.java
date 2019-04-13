package nl.hyper42.kim.android;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Rodrigo on 13/04/2018.
 */
public class ClaimsMenu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claims_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.claim_1).setOnClickListener(this);;
        findViewById(R.id.claim_2).setOnClickListener(this);;
        findViewById(R.id.claim_3).setOnClickListener(this);;
        findViewById(R.id.claim_4).setOnClickListener(this);;

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.claim_1:
                onButtonClick(1);
                break;
            case R.id.claim_2:
                onButtonClick(2);
                break;
            case R.id.claim_3:
                onButtonClick(3);
                break;
            case R.id.claim_4:
                onButtonClick(4);
                break;
        }
    }
    private void onButtonClick(int id) {
        Intent myIntent = new Intent(this, Claim_1.class);
        myIntent.putExtra("claim_id", id);
        this.startActivity(myIntent);
    }
}
