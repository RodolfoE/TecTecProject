package com.example.rodolfo.teste;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Rodolfo on 02/06/2015.
 */
public class fragmentTest extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag1, null);
        TextView tx = (TextView) v.findViewById(R.id.tx1);
        tx.setText("This is the \n3th fragment, dude");
        return v;
    }
}
