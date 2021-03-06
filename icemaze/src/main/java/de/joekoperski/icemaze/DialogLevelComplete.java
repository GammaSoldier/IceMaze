package de.joekoperski.icemaze;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DialogLevelComplete extends Dialog {

    DialogLevelCompleteListener mListener;
    Context mContext;
    boolean mFinalLevel;

    public interface DialogLevelCompleteListener {
        void onDialogNextLevelClick(Dialog dialog);
        void onDialogSelectLevelClick(Dialog dialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public DialogLevelComplete(@NonNull Context context) {
        super(context);
        mContext = context;
        mFinalLevel = false;
    }// DialogLevelComplete


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void show(boolean finalLevel) {
        show();
        if( finalLevel) {
            findViewById(R.id.button_next_level).setVisibility(View.INVISIBLE);
            ((TextView)findViewById(R.id.textViewLevelComplete)).setText(R.string.finalLevelDone);
        }// if
    }// show


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_level_complete);

        try {
            mListener = (DialogLevelCompleteListener)mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException( mContext.toString() + "must implement DialogLevelCompleteListener");
        }// catch


        Button buttonSelect = findViewById(R.id.button_select_level);
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDialogSelectLevelClick(DialogLevelComplete.this);
            }// onClick
        });



        Button buttonNext = findViewById(R.id.button_next_level);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDialogNextLevelClick(DialogLevelComplete.this);
            }// onClick
        });





    }// onCreate



}
