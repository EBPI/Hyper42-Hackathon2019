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
        setListeners();

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
    private void setListeners(){
        findViewById(R.id.claim_1).setOnClickListener(this);
        findViewById(R.id.claim_2).setOnClickListener(this);
        findViewById(R.id.claim_3).setOnClickListener(this);
        findViewById(R.id.claim_4).setOnClickListener(this);
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
        if (data==null)return;//When back button is pressed from android system
        int[] messageReturned = data.getIntArrayExtra("message_return");
        int index = messageReturned[0];
        permissions[index]= messageReturned;
        updateAccessibleTexts();
    }
    private void updateAccessibleTexts(){
        String[] accessible_texts = new String[permissions.length];
        for (int j = 0;j<permissions.length;j++){
            accessible_texts[j] = "None"; //By default no one is added
            for (int i = permissions[0].length-1; i>0; i--){
                 if (permissions[j][i] == 0) continue; //If 0 dont add to
                 if (accessible_texts[j] == "None") accessible_texts[j] = " & " + roles[i - 1]; //If empty string and role authorized then add to string.
                 else accessible_texts[j] = ", " + roles[i - 1] + accessible_texts[j]; //Concatenate the rest of allowed roles
            }
            if (accessible_texts[j].startsWith(" &") || accessible_texts[j].startsWith(", "))//Clean beginning of string
                accessible_texts[j]= accessible_texts[j].substring(2);
            }
            updateTextView(accessible_texts);
    }
    private void updateTextView(String[] accessible_texts){
        TextView text = findViewById(R.id.claim_1_access);
        text.setText("Accessible by: "+accessible_texts[0]);
        text = findViewById(R.id.claim_2_access);
        text.setText("Accessible by: "+accessible_texts[1]);
        text = findViewById(R.id.claim_3_access);
        text.setText("Accessible by: "+accessible_texts[2]);
        text = findViewById(R.id.claim_4_access);
        text.setText("Accessible by: "+accessible_texts[3]);
    }
}
