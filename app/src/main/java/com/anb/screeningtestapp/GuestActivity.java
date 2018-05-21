package com.anb.screeningtestapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.anb.screeningtestapp.Adapter.GuestAdapter;
import com.anb.screeningtestapp.Retrofit.RetroServer;
import com.anb.screeningtestapp.model.Guest;
import com.anb.screeningtestapp.Retrofit.RequestInterface;
import com.anb.screeningtestapp.utils.NetworkState;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuestActivity extends AppCompatActivity {

    private static final String TAG = "GuestActivity";
    public static final String BASE_URL = "http://dry-sierra-6832.herokuapp.com";
    SwipeRefreshLayout swipe;
    ArrayList<Guest> guestlist = new ArrayList<>();
    NetworkState networkState = new NetworkState();
    GuestAdapter guestAdapter = null;

    GridView gridView;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_guest);
        setSupportActionBar(toolbar);

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        if (!realm.isEmpty()) {
            removeAllDataRealm();
        }

        gridView = findViewById(R.id.guest_list);
        swipe = findViewById(R.id.swipe_refresh_layout);

        guestAdapter = new GuestAdapter(GuestActivity.this, guestlist);
        requestJSONwithRetrofit();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                guestAdapter.guestList.clear();
                guestAdapter.notifyDataSetChanged();
                if (networkState.isNetworkAvailable(getApplicationContext())) {
                    if (!realm.isEmpty()) {
                        removeAllDataRealm();
                    }
                    requestJSONwithRetrofit();
                } else {
                    loadDataFromRealm();
                }
            }
        });


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridView.setNumColumns(2);
        } else {
            gridView.setNumColumns(3);
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent returnIntent = new Intent();
                TextView txtBirthday = view.findViewById(R.id.txtGuestBirthday);
                TextView txtName = view.findViewById(R.id.txtGuestName);
                returnIntent.putExtra("namaguest", txtName.getText().toString());
                returnIntent.putExtra("birthdayguest", txtBirthday.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

    }

    private void removeAllDataRealm() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private void requestJSONwithRetrofit() {

        final int[] img = {R.drawable.foto1,
                R.drawable.foto2,
                R.drawable.foto3,
                R.drawable.foto4,
                R.drawable.foto5,};

        RequestInterface request = RetroServer.getClient().create(RequestInterface.class);
        Call<ArrayList<Guest>> call = request.getJSON();

        call.enqueue(new Callback<ArrayList<Guest>>() {
            @Override
            public void onResponse(Call<ArrayList<Guest>> call, final Response<ArrayList<Guest>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    realm.beginTransaction();
                    Guest guestRealm = realm.createObject(Guest.class, response.body().get(i).id);
                    guestRealm.name = response.body().get(i).name;
                    guestRealm.birthday = response.body().get(i).birthday;
                    guestRealm.image = img[i % 5];
                    realm.commitTransaction();
                    guestlist.add(guestRealm);
                }
                guestAdapter = new GuestAdapter(GuestActivity.this, guestlist);
                gridView.setAdapter(guestAdapter);
                swipe.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ArrayList<Guest>> call, Throwable t) {
                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage());
                Toast.makeText(GuestActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                swipe.setRefreshing(false);
            }
        });
    }

    private void loadDataFromRealm() {
        RealmResults<Guest> results = realm.where(Guest.class).findAll();
        for (int i = 0; i < results.size(); i++) {
            guestlist.add(results.get(i));
        }
        guestAdapter = new GuestAdapter(GuestActivity.this, guestlist);
        gridView.setAdapter(guestAdapter);
        swipe.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
