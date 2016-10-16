package maarifa.tn.langui.ui.langageDetails;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import maarifa.tn.langui.R;
import maarifa.tn.langui.utils.Constants;

/**
 * Created by hamza on 24/05/2016.
 */
public class AddWordFragment extends Fragment {


    EditText theWord;
    Button next;
    String word;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_word1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        theWord=(EditText) view.findViewById(R.id.the_word);
        next=(Button)view.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Callback)getActivity()).openSyn(theWord.getText().toString());
            }
        });

    }

    public interface Callback{
        public void openSyn(String theWord);
    }
}
