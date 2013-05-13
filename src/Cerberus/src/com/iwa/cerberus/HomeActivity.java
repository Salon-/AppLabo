package com.iwa.cerberus;

import com.iwa.common.AsyncHttpRequest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        Uri.Builder builder = new Uri.Builder();
        AsyncHttpRequest task = new AsyncHttpRequest(this);
		task.execute(builder);
		// リポジトリ運用のテストです。 Fork → クローン作成 → コミット → プルリクエスト のパターンです
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
}
