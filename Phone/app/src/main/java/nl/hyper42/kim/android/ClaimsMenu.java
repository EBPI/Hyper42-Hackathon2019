package nl.hyper42.kim.android;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Rodrigo on 13/04/2018.
 */
public class ClaimsMenu extends AppCompatActivity implements View.OnClickListener {
    static final int RESULT_CODE = 0;
    int[][] permissions ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claims_menu);
        Toolbar toolbar = findViewById(R.id.toolbarComplaintsMenu);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle messageReturned = intent.getBundleExtra("permissions");
        permissions = new int[4][6];
        permissions[0]=messageReturned.getIntArray("1");
        permissions[1]=messageReturned.getIntArray("2");
        permissions[2]=messageReturned.getIntArray("3");
        permissions[3]=messageReturned.getIntArray("4");;
        findViewById(R.id.claim_1).setOnClickListener(this);
        findViewById(R.id.claim_2).setOnClickListener(this);
        findViewById(R.id.claim_3).setOnClickListener(this);
        findViewById(R.id.claim_4).setOnClickListener(this);
        Button btn = findViewById(R.id.buttonConfirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWithResult = new Intent();
                Bundle permissionsBundle = new Bundle();
                permissionsBundle.putIntArray("1",permissions[0]);
                permissionsBundle.putIntArray("2",permissions[1]);
                permissionsBundle.putIntArray("3",permissions[2]);
                permissionsBundle.putIntArray("4",permissions[3]);
                intentWithResult.putExtra("message_return", permissionsBundle);
                setResult(0,intentWithResult);
                finish();
            }
        });
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
        Intent myIntent = new Intent(this, ClaimIndDetails.class);
        myIntent.putExtra("permissions", permissions[id-1]);
        this.startActivityForResult(myIntent, RESULT_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int[] messageReturned = data.getIntArrayExtra("message_return");
//        System.out.println("Result code = " + resultCode);
//        System.out.println(messageReturned.toString());
//        System.out.println("Message returned = " + messageReturned.toString());
        int index = messageReturned[0];
        permissions[index]= messageReturned;
    }
}
