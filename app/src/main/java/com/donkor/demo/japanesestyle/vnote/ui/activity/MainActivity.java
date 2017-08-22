package com.donkor.demo.japanesestyle.vnote.ui.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.donkor.demo.japanesestyle.vnote.R;
import com.donkor.demo.japanesestyle.vnote.ui.fragment.GroupFragment;
import com.donkor.demo.japanesestyle.vnote.ui.fragment.MineFragment;
import com.donkor.demo.japanesestyle.vnote.ui.fragment.NoteFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {
    @BindView(R.id.tvUserame)
    TextView mTvUserame;
    @BindView(R.id.ivNote)
    ImageView mIvNote;
    @BindView(R.id.tvNote)
    TextView mTvNote;
    @BindView(R.id.ivGroup)
    ImageView mIvGroup;
    @BindView(R.id.tvGroup)
    TextView mTvGroup;
    @BindView(R.id.ivMine)
    ImageView mIvMine;
    @BindView(R.id.tvMine)
    TextView mTvMine;
    private FragmentTransaction ft;
    private MineFragment mMineFragment;
    private GroupFragment mGroupFragment;
    private NoteFragment mNoteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMineFragment = new MineFragment();
        mGroupFragment = new GroupFragment();
        mNoteFragment = new NoteFragment();

        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.linContent, mMineFragment);
        ft.commit();
    }

    @OnClick({R.id.linMine, R.id.linNote, R.id.linGroup})
    public void onViewClicked(View view) {
        ft = getFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.linNote:
                ft.replace(R.id.linContent, mNoteFragment);
                mTvNote.setTextColor(getResources().getColor(R.color.commBlue));
                mTvGroup.setTextColor(getResources().getColor(R.color.commfalse));
                mTvMine.setTextColor(getResources().getColor(R.color.commfalse));
                mIvNote.setImageResource(R.mipmap.bottom_lesson_focus);
                mIvGroup.setImageResource(R.mipmap.bottom_class);
                mIvMine.setImageResource(R.mipmap.bottom_mine);
                break;
            case R.id.linGroup:
                ft.replace(R.id.linContent, mGroupFragment);
                mTvNote.setTextColor(getResources().getColor(R.color.commfalse));
                mTvGroup.setTextColor(getResources().getColor(R.color.commBlue));
                mTvMine.setTextColor(getResources().getColor(R.color.commfalse));
                mIvNote.setImageResource(R.mipmap.bottom_lesson);
                mIvGroup.setImageResource(R.mipmap.bottom_class_focus);
                mIvMine.setImageResource(R.mipmap.bottom_mine);
                break;
            case R.id.linMine:
                ft.replace(R.id.linContent, mMineFragment);
                mTvNote.setTextColor(getResources().getColor(R.color.commfalse));
                mTvGroup.setTextColor(getResources().getColor(R.color.commfalse));
                mTvMine.setTextColor(getResources().getColor(R.color.commBlue));
                mIvNote.setImageResource(R.mipmap.bottom_lesson);
                mIvGroup.setImageResource(R.mipmap.bottom_class);
                mIvMine.setImageResource(R.mipmap.bottom_mine_focus);
                break;
        }
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
