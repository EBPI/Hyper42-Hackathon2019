package nl.hyper42.kim.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
    private int[][] permissions = new int[4][6];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        View button = view.findViewById(R.id.buttonScanPassport);
        button.setOnClickListener(this);
        button = view.findViewById(R.id.buttonLoadTicket);
        button.setOnClickListener(this);
        initPermissions();
        view.findViewById(R.id.buttonApproveClaims).setOnClickListener(this);

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
            case R.id.buttonApproveClaims:
                onButtonThreeClick(view);
                break;
        }
    }

    private void onButtonTwoClick() {
        Intent myIntent = new Intent(getActivity(), LoadTicket.class);
        Tab1.this.startActivity(myIntent);
    }

    public void createClaims(List<String> olderEightteenRoles, List<String> euCitizenroles, List<String> outsideEURoles, List<String> flyingBlueLevelRoles) {
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
                File file = new File(getContext().getFilesDir(), "travelDataResponse.json");
                Gson gson = new Gson();
                String json = gson.toJson(response.body());
                FileOutputStream stream = null;
                try {
                    stream = new FileOutputStream(file);
                    stream.write(json.getBytes());
                    Toast.makeText(getContext(), "Claims submitted", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TravelDataResponse> call, Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void onButtonOneClick(View view) {
        Intent myIntent = new Intent(getActivity(), Passport.class);
        Tab1.this.startActivity(myIntent);
    }

    private void onButtonThreeClick(View view) {
        Intent myIntent = new Intent(getActivity(), ClaimsMenu.class);
//        myIntent.putExtra("key", value); //Optional parameters
        Bundle permissionsBundle = new Bundle();
        permissionsBundle.putIntArray("1", permissions[0]);
        permissionsBundle.putIntArray("2", permissions[1]);
        permissionsBundle.putIntArray("3", permissions[2]);
        permissionsBundle.putIntArray("4", permissions[3]);
        myIntent.putExtra("permissions", permissionsBundle);
        Tab1.this.startActivityForResult(myIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle messageReturned = data.getBundleExtra("message_return");
        permissions = new int[4][6];
        permissions[0] = messageReturned.getIntArray("1");
        permissions[1] = messageReturned.getIntArray("2");
        permissions[2] = messageReturned.getIntArray("3");
        permissions[3] = messageReturned.getIntArray("4");
        ;
        processAndCreateClaims();
    }

    private void processAndCreateClaims() {
        String[] roles = {"Airlines", "Security", "Customs", "Shops", "Lounge"};
        ArrayList<ArrayList<String>> converted_permissions = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            ArrayList<String> claim = new ArrayList<>();
            for (int j = 1; j < permissions[0].length; j++) {
                if (permissions[i][j] == 1) {
                    claim.add(roles[j - 1]);
                }
            }
            converted_permissions.add(claim);
        }
        for (int i = 0; i < converted_permissions.size(); i++) {
            System.out.println("Claim " + i);
            for (int j = 0; j < converted_permissions.get(0).size(); j++) {
                System.out.print(" Role " + j + " " + converted_permissions.get(i).get(j));
            }
            System.out.println();
        }
        createClaims(converted_permissions.get(0), converted_permissions.get(1), converted_permissions.get(2), converted_permissions.get(3));
    }

    private void initPermissions() {
        for (int i = 0; i < 4; i++) {
            permissions[i][0] = i;
            for (int j = 1; j < 6; j++) {
                permissions[i][j] = 1;
            }
        }
    }
}
