package com.majavrella.bloodinformer.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.majavrella.bloodinformer.R;
import com.majavrella.bloodinformer.base.Constants;
import com.majavrella.bloodinformer.base.UserFragment;
import com.majavrella.bloodinformer.register.RegisterConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraSettingsFragment extends UserFragment {

    private static View mExtraSettingsView;
    @Bind(R.id.change_mob) LinearLayout mChangeMob;
    @Bind(R.id.edit_profile) LinearLayout mEditProfile;
    @Bind(R.id.change_password) LinearLayout mChangePassword;
    @Bind(R.id.members_list) LinearLayout mMembersList;
    @Bind(R.id.posted_blood_request) LinearLayout mPostedBloodRequest;
    @Bind(R.id.app_version) TextView mAppVersion;
    @Bind(R.id.app_developer) TextView mAppDeveloper;

    public static ExtraSettingsFragment newInstance() {
        return new ExtraSettingsFragment();
    }

    public ExtraSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mExtraSettingsView = inflater.inflate(R.layout.fragment_extra_settings, container, false);
        ButterKnife.bind(this, mExtraSettingsView);

        mChangeMob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(getActivity());
                if(isNetworkAvailable()){
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    ChangeMobile changeMobile = new ChangeMobile();
                    changeMobile.show(manager, "change_mobile");
                } else {
                    showSnackbar(mExtraSettingsView, RegisterConstants.networkErrorText);
                }
            }
        });
        mEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(getActivity());
                add(EditProfileFragment.newInstance());
            }
        });

        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(getActivity());
                add(ChangePasswordFragment.newInstance());
            }
        });

        mMembersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(AddedMembers.newInstance());
            }
        });

        mPostedBloodRequest.setOnClickListener(mPostedBloodRequestListener);

        mAppVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(VersionFragment.newInstance());
            }
        });

        mAppDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(DeveloperFragment.newInstance());
            }
        });

        return mExtraSettingsView;
    }

    private View.OnClickListener mPostedBloodRequestListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            add(PostedRequests.newInstance());
        }
    };

    @Override
    public void onResume() {
        hideKeyboard(getActivity());
        super.onResume();
    }

    @Override
    protected String getTitle() {
        return Constants.kExtraSettingsFragment;
    }
}