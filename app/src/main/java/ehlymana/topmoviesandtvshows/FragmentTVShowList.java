package ehlymana.topmoviesandtvshows;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class FragmentTVShowList extends Fragment {
    private ArrayList<TMDbObject> TVShows=new ArrayList<TMDbObject>();
    AdapterTMDbObject adapter;
    OnItemClick oic;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_tmdb_objects, container, false);
    }
    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments().containsKey("TVShowList")) {
            TVShows = getArguments().getParcelableArrayList("TVShowList");
            ListView view = (ListView) getView().findViewById(R.id.listView);
            adapter = new AdapterTMDbObject(getActivity(), R.layout.list_element, TVShows);
            view.setAdapter(adapter);
            SearchView search = (SearchView) getActivity().findViewById(R.id.search);
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    //search
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    //search
                    return true;
                }
            });
            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    oic.onItemClickedF(position);
                }
            });
        }
    }
    public interface OnItemClick {
        public void onItemClickedF(int pos);
    }
}
