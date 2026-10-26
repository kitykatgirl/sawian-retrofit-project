package ph.me.sawiangooningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SawianAdapter extends ArrayAdapter<Sawian> {

    public SawianAdapter(Context context, List<Sawian> sawianList) {
        super(context, 0, sawianList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        Sawian sawian = getItem(position);

        if (sawian != null) {
            ((TextView) convertView.findViewById(R.id.textViewName))
                    .setText(sawian.getName());

            ((TextView) convertView.findViewById(R.id.textViewDesc))
                    .setText(sawian.getDescription());

            ((TextView) convertView.findViewById(R.id.textViewGameplay))
                    .setText(sawian.getGameplay());

            ((TextView) convertView.findViewById(R.id.textViewAim))
                    .setText(sawian.getAim());

            ((TextView) convertView.findViewById(R.id.textViewMove))
                    .setText(sawian.getMove());

            ((TextView) convertView.findViewById(R.id.textViewReload))
                    .setText(sawian.getReload());

            ((TextView) convertView.findViewById(R.id.textViewHealth))
                    .setText(sawian.getHealth());

            ((TextView) convertView.findViewById(R.id.textViewWeapon))
                    .setText(sawian.getWeapon());

            ((TextView) convertView.findViewById(R.id.textView2))
                    .setText(sawian.getName());
        }

        return convertView;
    }
}