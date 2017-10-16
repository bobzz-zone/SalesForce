package mtisfa.com.sfa.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Windows 8.1 on 23/09/2017.
 */

public class Assignment extends RealmObject {
    @SerializedName("status")
    public String status;
    @SerializedName("date")
    public Date date;
    @SerializedName("user")
    public String user;
    @SerializedName("note")
    public String note;
    @SerializedName("customer")
    public String customer;
    @SerializedName("contact_person")
    public String contact_person;
    @SerializedName("contact_display")
    public String contact_display;
    @SerializedName("phone")
    public String phone;
}
