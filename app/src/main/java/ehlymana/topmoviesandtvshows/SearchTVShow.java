package ehlymana.topmoviesandtvshows;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SearchTVShow extends AsyncTask<String, String, Void> {
    public interface OnTVShowSearchDone {
        public void OnDone(ArrayList<TMDbObject> rez);
    }
    ArrayList<TMDbObject> res=new ArrayList<TMDbObject>();
    ArrayList<String> id=new ArrayList<String>();
    private OnTVShowSearchDone caller;
    public SearchTVShow(OnTVShowSearchDone p) {
        caller=p;
    }
    @Override
    protected Void doInBackground (String... params) {
        String url1 = "https://api.themoviedb.org/3/tv/top_rated?api_key=c9b0bbf7aca75298c8581cbfd53f19d5";
        try {
            URL url=new URL(url1);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            InputStream in=new BufferedInputStream(urlConnection.getInputStream());
            String result=turnStreamToString(in);
            JSONObject jo=new JSONObject(result);
            JSONArray items=jo.getJSONArray("results");
            for (int i=0; i<10; i++) {
                JSONObject TVShow=items.getJSONObject(i);
                id.add(TVShow.getString("id"));
            }
            for (int i=0; i<id.size(); i++) {
                url=new URL("https://api.themoviedb.org/3/tv/"+id.get(i)+"?api_key=c9b0bbf7aca75298c8581cbfd53f19d5");
                urlConnection=(HttpURLConnection)url.openConnection();
                in=new BufferedInputStream(urlConnection.getInputStream());
                result=turnStreamToString(in);
                jo=new JSONObject(result);
                String name=jo.getString("name");
                String description=jo.getString("overview");
                String image="https://image.tmdb.org/t/p/w138_and_h175_bestv2"+jo.getString("poster_path");
                res.add(new TMDbObject(name, description, image));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String turnStreamToString (InputStream is) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String line=null;
        try {
            while ((line=reader.readLine())!=null) {
                sb.append(line+"\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    @Override
    protected void onPostExecute (Void aVoid) {
        super.onPostExecute(aVoid);
        caller.OnDone(res);
    }
}