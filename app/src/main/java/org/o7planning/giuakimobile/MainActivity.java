package org.o7planning.giuakimobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Xử lý sự kiện khi chọn mục trong Navigation Drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                setTitle(item.getTitle());


                if (item.getItemId() == R.id.nav_day1_layout) {
                    fragment = new Day1LayoutFragment();
                } else if (item.getItemId() == R.id.nav_day1_linearlayout) {
                    fragment = new Day1LinearLayoutFragment();
                } else if (item.getItemId() == R.id.nav_day2_activity) {
                    fragment = new LoginFragment();
                    // setTitle("Day2 Activity");
                } else if (item.getItemId() == R.id.nav_day2_listview_advanced) {
                    fragment = new ListViewAdvancedFragment();
                } else if (item.getItemId() == R.id.nav_day2_listview) {
                    fragment = new ListViewFragment();
                } else if (item.getItemId() == R.id.nav_day3_network_basic) {
                    fragment = new Day3NetworkBasicFragment();
                } else if (item.getItemId() == R.id.nav_day3_network_recyclerview) {
                    fragment = new Day3NetworkRecyclerViewFragment();
                } else if (item.getItemId() == R.id.nav_day3_network_weather) {
                    fragment = new Day3NetworkWeatherFragment();
                }

                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment_content_main, fragment)
                            .commit();
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Hiển thị Day1LayoutFragment mặc định khi mở ứng dụng
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, new Day1LayoutFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_day1_layout);
            setTitle("Day1_Layout");
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}