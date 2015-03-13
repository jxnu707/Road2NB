package kinds;

import utils.AndroidUtils;

import com.xu.road2nb.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewActivity extends Activity {

	private WebView wv;
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_webview_layout);
		
		wv = (WebView) findViewById(R.id.webview1);
		WebSettings websettings = wv.getSettings();
		//wv使能javascript
		websettings.setJavaScriptEnabled(true);
		//webview中链接跳转保持在webview中，而不是调用系统browser
		wv.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				
				wv.loadUrl(url);
				return true;
			}

			
		});
		//绑定一个java对象（此处只是个Object）到demo.html中的js中 js中通过window.AndroidInterface.showToast()（Android.showToast()）来执行这个java对象中的代码
		wv.addJavascriptInterface(new Object(){
			@JavascriptInterface
			public void showToast(){
//				AndroidUtils.showToast(MyWebViewActivity.this, "come from Android", "short");
				mHandler.post(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						wv.loadUrl("javascript:jsfunction()");
					}});
			}
			
		}, "AndroidInterface");
		
		wv.loadUrl("file:///android_asset/demo1.html");
	}
	
	//屏蔽系统back返回键 实现网页回退
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {       
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {       
            wv.goBack();       
                   return true;       
        }       
        return super.onKeyDown(keyCode, event);       
    }
	

}
