package mtisfa.com.sfa.model.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mtisfa.com.sfa.model.Assignment;

/**
 * Created by Windows 8.1 on 24/09/2017.
 */

public class AssignmentListResponse {
    @SerializedName("data")
    public List<Assignment> data;
}
