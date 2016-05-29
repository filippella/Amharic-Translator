package org.dalol.ethiopianmultidictionary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.dalol.ethiopianmultidictionary.callback.PagerListener;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 4/17/2016
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private final PagerListener mListener;

    public TabsPagerAdapter(FragmentManager manager, PagerListener listener) {
        super(manager);
        mListener = listener;
    }

    @Override
    public Fragment getItem(int position) {
        return mListener.getItem(position);
    }

    @Override
    public int getCount() {
        return mListener.getCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListener.getPageTitle(position);
    }
}