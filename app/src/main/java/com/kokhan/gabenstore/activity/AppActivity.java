package com.kokhan.gabenstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.kokhan.gabenstore.fragment.CartFragment;
import com.kokhan.gabenstore.fragment.CatalogFragment;
import com.kokhan.gabenstore.data.DataStorage;
import com.kokhan.gabenstore.R;

public class AppActivity extends AppCompatActivity {

    AHBottomNavigation bottomNavigation;
    CatalogFragment catalogFragment;
    CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catalogFragment = new CatalogFragment();
        cartFragment = new CartFragment();

        setupBottomNavigation();

        setFragment(catalogFragment);
    }

    @SuppressLint("ResourceAsColor")
    private void setupBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Catalog", R.drawable.ic_dashboard_black_24dp);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Cart", R.drawable.ic_shopping_cart_black_24dp);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);

        bottomNavigation.setColored(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        bottomNavigation.setCurrentItem(0);
        setNotificationBadgeValue();
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case 0:
                    setFragment(catalogFragment);
                    break;
                case 1:
                    setFragment(cartFragment);
                    break;
            }
            return true;
        });
    }

    public void setNotificationBadgeValue() {
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(DataStorage.getInstance().getCartGameList().size())).build();
        bottomNavigation.setNotification(notification, 1);
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
