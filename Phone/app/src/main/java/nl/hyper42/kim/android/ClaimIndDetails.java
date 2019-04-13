package nl.hyper42.kim.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rodrigo on 13/04/2018.
 */

public class ClaimIndDetails extends AppCompatActivity {
    String[] claims = {"Are you older than 18 years old?",
                        "Are you an EU citizen?","Are you you flying outside EU?",
                        "Which level of SkyBlue are you?"};
    int[] permissions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_ind_details);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        permissions = getIntent().getIntArrayExtra("permissions");
        TextView claimText = findViewById(R.id.claim_text_details);
        claimText.setText(claims[permissions[0]]);//Index from 0 in array and claim ids start at 1 (sorry used to python now)
        onCreateInitCheckbox();

        //Find confirm button, when clicked, the activity ends and go back to prev. screen
        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWithResult = new Intent();
                int[] checkboxes = processCheckboxes();
                intentWithResult.putExtra("message_return", checkboxes);
                setResult(0,intentWithResult);
                finish();
            }
        });

    }

    private int[] processCheckboxes() {
        int[] checkboxes = permissions;
        CheckBox checkBox = findViewById(R.id.role_1_checkbox);
        checkboxes[1]=  (checkBox.isChecked()) ? 1 : 0;
        checkBox = findViewById(R.id.role_2_checkbox);
        checkboxes[2]=checkBox.isChecked()? 1 : 0;
        checkBox = findViewById(R.id.role_3_checkbox);
        checkboxes[3]=checkBox.isChecked()? 1 : 0;
        checkBox = findViewById(R.id.role_4_checkbox);
        checkboxes[4]=checkBox.isChecked()? 1 : 0;
        checkBox = findViewById(R.id.role_5_checkbox);
        checkboxes[5]=checkBox.isChecked()? 1 : 0;
        return checkboxes;
    }

    private void onCreateInitCheckbox() {
        CheckBox checkBox = findViewById(R.id.role_1_checkbox);
        checkBox.setChecked(permissions[1]==1);
        checkBox = findViewById(R.id.role_2_checkbox);
        checkBox.setChecked(permissions[2]==1);
        checkBox = findViewById(R.id.role_3_checkbox);
        checkBox.setChecked(permissions[3]==1);
        checkBox = findViewById(R.id.role_4_checkbox);
        checkBox.setChecked(permissions[4]==1);
        checkBox = findViewById(R.id.role_5_checkbox);
        checkBox.setChecked(permissions[5]==1);
    }

}
