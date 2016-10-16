package maarifa.tn.langui.ui.langageDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
public class AddGrammarFragment extends Fragment {

    EditText title;
    EditText content;
    FloatingActionButton tags;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_grammar, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title=(EditText) view.findViewById(R.id.title);
        content=(EditText)view.findViewById(R.id.content);
        tags=(FloatingActionButton)view.findViewById(R.id.tags);

        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CallBack)getActivity()).returToDetail(title.getText().toString(),content.getText().toString());
            }
        });
    }

    public interface CallBack{
        public void returToDetail(String title,String content);
    }
}
