package nl.hyper42.kim.android;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Rodrigo on 13/04/2018.
 */
public class ClaimsMenu extends AppCompatActivity implements View.OnClickListener {
    static final int RESULT_CODE = 0;
    int[][] permissions ;
    String[] roles = {"Airlines","Security","Customs","Shops","Lounge"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claims_menu);
        Toolbar toolbar = findViewById(R.id.toolbarComplaintsMenu);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle messageReturned = intent.getBundleExtra("permissions");
        onCreateInitPermissions(messageReturned);
        findViewById(R.id.claim_1).setOnClickListener(this);
        findViewById(R.id.claim_2).setOnClickListener(this);
        findViewById(R.id.claim_3).setOnClickListener(this);
        findViewById(R.id.claim_4).setOnClickListener(this);
        Button btn = findViewById(R.id.buttonConfirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWithResult = new Intent();
                intentWithResult.putExtra("message_return", onCreateInitBundle());
                setResult(0,intentWithResult);
                finish();
            }
        });
    }

    private Bundle onCreateInitBundle() {
        Bundle permissionsBundle = new Bundle();
        permissionsBundle.putIntArray("1",permissions[0]);
        permissionsBundle.putIntArray("2",permissions[1]);
        permissionsBundle.putIntArray("3",permissions[2]);
        permissionsBundle.putIntArray("4",permissions[3]);
        return permissionsBundle;
    }

    private void onCreateInitPermissions(Bundle messageReturned) {
        permissions = new int[4][6];
        permissions[0]=messageReturned.getIntArray("1");
        permissions[1]=messageReturned.getIntArray("2");
        permissions[2]=messageReturned.getIntArray("3");
        permissions[3]=messageReturned.getIntArray("4");
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
        updateAccesibleTexts();
    }
    private void updateAccesibleTexts(){

        String[] accesible_texts = new String[permissions.length];
        for (int j = 0;j<permissions.length;j++){
            accesible_texts[j] = "None";
            for (int i = permissions[0].length-1; i>0; i--){
                 if (permissions[j][i] == 1)
                        if(accesible_texts[j]=="None")
                            accesible_texts[j] = " & "+ roles[i-1];
                        else
                         accesible_texts[j] = ", "+roles[i-1] +accesible_texts[j];
            }
            if (accesible_texts[j].startsWith(" &") || accesible_texts[j].startsWith(", "))
                accesible_texts[j]= accesible_texts[j].substring(2);

            }
        TextView text = findViewById(R.id.claim_1_access);
        text.setText("Accesible by: "+accesible_texts[0]);
        text = findViewById(R.id.claim_2_access);
        text.setText("Accesible by: "+accesible_texts[1]);
        text = findViewById(R.id.claim_3_access);
        text.setText("Accesible by: "+accesible_texts[2]);
        text = findViewById(R.id.claim_4_access);
        text.setText("Accesible by: "+accesible_texts[3]);
    }
}
