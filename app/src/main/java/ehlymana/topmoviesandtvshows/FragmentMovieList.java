package ehlymana.topmoviesandtvshows;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentMovieList extends Fragment {
    private ArrayList<TMDbObject> movies=new ArrayList<TMDbObject>();
    AdapterTMDbObject adapter;
    OnItemClick oic;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_tmdb_objects, container, false);
    }
    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments().containsKey("movieList")) {
            movies = getArguments().getParcelableArrayList("movieList");
            ListView view = (ListView) getView().findViewById(R.id.listView);
            adapter = new AdapterTMDbObject(getActivity(), R.layout.list_element, movies);
            view.setAdapter(adapter);
            //region Queries
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
            //endregion
            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    oic.onItemClickedMovie(position);
                }
            });
        }
    }
    public interface OnItemClick {
        public void onItemClickedMovie (int pos);
    }
}
