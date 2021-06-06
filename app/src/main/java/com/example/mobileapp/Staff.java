package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Staff extends AppCompatActivity {
    TextView staff_show ;
    Button StafBtn,back;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        staff_show = findViewById(R.id.staff_show);
        StafBtn = findViewById(R.id.StafBtn);
        back = findViewById(R.id.back);
        queue = Volley.newRequestQueue(this);

        StafBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseJson();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FitnessActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void ParseJson() {
        String strJson = "{\"Staff\":[{\"lname\":\"Hanks\",\"age\":23,\"email\":\"eduard.hanks@gmail.com\",\"fname\":\"Eduard\"},{\"lname\":\"Snow\",\"age\":25,\"email\":\"diana.snow@gmail.com\",\"fname\":\"Diana\"},{\"lname\":\"Bell\",\"age\":21,\"email\":\"edmund.bell@gmail.com\",\"fname\":\"Edmund\"},{\"lname\":\"Francis\",\"age\":30,\"email\":\"Nathaniel.francis@gmail.com\",\"fname\":\"Nathaniel\"},{\"lname\":\"Kim\",\"age\":29,\"email\":\"tami.kim@gmail.com\",\"fname\":\"Tami\"},{\"lname\":\"Page\",\"age\":21,\"email\":\"lucas.page@gmail.com\",\"fname\":\"Lucas\"},{\"lname\":\"Miles\",\"age\":23,\"email\":\"albert.miles@gmail.com\",\"fname\":\"Albert\"},{\"lname\":\"Adkins\",\"age\":24,\"email\":\"johnnie.adkins@gmail.com\",\"fname\":\"Johnnie\"},{\"lname\":\"Adams\",\"age\":22,\"email\":\"sharry.adams@gmail.com\",\"fname\":\"Sharry\"},{\"lname\":\"Matthews\",\"age\":25,\"email\":\"judy.matthews@gmail.com\",\"fname\":\"Judy\"}]}";

        String data = "";
        try {
            // Create the root JSONObject from the JSON string.
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Staff");

            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String fname = jsonObject.optString("fname").toString();
                String lname = jsonObject.optString("lname").toString();
                int age = Integer.parseInt(jsonObject.optString("age").toString());
                String email = jsonObject.optString("email").toString();

                data += fname + " " + lname + ", " + age + ", " + email + "\n\n";
            }
            staff_show.setText(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}