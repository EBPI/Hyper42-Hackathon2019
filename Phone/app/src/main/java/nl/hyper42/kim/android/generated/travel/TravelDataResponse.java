
package nl.hyper42.kim.android.generated.travel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TravelDataResponse {

    @SerializedName("dataHashAddress")
    @Expose
    private String dataHashAddress;
    @SerializedName("dataHashSalt")
    @Expose
    private String dataHashSalt;
    @SerializedName("claimAddresses")
    @Expose
    private List<ClaimAddress> claimAddresses = null;
    @SerializedName("photoKey")
    @Expose
    private String photoKey;
    @SerializedName("photoAddress")
    @Expose
    private String photoAddress;

    public String getDataHashAddress() {
        return dataHashAddress;
    }

    public void setDataHashAddress(String dataHashAddress) {
        this.dataHashAddress = dataHashAddress;
    }

    public TravelDataResponse withDataHashAddress(String dataHashAddress) {
        this.dataHashAddress = dataHashAddress;
        return this;
    }

    public String getDataHashSalt() {
        return dataHashSalt;
    }

    public void setDataHashSalt(String dataHashSalt) {
        this.dataHashSalt = dataHashSalt;
    }

    public TravelDataResponse withDataHashSalt(String dataHashSalt) {
        this.dataHashSalt = dataHashSalt;
        return this;
    }

    public List<ClaimAddress> getClaimAddresses() {
        return claimAddresses;
    }

    public void setClaimAddresses(List<ClaimAddress> claimAddresses) {
        this.claimAddresses = claimAddresses;
    }

    public TravelDataResponse withClaimAddresses(List<ClaimAddress> claimAddresses) {
        this.claimAddresses = claimAddresses;
        return this;
    }

    public String getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
    }

    public TravelDataResponse withPhotoKey(String photoKey) {
        this.photoKey = photoKey;
        return this;
    }

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
    }

    public TravelDataResponse withPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dataHashAddress", dataHashAddress).append("dataHashSalt", dataHashSalt).append("claimAddresses", claimAddresses).append("photoKey", photoKey).append("photoAddress", photoAddress).toString();
    }

}
