package com.majavrella.bloodfactory.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.majavrella.bloodfactory.user.UserActivity;

public abstract class UserFragment extends Fragment {
	public UserActivity mActivity;
    private AddFragmentHandler fragmentHandler;
	protected FirebaseAuth mFirebaseAuth;
	protected ProgressDialog progress;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        fragmentHandler = new AddFragmentHandler(getActivity().getSupportFragmentManager());
        mActivity		=	(UserActivity) this.getActivity();
        mFirebaseAuth = FirebaseAuth.getInstance();
        progress=new ProgressDialog(mActivity);
	}

	@Override
	public void onResume() {
		super.onResume();
		getActivity().setTitle(getTitle());
	}

	public void hideKeyboard(Context ctx) {
		InputMethodManager inputManager = (InputMethodManager) ctx
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// check if no view has focus:
		View v = ((Activity) ctx).getCurrentFocus();
		if (v == null)
			return;
		inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

    public void showSnackbar(View view, String text) {
        final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {  }
                });
        snackbar.show();
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

    protected void add(UserFragment fragment) {
        fragmentHandler.add(fragment);
    }

	public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

	public void setStatusBarColor(String color){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = mActivity.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(Color.parseColor(color));
		}
	}
}
