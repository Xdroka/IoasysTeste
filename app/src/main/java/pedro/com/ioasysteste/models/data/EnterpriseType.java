package pedro.com.ioasysteste.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnterpriseType {

    @SerializedName("enterprise_type_name")
    @Expose
    private String enterpriseTypeName;

    /**
     * No args constructor for use in serialization
     */
    public EnterpriseType() {
    }

    /**
     * @param enterpriseTypeName
     */
    public EnterpriseType(String enterpriseTypeName) {
        super();
        this.enterpriseTypeName = enterpriseTypeName;
    }

    public String getEnterpriseTypeName() {
        return enterpriseTypeName;
    }

    public void setEnterpriseTypeName(String enterpriseTypeName) {
        this.enterpriseTypeName = enterpriseTypeName;
    }

}

