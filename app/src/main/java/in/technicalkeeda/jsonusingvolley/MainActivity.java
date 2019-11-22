package in.technicalkeeda.jsonusingvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        final JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArrayRequest1= null;

                String s= "error";
                JSONArray jsonArray1= null;
                try {
                    jsonArray1 = response.getJSONArray("features");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject jsonObject1  = null;
                try {
                    jsonObject1 = jsonArray1.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject jsonObject2=jsonObject1.getJSONObject("properties");
                    s=jsonObject2.getString("place");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.i("mgs",s+" is found");
                Toast.makeText(MainActivity.this, s+"is found ", Toast.LENGTH_SHORT).show();








            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
       requestQueue.add(jsonObjectRequest);
        //requestQueue.add(jsonObjectRequest);  requestQueue.add(jsonObjectRequest);  requestQueue.add(jsonObjectRequest);

    }
}
