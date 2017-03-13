package com.majavrella.bloodfactory.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.majavrella.bloodfactory.R;
import com.majavrella.bloodfactory.activities.MainActivity;
import com.majavrella.bloodfactory.base.*;
import com.majavrella.bloodfactory.user.UserActivity;

public abstract class BaseFragment extends Fragment {
    public MainActivity mActivity;
    private AddFragmentHandler fragmentHandler;
    protected ProgressDialog progress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragmentHandler = new AddFragmentHandler(getActivity().getSupportFragmentManager());
		mActivity		=	(MainActivity) this.getActivity();
		progress=new ProgressDialog(mActivity);
	}

	@Override
	public void onResume() {
		super.onResume();
		getActivity().setTitle(getTitle());
	}

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

	public void hideIt(LinearLayout ll){
		ll.setVisibility(View.GONE);
	}

	public void setErrorMsg(LinearLayout linearLayout, TextView tv, String msg){
		linearLayout.setVisibility(View.VISIBLE);
		tv.setText(msg);
	}

	public String getStringDataFromEditText(EditText v){
		return v.getText().toString().trim();
	}

	public String getStringDataFromSpinner(Spinner v){
		return v.getSelectedItem().toString();
	}

	public String getStringDataFromRadioButton(RadioButton v){
		return v.getText().toString();
	}

	public boolean isNameValid(String name){
		return name.matches(Constants.nameRegex);
	}
	public boolean isEmailValid(String email){
		return email.matches(Constants.emailRegex);
	}
	public boolean isPhoneValid(String phone){
		return phone.matches(Constants.mobRegex);
	}
	public boolean isDateValid(String date){
		return date.matches(Constants.dateRegex);
	}

	protected abstract String getTitle();

	protected void add(BaseFragment fragment) {
		fragmentHandler.add(fragment);
	}

	public boolean isNetworkAvailable() {
		final ConnectivityManager connectivityManager = ((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE));
		return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
	}

    public void showDialog(String title, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setIcon(R.drawable.error);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show(); //show() should be called before dialog.getButton().

        final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        positiveButtonLL.gravity = Gravity.CENTER;
        positiveButton.setLayoutParams(positiveButtonLL);
	}
}
