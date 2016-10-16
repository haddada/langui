package maarifa.tn.langui.ui.langageDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import maarifa.tn.langui.R;

/**
 * Created by hamza on 24/05/2016.
 */
public class AddSynonymsFragment extends Fragment {

    EditText syn1;
    Button next;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_syn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        syn1=(EditText) view.findViewById(R.id.syn1);
        next=(Button)view.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Callback)getActivity()).openContext(syn1.getText().toString());
            }
        });

    }

    public interface Callback{
        public void openContext(String theWord);
    }
}
