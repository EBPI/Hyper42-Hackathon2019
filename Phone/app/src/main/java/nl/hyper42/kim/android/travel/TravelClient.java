package nl.hyper42.kim.android.travel;

import nl.hyper42.kim.android.info.InfoService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TravelClient {
    /**
     * URLS
     */
    private static final String ROOT_URL = "http://localhost/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static TravelService getTravelService() {
        return getRetrofitInstance().create(TravelService.class);
    }

}
