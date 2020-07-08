package com.example.test.util;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.test.R;

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = "BaseActivity===>";

    public BaseActivity(){

    }

    public void ShowToast(String text) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custome_toast_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
        TextView toastTextView = (TextView) layout.findViewById(R.id.toastTextView);
        ImageView toastImageView = (ImageView) layout.findViewById(R.id.toastImageView);
        toastTextView.setText(text);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG); // set the duration for the Toast
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        layout.setBackgroundResource(R.color.colorPrimary);
        toastTextView.setShadowLayer(0, 0, 0, 0);
        toast.setView(layout);
        toast.show();
    }

    public void showAlert(String message) {
        try {
            if (!this.isFinishing()) {
                new AlertDialog.Builder(this)
                        .setTitle(getResources().getString(R.string.app_name))
                        .setMessage(message)
                        .setCancelable(true)
                        .setPositiveButton(getResources().getString(R.string.ok), null)
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        } catch (Exception e) {
            printLog(BaseActivity.class,e.getMessage());
        }
    }

    public void printLog(Class<?> pClassName, String pStrMsg){
        Log.e(pClassName.getSimpleName(),pStrMsg);
    }
}
