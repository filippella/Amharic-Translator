package org.dalol.ethiopianmultidictionary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.ethiopianmultidictionary.R;
import org.dalol.ethiopianmultidictionary.model.LanguageFlag;

import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 4/20/2016
 */
public class FlagSpinnerAdapter extends ArrayAdapter<LanguageFlag> {

    private final List<LanguageFlag> mFlags;

    public FlagSpinnerAdapter(Context context, List<LanguageFlag> flags) {
        super(context, R.layout.language_item_layout, R.id.languageTitle, flags);
        mFlags = flags;
    }

    @Override
    public int getCount() {
        return mFlags.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder ;
        View rowview = convertView;
        if (rowview==null) {
            holder = new viewHolder();
            rowview = LayoutInflater.from(getContext()).inflate(R.layout.language_item_layout, null, false);

            holder.txtTitle = (TextView) rowview.findViewById(R.id.languageTitle);
            holder.imageView = (ImageView) rowview.findViewById(R.id.languageIcon);
            rowview.setTag(holder);
        }else{
            holder = (viewHolder) rowview.getTag();
        }

        LanguageFlag flag = mFlags.get(position);
        holder.txtTitle.setText(flag.getLanguageTitle());
        holder.imageView.setImageResource(flag.getResId());
        return rowview;
    }

    @Override
    public View getDropDownView(int position, View convertView,  ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private class viewHolder{
        TextView txtTitle;
        ImageView imageView;
    }
}
