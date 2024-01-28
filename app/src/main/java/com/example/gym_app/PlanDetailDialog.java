package com.example.gym_app;

import static com.example.gym_app.TrainingActivity.TRAINING_KEY;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class PlanDetailDialog extends DialogFragment {

    public interface PassPlanInterface{
        void getPlan(Plan plan);
    }

    private PassPlanInterface passPlanInterface;

    private Button btnDismiss, btnAdd;
    private TextView txtName;
    private EditText edtTxtMinutes;
    private Spinner spinnerDay;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_plan_details, null);
        initViews(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Enter Details");

        Bundle bundle = getArguments();
        if(null != bundle){
            Training training = bundle.getParcelable(TRAINING_KEY);
            if(null != training){
                txtName.setText(training.getName());
                btnDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String day = spinnerDay.getSelectedItem().toString();
//                        int minutes = Integer.valueOf(edtTxtMinutes.getText().toString());
//                        Plan plan = new Plan(training, minutes, day, false);
//                        try {
//                            passPlanInterface = (PassPlanInterface) getActivity();
//                            passPlanInterface.getPlan(plan);
//                            dismiss();
//                        }catch (ClassCastException e){
//                            e.printStackTrace();
//                            dismiss();
//                        }

                        try {
                            int minutes = Integer.valueOf(edtTxtMinutes.getText().toString());
                            if(minutes<=0 || minutes > 60){
                                Toast.makeText(getContext(), "Input a number between 0 and 60", Toast.LENGTH_LONG).show();
                                dismiss();
                            }
                            else {
                                Plan plan = new Plan(training, minutes, day, false);
                                try {
                                    passPlanInterface = (PassPlanInterface) getActivity();
                                    passPlanInterface.getPlan(plan);
                                    dismiss();
                                } catch (ClassCastException e) {
                                    e.printStackTrace();
                                    dismiss();
                                }
                            }
                        }catch (NumberFormatException e){
                            Toast.makeText(getContext(), "Input a valid number", Toast.LENGTH_LONG).show();
                            dismiss();
                        }



                    }
                });
            }
        }

        return builder.create();
    }

    private void initViews(View view) {
        btnDismiss = view.findViewById(R.id.btnDismiss);
        btnAdd = view.findViewById(R.id.btnAdd);
        txtName = view. findViewById(R.id.txtTrainingName);
        edtTxtMinutes = view.findViewById(R.id.edtTxtMinutes);
        spinnerDay = view.findViewById(R.id.spinner);

    }
}
