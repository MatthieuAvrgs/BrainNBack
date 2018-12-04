package fr.utt.if26.brainn_back;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.brainn_back.Game.Statistique;

public class StatistiquesAdapter extends BaseAdapter {

    public List<Statistique> productList;
    Activity activity;

    public StatistiquesAdapter(Activity activity, List<Statistique> productList) {
        super();
        this.activity = activity;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView titre;
        TextView bonnesReponses;
        TextView mauvaisesReponses;
        TextView oublies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_une_ligne_statistique, null);
            holder = new ViewHolder();
            holder.titre = (TextView) convertView.findViewById(R.id.titre);
            holder.bonnesReponses = (TextView) convertView.findViewById(R.id.bonnesReponses);
            holder.mauvaisesReponses = (TextView) convertView
                    .findViewById(R.id.mauvaisesReponses);
            holder.oublies = (TextView) convertView.findViewById(R.id.oublies);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Statistique item = productList.get(position);
        holder.titre.setText(item.getTitre().toString());
        holder.bonnesReponses.setText(String.valueOf(item.getBonnesReponses()));
        holder.mauvaisesReponses.setText(String.valueOf(item.getMauvaisesReponses()));
        holder.oublies.setText(String.valueOf(item.getOublies()));

        return convertView;
    }
}
