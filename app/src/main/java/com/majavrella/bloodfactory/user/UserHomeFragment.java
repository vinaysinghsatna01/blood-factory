package com.majavrella.bloodfactory.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.majavrella.bloodfactory.R;
import com.majavrella.bloodfactory.base.BackButtonSupportFragment;
import com.majavrella.bloodfactory.base.UserFragment;

public class UserHomeFragment extends UserFragment implements BackButtonSupportFragment {

    private static View userHomeFragment;
    private static Button donate, recieve;
    private boolean consumingBackPress = true;
    private Toast toast;

    public static UserHomeFragment newInstance() {
        return new UserHomeFragment();
    }


    public UserHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userHomeFragment = inflater.inflate(R.layout.fragment_user_home, container, false);
        donate = (Button) userHomeFragment.findViewById(R.id.donateBtn);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonateFragment donateFragment = new DonateFragment();
               // mFragmentTransaction.replace(R.id.frag_container,donateFragment).addToBackStack(null).commit();


            }
        });
        recieve = (Button) userHomeFragment.findViewById(R.id.recieveBtn);
        recieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecieveFragment recieveFragment = new RecieveFragment();
                //mFragmentTransaction.replace(R.id.frag_container,recieveFragment).addToBackStack(null).commit();

            }
        });
        return userHomeFragment;
    }

    @Override
    protected String getTitle() {
        return "Home";
    }

    @Override
    public boolean onBackPressed() {
        if (consumingBackPress) {
            toast = Toast.makeText(getActivity(), "Press back again to exit !!!", Toast.LENGTH_LONG);
            toast.show();
            consumingBackPress = false;
            return true;
        }
        toast.cancel();
        return false;
    }

}

