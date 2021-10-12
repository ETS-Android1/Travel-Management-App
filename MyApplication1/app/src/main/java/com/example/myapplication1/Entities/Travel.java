package com.example.myapplication1.Entities;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.HashMap;

public class Travel {
    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    private String travelId;
    private String clientName;
    private String ClientPhone;
    private String ClientEmail;
    private String UserLocation;

    public String getTarget() {
        return Target;
    }

    private String Target;
    private RequestType requesType;
    private Date TravelDate;
    private Date ArrivalDate;
    private HashMap<String, Boolean> Company;
    private int NumOfTravelers;

    public com.example.myapplication1.Entities.UserLocation getUs() {
        return us;
    }

    private UserLocation us;
    public Travel() { }

    public Travel(String clientName, String clientPhone, String clientEmail, String city,
                  Date travelDate, Date arrivalDate, int numoftravelers, String target, LatLng Loc) {

        this.clientName = clientName;
        this.ClientPhone = clientPhone;
        this.ClientEmail = clientEmail;
        this.UserLocation =city;
        this.Target=target;
        this.TravelDate = travelDate;
        this.ArrivalDate = arrivalDate;
        this.NumOfTravelers=numoftravelers;
        Company=new HashMap<>();
       // Company.put("GNA",false);
        us=new UserLocation(Loc.latitude,Loc.longitude);
        this.requesType= RequestType.sent;

    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return ClientPhone;
    }

    public String getClientEmail() { return ClientEmail; }

    public String getUserLocation() {
        return UserLocation;
    }

    public RequestType getRequesType() {
        return requesType;
    }

    public Date getTravelDate() {
        return TravelDate;
    }

    public Date getArrivalDate() {
        return ArrivalDate;
    }

    public HashMap<String, Boolean> getCompany() {
        return Company;
    }

    public int getNumOfTravelers() {
        return NumOfTravelers;
    }

    public enum RequestType {
        sent(0), accepted(1), run(2), close(3);
        private final Integer code;
        RequestType(Integer value) {
            this.code = value;
        }
        public Integer getCode() {
            return code;
        }
    }













    }


