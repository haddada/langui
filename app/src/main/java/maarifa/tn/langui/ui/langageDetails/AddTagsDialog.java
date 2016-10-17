package maarifa.tn.langui.ui.langageDetails;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import maarifa.tn.langui.R;

/**
 * Created by seif on 26/05/2016.
 */
public class AddTagsDialog extends DialogFragment {
    AutoCompleteTextView tagEdit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.fragment_dialog_addtag, null);
        tagEdit = (AutoCompleteTextView) rootView.findViewById(R.id.edit_tag);

        String[] tags = getResources().getStringArray(R.array.tags_table);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, tags);
        tagEdit.setAdapter(adapter);
        tagEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    ((CallBack)getActivity()).addTag(tagEdit.getText().toString());
                    AddTagsDialog.this.getDialog().cancel();
                }
                return true;
            }
        });
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton(R.string.positive_button_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ((CallBack)getActivity()).addTag(tagEdit.getText().toString());
                         AddTagsDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public interface CallBack{
        public void addTag(String tag);
    }
}
