package nl.hyper42.kim.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.hyper42.kim.android.generated.travel.Authorisation;
import nl.hyper42.kim.android.generated.travel.TravelDataRequest;
import nl.hyper42.kim.android.generated.travel.TravelDataResponse;
import nl.hyper42.kim.android.travel.TravelClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The Onboard tab
 */

public class Tab1 extends Fragment implements View.OnClickListener {

    private String TAG = "Tab1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        View button = view.findViewById(R.id.buttonScanPassport);
        button.setOnClickListener(this);
        button = view.findViewById(R.id.buttonLoadTicket);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonScanPassport:
                onButtonOneClick(view);
                break;
            case R.id.buttonLoadTicket:
                onButtonTwoClick();
                break;
        }
    }

    private void onButtonTwoClick() {

    }

    public void createClaims (List<String> olderEightteenRoles,  List<String> euCitizenroles, List<String> outsideEURoles, List<String> flyingBlueLevelRoles) {
        String passportData = "{\"name\": \"MyName\", \"DateOfBirth\": \"1998-04-12\", \"Nationality\": \"Netherlands\", \"ExpirationDate\": \"2024-04-23\", \"Photo\": \"base64encodedPhoto\"}";
        String travelData = "{\"FlightNumber\": \"KL123\", \"Date\": \"2019-06-12\", \"Departure\": \"AMS\", \"DepartureCountry\": \"Netherlands\", \"Destination\": \"BRU\", \"DestinationCountry\": \"Belgium\", \"FlightBlue\": \"Silver\"}";

        List<Authorisation> authorisations = new ArrayList<>();

 //       List olderEightteenRoles = new ArrayList <>();
        Authorisation olderEightteen = new Authorisation();
        olderEightteen.setClaimName("OlderEightteen");
        olderEightteen.setRole(olderEightteenRoles);

 //       List euCitizenroles = new ArrayList <>();
        Authorisation euCitizen = new Authorisation();
        euCitizen.setClaimName("EUCitizen");
        euCitizen.setRole(euCitizenroles);

//        List outsideEURoles = new ArrayList <>();
        Authorisation travelOutsideEU = new Authorisation();
        travelOutsideEU.setClaimName("TravelOutsideEU");
        travelOutsideEU.setRole(outsideEURoles);

 //       List flyingBlueLevelRoles = new ArrayList <>();
        Authorisation flyingBlueLevel = new Authorisation();
        flyingBlueLevel.setClaimName("FlyingBlueLevel");
        flyingBlueLevel.setRole(flyingBlueLevelRoles);

        authorisations.add(olderEightteen);
        authorisations.add(euCitizen);
        authorisations.add(travelOutsideEU);
        authorisations.add(flyingBlueLevel);

        TravelDataRequest request = new TravelDataRequest();
        request.setPassportData(passportData);
        request.setAuthorisation(authorisations);
        request.setTravelData(travelData);

        Call<TravelDataResponse> travelInfoCall = TravelClient.getTravelService().registerFlight(request);


       travelInfoCall.enqueue(new Callback<TravelDataResponse>() {
            @Override
            public void onResponse(Call<TravelDataResponse> call, Response<TravelDataResponse> response) {
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TravelDataResponse> call, Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
        Toast.makeText(getContext(), "you choose button 2", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(getActivity(), LoadTicket.class);
//        myIntent.putExtra("key", value); //Optional parameters
        Tab1.this.startActivity(myIntent);
    }

    private void onButtonOneClick(View view) {
        Intent myIntent = new Intent(getActivity(), Passport.class);
//        myIntent.putExtra("key", value); //Optional parameters
        Tab1.this.startActivity(myIntent);
    }
}
