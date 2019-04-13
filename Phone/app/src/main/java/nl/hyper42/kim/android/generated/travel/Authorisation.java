
package nl.hyper42.kim.android.generated.travel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Authorisation {

    @SerializedName("ClaimName")
    @Expose
    private String claimName;
    @SerializedName("Who")
    @Expose
    private List<String> who = null;
    @SerializedName("Where")
    @Expose
    private List<String> where = null;
    @SerializedName("Role")
    @Expose
    private List<String> role = null;

    public String getClaimName() {
        return claimName;
    }

    public void setClaimName(String claimName) {
        this.claimName = claimName;
    }

    public Authorisation withClaimName(String claimName) {
        this.claimName = claimName;
        return this;
    }

    public List<String> getWho() {
        return who;
    }

    public void setWho(List<String> who) {
        this.who = who;
    }

    public Authorisation withWho(List<String> who) {
        this.who = who;
        return this;
    }

    public List<String> getWhere() {
        return where;
    }

    public void setWhere(List<String> where) {
        this.where = where;
    }

    public Authorisation withWhere(List<String> where) {
        this.where = where;
        return this;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public Authorisation withRole(List<String> role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("claimName", claimName).append("who", who).append("where", where).append("role", role).toString();
    }

}
