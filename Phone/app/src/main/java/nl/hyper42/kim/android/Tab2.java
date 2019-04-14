package nl.hyper42.kim.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Kamere on 4/18/2018.
 */

public class Tab2 extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        View button = view.findViewById(R.id.buttonShareClaims);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "Your claims have been shared", Toast.LENGTH_LONG).show();
        view.findViewById(R.id.buttonShareClaims);
//        view.setEnabled(false);
//        view.setBackground(getResources().getDrawable(R.drawable.button_disabled));

    }
}
