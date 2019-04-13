
package nl.hyper42.kim.android.generated.travel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ClaimAddress {

    @SerializedName("claimName")
    @Expose
    private String claimName;
    @SerializedName("claimAddress")
    @Expose
    private String claimAddress;

    public String getClaimName() {
        return claimName;
    }

    public void setClaimName(String claimName) {
        this.claimName = claimName;
    }

    public ClaimAddress withClaimName(String claimName) {
        this.claimName = claimName;
        return this;
    }

    public String getClaimAddress() {
        return claimAddress;
    }

    public void setClaimAddress(String claimAddress) {
        this.claimAddress = claimAddress;
    }

    public ClaimAddress withClaimAddress(String claimAddress) {
        this.claimAddress = claimAddress;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("claimName", claimName).append("claimAddress", claimAddress).toString();
    }

}
