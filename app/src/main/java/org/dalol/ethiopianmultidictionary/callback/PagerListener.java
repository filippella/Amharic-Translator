package org.dalol.ethiopianmultidictionary.callback;

import android.support.v4.app.Fragment;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 4/17/2016
 */
public interface PagerListener {

    Fragment getItem(int position);

    int getCount();

    CharSequence getPageTitle(int position);
}
