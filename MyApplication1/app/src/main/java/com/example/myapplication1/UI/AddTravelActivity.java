package com.example.myapplication1.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication1.Entities.Travel;
import com.example.myapplication1.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class                                                                                                                                        AddTravelActivity extends AppCompatActivity {
    protected TravelViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        viewModel = ViewModelProviders.of(this).get(TravelViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_travel_activity);
        EditText phone  = (EditText) findViewById(R.id.phone);
        //check phone number
       phone.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { if(s.length()!=10&& s.length()!=9) { phone.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP); } }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { phone.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP); }
            public void afterTextChanged(Editable s) { }
        });
        viewModel.getIsSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                Toast.makeText(AddTravelActivity.this, "ההזמנה נקלטה בהצלחה", Toast.LENGTH_SHORT).show();
            }
        } );
        Button b=findViewById(R.id.button2); //0544292399 asi
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ArrayList<String> h= CheckPropriety();
               if(h.size()!=0) {
                   build.setTitle("רק רגע, שימ/י לב:");
                   build.setIcon(R.drawable.common_google_signin_btn_icon_disabled);
                   String ErrorList = "";
                   for (String i : h) {
                       ErrorList += i +'\n';
                   }
                   build.setMessage(ErrorList);
                   AlertDialog alert = build.create();
                   alert.show();

               }

               else {
                   Travel t = CreateTravel();
                   viewModel.insertTravel(t);
               }



            }
        });

    }

    public ArrayList<String> CheckPropriety() {


        ArrayList<String> CountError = new ArrayList<String>();
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText mail = (EditText) findViewById(R.id.mailAddress);
        String Mail = mail.getText().toString();
        String Phone = phone.getText().toString();
        EditText name = (EditText) findViewById(R.id.name);
        String Name = name.getText().toString();
        DatePicker sd = (DatePicker) findViewById(R.id.startDate);
        Date travelDate = new Date(sd.getYear(), sd.getMonth(), sd.getDayOfMonth());
        DatePicker fd = (DatePicker) findViewById(R.id.finishdate);
        Date arrivalDate = new Date(fd.getYear(), fd.getMonth(), fd.getDayOfMonth());
        EditText numoftrav  = (EditText) findViewById(R.id.numOfTravelers);
        String Numoftrav=   numoftrav.getText().toString();
        if(Phone.isEmpty()||Mail.isEmpty()||Name.isEmpty()||Numoftrav.isEmpty())
        {
            CountError.add("יש למלא את כל השדות");
            return CountError;
        }
        if (!android.util.Patterns.PHONE.matcher(Phone).matches() || (Phone.length() != 9 && Phone.length() != 10))
            CountError.add("מספר הטלפון לא תקין");

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Mail).matches())
            CountError.add("כתובת מייל לא תקינה");

        Pattern p = Pattern.compile("([0-9])");
        if (p.matcher(Name.trim()).find())
            CountError.add("על השם להכיל אותיות בלבד");
        boolean b=travelDate.after(new Date(System.currentTimeMillis()));
        boolean n=arrivalDate.after(travelDate);

        if ((!travelDate.after(new Date(System.currentTimeMillis()))) || (!arrivalDate.after(travelDate)))
            CountError.add("תאריכים לא חוקיים");

        if(Integer.parseInt( Numoftrav)==0)
            CountError.add("בחר מספר נוסעים");



        return CountError;
    }

    public Travel CreateTravel(){
        EditText phone  = (EditText) findViewById(R.id.phone);
        String Phone= phone.getText().toString();
        EditText name  = (EditText) findViewById(R.id.name);
        String Name=   name.getText().toString();
        EditText mail  = (EditText) findViewById(R.id.mailAddress);
        String Mail=   mail.getText().toString();
        DatePicker sd  = (DatePicker) findViewById(R.id.startDate);
        Date travelDate=new Date(sd.getYear(),sd.getMonth(),sd.getDayOfMonth());
        DatePicker fd  = (DatePicker) findViewById(R.id.finishdate);
        Date arrivalDate=new Date(fd.getYear(),fd.getMonth(),fd.getDayOfMonth());

        EditText Saddress  = (EditText) findViewById(R.id.city);
        String saddress=   Saddress.getText().toString();
        //LatLng g=getLocationFromAddress(this,saddress);
        //UserLocation ul=new UserLocation(g.latitude,g.longitude);
        EditText street  = (EditText) findViewById(R.id.street);
        String street1=   street.getText().toString();

        String newAddress=street1+","+saddress+","+"ישראל";
        LatLng Loc=getLocationFromAddress(this,newAddress);

        EditText numoftrav  = (EditText) findViewById(R.id.numOfTravelers);
        int Numoftrav=  Integer.parseInt( numoftrav.getText().toString());
        EditText t  = (EditText) findViewById(R.id.destination);
        String target=t.getText().toString();




        Travel travel=new Travel(Name, Phone, Mail,newAddress,
                travelDate,  arrivalDate,Numoftrav,target,Loc);

        return travel;



    }



    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng LatLan= null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            LatLan= new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return LatLan;
    }



}