package jp.co.iwa.applabo.common;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;

// 非同期通信用のクラス
public class AsyncHttpRequest extends AsyncTask<Uri.Builder, Void, String> {
    
    private Activity mainActivity;

    public AsyncHttpRequest(Activity activity) {

        // 呼び出し元のアクティビティ
        this.mainActivity = activity;
    }
    
    @Override
    protected String doInBackground(Uri.Builder... builder) {
        
    	httpRequestExecute("http://10.0.2.2:9000/");
		return "OK";
    }
    
    // このメソッドは非同期処理の終わった後に呼び出されます
    @Override
    protected void onPostExecute(String result) {
        
    }
    
    private boolean httpRequestExecute(String httpTarget){
    	
    	HttpGet httpGet = new HttpGet(httpTarget);
		DefaultHttpClient client = new DefaultHttpClient();
		try{ 
			HttpResponse httpResponse = client.execute(httpGet);
			// ステータスコードを取得
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			// レスポンスを取得
			HttpEntity entity = httpResponse.getEntity();
			String response = EntityUtils.toString(entity);
			// リソースを解放
			entity.consumeContent();
			// クライアントを終了させる
			client.getConnectionManager().shutdown();
			return true;
		}catch(Exception e){
			// サンプルなので握りつぶしますが本実装ではハンドリングしてください
		}
		return false;
    
    }
}