package mtisfa.com.sfa.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Windows 8.1 on 18/10/2017.
 */

public class Outlet  {
    @SerializedName("name")
    public String name;
    @SerializedName("customer_name")
    public String customer_name;
    @SerializedName("longitude")
    public String longitude;
    @SerializedName("latitude")
    public String latitude;
    @SerializedName("default_address")
    public String default_address_location;
    @SerializedName("default_address_city")
    public String default_address_city;
    @SerializedName("default_contact_person")
    public String default_contact_person;
    @SerializedName("default_contact_person_phone")
    public String default_contact_person_phone;
    @SerializedName("default_contact_person_mobile_no")
    public String default_contact_person_mobile_no;
    @SerializedName("default_contact_person_name")
    public String default_contact_person_name;
    @SerializedName("customer_details")
    public String customer_details;
}