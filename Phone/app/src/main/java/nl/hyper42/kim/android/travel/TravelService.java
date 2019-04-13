package nl.hyper42.kim.android.travel;

import nl.hyper42.kim.android.generated.info.FlightInfo;
import nl.hyper42.kim.android.generated.travel.TravelDataRequest;
import nl.hyper42.kim.android.generated.travel.TravelDataResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TravelService {
    @POST("register")
    Call<TravelDataResponse> registerFlight(@Body TravelDataRequest request);
}
