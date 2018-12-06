package com.example.shangsheingoh.scaneatserverside.Common;

import com.example.shangsheingoh.scaneatserverside.Model.User;
import com.example.shangsheingoh.scaneatserverside.Model.UserProfile;

public class Common {
    public static UserProfile currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final int PICK_IMAGE_REQUEST = 71;

    public static String convertCodeToStatus(String status){
        if(status.equals("0")){
            return "Placed";
        }
        else if(status.equals("1")){
            return "Ready";
        }
        else if(status.equals("2")){
            return "Shipped";
        }
        else{
            return "Finished";
        }
    }
}
