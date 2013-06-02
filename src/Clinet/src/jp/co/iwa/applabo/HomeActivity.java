package jp.co.iwa.applabo;

import jp.co.iwa.applabo.common.AsyncHttpRequest;
import jp.co.iwa.applabo.db.SQLiteHelper;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeActivity extends Activity implements OnClickListener {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        Uri.Builder builder = new Uri.Builder();
        AsyncHttpRequest task = new AsyncHttpRequest(this);
		task.execute(builder);
		
		View toDbOperation = findViewById(R.id.sqliteOperationButton);
		toDbOperation.setOnClickListener(this);
    }
    
    /**
     * アプリケーションの終了時にDBをクローズします。
     */
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	SQLiteHelper.GetInstance(this).close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.sqliteOperationButton:
            Intent i = new Intent (this, SQLiteOperation.class);
            startActivity(i);
            break;
        }
    }
}
