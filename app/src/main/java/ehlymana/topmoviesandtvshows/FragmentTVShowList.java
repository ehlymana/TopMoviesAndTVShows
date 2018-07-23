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

public class FragmentTVShowList extends Fragment implements SearchTVShow.OnTVShowSearchDone {
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
            //region Queries
            SearchView search = (SearchView) getActivity().findViewById(R.id.search);
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    for (int i=0; i<TVShows.size(); i++) {
                        if (!TVShows.get(i).name.contains(newText)) TVShows.remove(i);
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    for (int i=0; i<TVShows.size(); i++) {
                        if (!TVShows.get(i).name.contains(query)) TVShows.remove(i);
                    }
                    return true;
                }
            });
            //endregion
            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    oic.onItemClickedTVShow(position);
                }
            });
            new SearchTVShow((SearchTVShow.OnTVShowSearchDone) FragmentTVShowList.this).execute();
        }
    }
    @Override
    public void OnDone (ArrayList<TMDbObject> res) {
        TVShows.clear();
        for (int i=0; i<res.size(); i++) TVShows.add(res.get(i));
        adapter.notifyDataSetChanged();
    }
    public interface OnItemClick {
        public void onItemClickedTVShow(int pos);
    }
}