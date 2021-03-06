/* Jeremy Holloway
 * CPSC-4150-001
 * 9/24/2019
 * HW2
 * C20581376
 * jjhollo@g.clemson.edu
 */
package com.example.pigdice;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogFragment1 extends DialogFragment {

    /**
     * This is the interface for the fragment listener
     */
    public interface DialogFragmentListener {
        void onDialogPositiveClick(DialogFragment1 dialogFragment);
        void onDialogNegativeClick(DialogFragment1 dialogFragment);
    }

    //set score and getter function
    private int score = 0;
    public int getScore(){
        return score;
    }

    DialogFragmentListener listener;

    /**
     * This function overrides the onAttach method
     * @pre DialogFragmentListener must be declared
     * @post the listener can send events or an exception is thrown
     * @param context = checks for the callback implementation
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (DialogFragmentListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }

    /**
     * This function orrides the onCreateDialog method
     * @pre dialog must be called from another activity
     * @post score is set to the value entered in the editText field
     * @param savedInstanceState = the saved instance variables
     * @return the created dialog is returned
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.activity_dialog_fragment1);
        builder.setMessage(R.string.target);
        builder.setPositiveButton(R.string.set_target, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText = getDialog().findViewById(R.id.target_field);
                score = Integer.parseInt(editText.getText().toString());
                listener.onDialogPositiveClick(DialogFragment1.this);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onDialogNegativeClick(DialogFragment1.this);
            }
        });
        return builder.create();
    }
}
