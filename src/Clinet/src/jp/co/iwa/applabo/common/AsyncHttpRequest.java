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

// �񓯊��ʐM�p�̃N���X
public class AsyncHttpRequest extends AsyncTask<Uri.Builder, Void, String> {
    
    private Activity mainActivity;

    public AsyncHttpRequest(Activity activity) {

        // �Ăяo�����̃A�N�e�B�r�e�B
        this.mainActivity = activity;
    }
    
    @Override
    protected String doInBackground(Uri.Builder... builder) {
        
    	httpRequestExecute("http://10.0.2.2:9000/");
		return "OK";
    }
    
    // ���̃��\�b�h�͔񓯊������̏I�������ɌĂяo����܂�
    @Override
    protected void onPostExecute(String result) {
        
    }
    
    private boolean httpRequestExecute(String httpTarget){
    	
    	HttpGet httpGet = new HttpGet(httpTarget);
		DefaultHttpClient client = new DefaultHttpClient();
		try{ 
			HttpResponse httpResponse = client.execute(httpGet);
			// �X�e�[�^�X�R�[�h���擾
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			// ���X�|���X���擾
			HttpEntity entity = httpResponse.getEntity();
			String response = EntityUtils.toString(entity);
			// ���\�[�X�����
			entity.consumeContent();
			// �N���C�A���g���I��������
			client.getConnectionManager().shutdown();
			return true;
		}catch(Exception e){
			// �T���v���Ȃ̂ň���Ԃ��܂����{�����ł̓n���h�����O���Ă�������
		}
		return false;
    
    }
}