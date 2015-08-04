package yw.cn.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yw.cn.domain.Mp3Info;
import yw.cn.utils.MediaUtil;

public class MainActivity extends Activity {
    ListView listView;
    Button play_music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)this.findViewById(R.id.music_list);
        List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> hashMap;
        List<Mp3Info> mp3Infos;
        Mp3Info mp3Info;
        mp3Infos = MediaUtil.getMp3Infos(this);
        for (int i = 0;i<mp3Infos.size();i++) {
            mp3Info = mp3Infos.get(i);
            hashMap = new HashMap<String, String>();
            hashMap.put("title",mp3Info.getTitle());
            hashMap.put("album",mp3Info.getAlbum());
            hashMap.put("albumId",""+mp3Info.getAlbumId());
            hashMap.put("displayName",mp3Info.getDisplayName());
            list.add(hashMap);
        }
        play_music = (Button)this.findViewById(R.id.play_music);
        play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        SimpleAdapter listAdapter = new SimpleAdapter(this,list,R.layout.item,new String[] {"title","displayName"},new int[]{R.id.title,R.id.displayName});
        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
