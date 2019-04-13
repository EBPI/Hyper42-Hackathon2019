package nl.hyper42.kim.android.travel;

import nl.hyper42.kim.android.generated.info.FlightInfo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TravelService {
    @GET("travelinfo")
    Call<FlightInfo> getTravelInfo();
}
