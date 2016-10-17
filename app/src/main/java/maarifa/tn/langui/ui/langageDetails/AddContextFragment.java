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
 * Created by seif on 24/05/2016.
 */
public class AddContextFragment extends Fragment {

    EditText context;
    EditText sentence;
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
        return inflater.inflate(R.layout.fragment_add_context, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=(EditText) view.findViewById(R.id.context);
        sentence=(EditText)view.findViewById(R.id.sentence);
        next=(Button)view.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Callback)getActivity()).openVocablary(context.getText().toString(),sentence.getText().toString());
            }
        });

    }

    public interface Callback{
        public void openVocablary(String context,String sentence);
    }
}
