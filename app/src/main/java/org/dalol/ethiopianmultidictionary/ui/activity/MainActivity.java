package org.dalol.ethiopianmultidictionary.ui.activity;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.dalol.ethiopianmultidictionary.R;
import org.dalol.ethiopianmultidictionary.adapter.FlagSpinnerAdapter;
import org.dalol.ethiopianmultidictionary.adapter.TabsPagerAdapter;
import org.dalol.ethiopianmultidictionary.callback.PagerListener;
import org.dalol.ethiopianmultidictionary.model.LanguageFlag;
import org.dalol.ethiopianmultidictionary.ui.base.BaseActivity;
import org.dalol.ethiopianmultidictionary.ui.fragment.HomeFragment;
import org.dalol.ethiopianmultidictionary.utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements PagerListener {

    private static final CharSequence[] TITLES = {"Home", "Recent", "Favourites"};
    public static final int DP = 56;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tabs) TabLayout mTabLayout;
    @Bind(R.id.viewpager) ViewPager mViewPager;
    @Bind(R.id.drawerLayout) DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view) NavigationView mNavigationView;
    @Bind(R.id.sourceLanguageSpinner) Spinner mSourceLanguageSpinner;

    private ActionBarDrawerToggle mDrawerToggle;


    private TabsPagerAdapter mAdapter;
    private int[] tabIcons = { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
        mToolbar.setTitle("Multi Language Dictionary");
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        configNavigationWidth();
        configPager();

//        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,
//                new String[] {"water 1", "water 2", "water 3", "water 4"} );
//        mSourceLanguageSpinner.setAdapter(spinnerAdapter);

        mSourceLanguageSpinner.setAdapter(new FlagSpinnerAdapter(getApplicationContext(), getFlags()));
        mSourceLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LanguageFlag selectedFlag = (LanguageFlag) parent.getItemAtPosition(position);
                Toast toastSpinnerSelection = Toast.makeText(getApplicationContext(), selectedFlag.getLanguageTitle(), Toast.LENGTH_SHORT);
                toastSpinnerSelection.setGravity(Gravity.LEFT| Gravity.BOTTOM,20,150);
                toastSpinnerSelection.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //configTabLayout();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("Title");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.nav_share:
                        Toast.makeText(getApplicationContext(), "Share Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_about:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "About Selected", Toast.LENGTH_SHORT).show();
                            }
                        }, 250);

                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void configNavigationWidth() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        mNavigationView.getLayoutParams().width = (int) (metrics.widthPixels - CommonUtils.convertDpToPixel(DP, getApplicationContext()));
    }

    private void configTabLayout() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void configPager() {
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), MainActivity.this);
//        mAdapter.addFragment(new TabFragment(), "RECIPE");
//        mAdapter.addFragment(new TabFragment(), "PREPARATION");
//        mAdapter.addFragment(new TabFragment(), "HISTORY");
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        if (id == android.R.id.home) {
            Toast.makeText(getApplicationContext(), "Home Clicked!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Fragment getItem(int position) {
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    public List<LanguageFlag> getFlags() {
        List<LanguageFlag> flags = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LanguageFlag flag = new LanguageFlag();
            flag.setLanguageTitle("English");
            flag.setResId(R.drawable.it);
            flags.add(flag);
        }
        return flags;
    }
}
