package nl.hyper42.kim.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rodrigo on 13/04/2018.
 */

public class Tab3 extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.claims_menu, container, false);
        View claim = view.findViewById(R.id.claim_1);
        claim.setOnClickListener(this);
        claim = view.findViewById(R.id.claim_2);
        claim.setOnClickListener(this);
        claim = view.findViewById(R.id.claim_3);
        claim.setOnClickListener(this);
        claim = view.findViewById(R.id.claim_4);
        claim.setOnClickListener(this);

        return view;
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
        Intent myIntent = new Intent(getActivity(), Claim_1.class);
        myIntent.putExtra("claim_id", id);
        Tab3.this.startActivity(myIntent);
    }
}