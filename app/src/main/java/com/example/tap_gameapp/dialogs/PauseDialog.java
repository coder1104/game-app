package com.example.tap_gameapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import com.example.tap_gameapp.R;
import com.example.tap_gameapp.util.ActionListener;

public class PauseDialog extends Dialog {

    private ActionListener listener;

    public PauseDialog(@NonNull Context context, ActionListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pause_dilogue);


        View no = findViewById(R.id.btn_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    dismiss();
                    listener.onNegativeAction();
                }

            }
        });
        View yes = findViewById(R.id.btn_yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onPositiveAction();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
