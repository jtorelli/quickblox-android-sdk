package com.quickblox.sample.groupchatwebrtc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.quickblox.sample.core.utils.UiUtils;
import com.quickblox.sample.groupchatwebrtc.R;
import com.quickblox.sample.groupchatwebrtc.utils.CollectionsUtils;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;

/**
 * Created by tereha on 25.05.16.
 */
public class AudioConversationFragment extends BaseConversationFragment {
    private static final String TAG = AudioConversationFragment.class.getSimpleName();
    private ImageView firstOpponentAvatarImageView;
    private TextView firsrOpponentNameTextView;

    private ToggleButton audioSwichToggleButton;
    private TextView otherOpponentsTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void configureOutgoingScreen() {
        outgoingOpponentsRelativeLayout.setBackgroundColor(getResources().getColor(R.color.white));
        allOpponentsTextView.setTextColor(getResources().getColor(R.color.text_color_outgoing_opponents_names_audio_call));
        ringingTextView.setTextColor(getResources().getColor(R.color.text_color_call_type));
    }

    @Override
    protected void configureToolbar() {
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbar_title_color));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.toolbar_subtitle_color));
    }

    @Override
    protected void configureActionBar() {
        actionBar.setTitle(currentUser.getTags().get(0));
        actionBar.setSubtitle(String.format(getString(R.string.logged_in_as), currentUser.getFullName()));
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        timerChronometer = (Chronometer) view.findViewById(R.id.chronometer_timer_audio_call);

        firstOpponentAvatarImageView = (ImageView) view.findViewById(R.id.image_caller_avatar);
        firstOpponentAvatarImageView.setBackgroundDrawable(UiUtils.getColorCircleDrawable(opponents.get(0).getId()));

        firsrOpponentNameTextView = (TextView) view.findViewById(R.id.text_caller_name);
        firsrOpponentNameTextView.setText(opponents.get(0).getFullName());

        otherOpponentsTextView = (TextView) view.findViewById(R.id.text_other_inc_users);
        otherOpponentsTextView.setText(getOtherOpponentsNames());

        audioSwichToggleButton = (ToggleButton) view.findViewById(R.id.speakerToggle);
        audioSwichToggleButton.setVisibility(View.VISIBLE);

        actionButtonsEnabled(false);
    }

    private String getOtherOpponentsNames() {
        ArrayList<QBUser> otherOpponents = opponents;
        otherOpponents.remove(0);

        return CollectionsUtils.makeStringFromUsersFullNames(otherOpponents);
    }


    @Override
    protected void initButtonsListener() {
        super.initButtonsListener();

        audioSwichToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conversationFragmentCallbackListener.onSwitchAudio();
            }
        });
    }

    @Override
    protected void actionButtonsEnabled(boolean enability) {
        super.actionButtonsEnabled(enability);

        audioSwichToggleButton.setEnabled(enability);
        audioSwichToggleButton.setActivated(enability);
    }

    @Override
    int getFragmentLayout() {
        return R.layout.fragment_audio_conversation;
    }
}