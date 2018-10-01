package pedro.com.ioasysteste.models.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Enterprise {

    @SerializedName("enterprise_name")
    @Expose
    private String enterpriseName;

    @SerializedName("photo")
    @Expose
    private Object photo;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("enterprise_type")
    @Expose
    private EnterpriseType enterpriseType;

    /**
     * No args constructor for use in serialization
     */
    public Enterprise() {
    }

    /**
     * @param enterpriseName
     * @param photo
     * @param city
     * @param country
     * @param enterpriseType
     * @param description
     */
    public Enterprise(String enterpriseName, Object photo, String description, String city, String country, EnterpriseType enterpriseType) {
        super();
        this.enterpriseName = enterpriseName;
        this.photo = photo;
        this.description = description;
        this.city = city;
        this.country = country;
        this.enterpriseType = enterpriseType;
    }


    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

}