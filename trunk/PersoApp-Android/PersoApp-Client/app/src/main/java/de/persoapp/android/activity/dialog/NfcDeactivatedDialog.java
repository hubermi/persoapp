package de.persoapp.android.activity.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.view.View;

import net.vrallev.android.base.BaseActivitySupport;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.activity.fragment.InitializeAppFragment;

/**
 * @author Ralf Wondratschek
 */
public class NfcDeactivatedDialog extends DialogFragment {

    @Inject
    NfcManager mNfcManager;

    @Inject
    EventBus mEventBus;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivitySupport) activity).inject(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        getActivity().finish();
                        break;
                }
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.nfc_deactivated)
                .setMessage(R.string.nfc_deactivated_message)
                .setPositiveButton(R.string.settings, null)
                .setNegativeButton(android.R.string.cancel, onClickListener)
                .create();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onResume() {
        super.onResume();

        if (mNfcManager.getDefaultAdapter().isEnabled()) {
            dismiss();
            mEventBus.post(new InitializeAppFragment.OnAppInitialized(true));

        } else {
            AlertDialog dialog = (AlertDialog) getDialog();

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                }
            });
        }
    }
}
