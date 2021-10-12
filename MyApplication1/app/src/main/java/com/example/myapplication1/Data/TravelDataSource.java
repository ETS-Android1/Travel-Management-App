package com.example.myapplication1.Data;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication1.Entities.Travel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TravelDataSource {
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference DBRef = database.getReference("travels");
    MutableLiveData<Boolean> isSuccess = new MutableLiveData<Boolean>();
    ArrayList<String> Travels=new ArrayList<String>();
    /*public TravelDataSource()
    {
        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // USERS.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {




                            String phone = snapshot.getKey().toString();

                            // Map<String, Object> td = (HashMap<String,Object>) dataSnapshot.getValue();

                            //List<Object> values = td.values();


                            Travels.add(phone);

                    }
                    //if (notifyToTravelListListener != null)
                    // notifyToTravelListListener.onTravelsChanged();
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }*/
    public void insertTravel(Travel p) {
        String id = DBRef.push().getKey();
        p.setTravelId(id);
        DBRef.child(id).setValue(p).addOnSuccessListener(aVoid -> isSuccess.postValue(true));
        /*for (String t :
                Travels) {

            ///////////////////////////
           // Travel h=(Travel)t.get(0);
            if (travel.getClientPhone().equals( t));
            {
                DatabaseReference node = FirebaseDatabase.getInstance().getReference("travels/"+travel.getClientPhone());

                DatabaseReference newPostRef = node.push();
                newPostRef.setValue(travel);




               // t.add(travel);
                /////////String key = travel.getClientEmail();
                //DBRef.child(key)
                   //     .setValue(t)
                     //   .addOnSuccessListener(aVoid -> isSuccess.postValue(true));


                //TODO add the existing one.
                return;
            }
        }
        ArrayList<Travel> newnode=new ArrayList<Travel>();
        newnode.add(travel);
        String key = travel.getClientPhone();
        DBRef.child(key)
                .setValue(newnode)
                .addOnSuccessListener(aVoid -> isSuccess.postValue(true));*/
    }

    public MutableLiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }
}
