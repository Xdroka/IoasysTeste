package pedro.com.ioasysteste.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ListEnterprise {

    @SerializedName("enterprises")
    @Expose
    private List<Enterprise> enterprises = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListEnterprise() {
    }

    /**
     *
     * @param enterprises
     */
    public ListEnterprise(List<Enterprise> enterprises) {
        super();
        this.enterprises = enterprises;
    }

    public List<Enterprise> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(List<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }

}