package org.dalol.ethiopianmultidictionary.model;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 4/20/2016
 */
public class LanguageFlag {

    private int mResId;
    private String mLanguageTitle;

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        mResId = resId;
    }

    public String getLanguageTitle() {
        return mLanguageTitle;
    }

    public void setLanguageTitle(String languageTitle) {
        mLanguageTitle = languageTitle;
    }
}
