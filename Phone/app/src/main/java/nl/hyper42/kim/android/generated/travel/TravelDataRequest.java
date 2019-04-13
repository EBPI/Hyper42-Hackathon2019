
package nl.hyper42.kim.android.generated.travel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TravelDataRequest {

    @SerializedName("passportData")
    @Expose
    private String passportData;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("travelData")
    @Expose
    private String travelData;
    @SerializedName("Authorisation")
    @Expose
    private List<Authorisation> authorisation = null;

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public TravelDataRequest withPassportData(String passportData) {
        this.passportData = passportData;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public TravelDataRequest withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getTravelData() {
        return travelData;
    }

    public void setTravelData(String travelData) {
        this.travelData = travelData;
    }

    public TravelDataRequest withTravelData(String travelData) {
        this.travelData = travelData;
        return this;
    }

    public List<Authorisation> getAuthorisation() {
        return authorisation;
    }

    public void setAuthorisation(List<Authorisation> authorisation) {
        this.authorisation = authorisation;
    }

    public TravelDataRequest withAuthorisation(List<Authorisation> authorisation) {
        this.authorisation = authorisation;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("passportData", passportData).append("photo", photo).append("travelData", travelData).append("authorisation", authorisation).toString();
    }

}
