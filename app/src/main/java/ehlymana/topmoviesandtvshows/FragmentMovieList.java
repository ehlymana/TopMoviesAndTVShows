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

public class FragmentMovieList extends Fragment implements SearchMovie.OnMovieSearchDone {
    private ArrayList<TMDbObject> movies=new ArrayList<TMDbObject>();
    private ArrayList<TMDbObject> backup=new ArrayList<TMDbObject>();
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
                    movies.clear();
                    for (int i=0; i<backup.size(); i++) {
                        if (backup.get(i).name.contains(newText)) movies.add(backup.get(i));
                        adapter.notifyDataSetChanged();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    movies.clear();
                    for (int i=0; i<backup.size(); i++) {
                        if (backup.get(i).name.contains(query)) movies.add(backup.get(i));
                        adapter.notifyDataSetChanged();
                    }
                    return true;
                }
            });
            //endregion
            oic=(OnItemClick)getActivity();
            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    oic.onItemClickedMovie(position);
                }
            });
            if (backup.size()<10) new SearchMovie((SearchMovie.OnMovieSearchDone) FragmentMovieList.this).execute();
        }
    }
    @Override
    public void OnDone (ArrayList<TMDbObject> res) {
        backup.clear();
        movies.clear();
        for (int i=0; i<res.size(); i++) {
            backup.add(res.get(i));
            movies.add(res.get(i));
        }
        adapter.notifyDataSetChanged();
    }
    public interface OnItemClick {
        public void onItemClickedMovie (int pos);
    }
}