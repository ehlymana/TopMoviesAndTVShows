package ehlymana.topmoviesandtvshows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterTMDbObject extends ArrayAdapter<TMDbObject> {
    int resource;
    public AdapterTMDbObject(Context context, int _resource, List<TMDbObject> items) {
        super (context, _resource, items);
        resource=_resource;
    }
    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        LinearLayout newView;
        if (convertView==null) {
            newView=new LinearLayout(getContext());
            String inflater=Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li=(LayoutInflater)getContext().getSystemService(inflater);
            li.inflate(resource, newView, true);
        }
        else {
            newView=(LinearLayout)convertView;
        }
        TMDbObject object=getItem(position);
        TextView name=(TextView)newView.findViewById(R.id.nameList);
        ImageView image = (ImageView)newView.findViewById(R.id.imageList);
        name.setText(object.getName());
        // slika je tip string
        // image.setImageURI(object.getImage());
        return newView;
    }
}
