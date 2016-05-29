package org.dalol.ethiopianmultidictionary.ui.base;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 4/17/2016
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Shows v4 Fragment.
     *
     * @param fragmentContainerId Identifier of the container to be replaced with Fragment
     * @param fragment            The new V4 fragment to place in the container
     * @param fragmentTag         Tag name for the fragment, to help find the fragment
     */
    protected void showFragment(@IdRes int fragmentContainerId, @NonNull android.support.v4.app.Fragment fragment, @NonNull String fragmentTag) {
        android.support.v4.app.Fragment previous = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (previous != null) {
            return;
        }
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(fragmentContainerId, fragment, fragmentTag);
        ft.commit();
    }

    /**
     * Shows Fragment.
     *
     * @param fragmentContainerId Identifier of the container to be replaced with Fragment
     * @param fragment            The new fragment to place in the container
     * @param fragmentTag         Tag name for the fragment, to help find the fragment
     */
    protected void showFragment(@IdRes int fragmentContainerId, @NonNull android.app.Fragment fragment, @NonNull String fragmentTag) {
        android.app.Fragment previous = getFragmentManager().findFragmentByTag(fragmentTag);
        if (previous != null) {
            return;
        }
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(fragmentContainerId, fragment, fragmentTag);
        ft.commit();
    }
}
