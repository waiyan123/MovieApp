package itachi_waiyan.com.restapitest.ApiResponse;

import com.google.gson.annotations.SerializedName;

public class ProductCompany {

    @SerializedName("logo_path")
    String logo_path;

    @SerializedName("name")
    String name;

    public String getLogo_path() {
        return logo_path;
    }

    public String getName() {
        return name;
    }
}
