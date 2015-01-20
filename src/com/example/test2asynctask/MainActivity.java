package com.example.test2asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "test2asynctask";

    Button btn;
    TextView text;
    ProgressBar pb;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button1);
        text = (TextView) findViewById(R.id.text1);
        pb = (ProgressBar) findViewById(R.id.progressBar1);

        btnEvent();
    }   

    private void btnEvent() {
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new ExampleAsyncTask().execute("1", "2", "3", "4", "5");
            }
        });     
    }           

    // AsyncTaskŬ������ �׻� Subclassing �ؼ� ��� �ؾ� ��.
    // ��� �ڷ�����
    // background �۾��� ����� data�� �ڷ���: String ��
    // background �۾� ���� ǥ�ø� ���� ����� ����: Integer��
    // background �۾��� ����� ǥ���� �ڷ���: Long
    // ���ڸ� ������� ���� ��� Void Type ���� ����.
    public class ExampleAsyncTask extends AsyncTask<String, Integer, Long> {

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }   

        @Override
        protected void onPostExecute(Long result) {
        	Log.d(TAG, "AsyncTask: 4");
            btn.setText("Thread END");
            super.onPostExecute(result);
        }   

        @Override
        protected void onPreExecute() {
        	Log.d(TAG, "AsyncTask: 1");
            btn.setText("Thread START!!!!");
            super.onPreExecute();
        }

        @Override   
        protected void onProgressUpdate(Integer... values) {
        	Log.d(TAG, "AsyncTask: 3");
            pb.setProgress(values[0]);
            text.setText("����[" + values[0] +"]");
            super.onProgressUpdate(values);
        }
        
        @Override
        protected Long doInBackground(String... params) {
        	Log.d(TAG, "AsyncTask: 2");
            long result = 0;
            int numberOfParams = params.length;

            for (int i = 0; i < numberOfParams; i++) {
                SystemClock.sleep(1000);
                
                //android.os.AsyncTask.publishProgress()
                publishProgress((int) (((i + 1) / (float) numberOfParams) * 100));
            }
            return result;
        }
        
    }
    
}
