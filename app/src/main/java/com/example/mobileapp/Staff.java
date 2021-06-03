package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
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
    Button StafBtn;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        staff_show = findViewById(R.id.staff_show);
        StafBtn = findViewById(R.id.StafBtn);
        queue = Volley.newRequestQueue(this);
        StafBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseJson();
            }
        });
    }
    private void ParseJson() {
        String url = "http://myjson.dit.upm.es/api/bins/1uvx";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Staff");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject Staff = jsonArray.getJSONObject(i);
                                String firstName = Staff.getString("fname");
                                String lastName = Staff.getString("lname");
                                int age = Staff.getInt("age");
                                String email = Staff.getString("email");
                                staff_show.append(firstName + ", "+lastName + ", "  + age + ", " + email + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }
}