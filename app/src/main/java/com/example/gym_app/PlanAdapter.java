package com.example.gym_app;

import static com.example.gym_app.TrainingActivity.TRAINING_KEY;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder>{

    public interface RemovePlan{
        void onRemovePlanResult(Plan plan);
    }
    private RemovePlan removePlan;
    private ArrayList<Plan> plans = new ArrayList<>();
    private Context context;

    private String type = "";
    public PlanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtTrainingName.setText(plans.get(position).getTraining().getName());
        holder.txtTime.setText(String.valueOf(plans.get(position).getMinutes()));
        holder.txtDescription.setText(plans.get(position).getTraining().getShortDesc());
        Glide.with(context)
                .asBitmap()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.trainingImage);
        if(plans.get(position).isAccomplished()){
            holder.emptyCircle.setVisibility(View.GONE);
            holder.checkedCircle.setVisibility(View.VISIBLE);
        }else{
            holder.emptyCircle.setVisibility(View.VISIBLE);
            holder.checkedCircle.setVisibility(View.GONE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TrainingActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(TRAINING_KEY, plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });

        if(type.equals("edit")){
            holder.emptyCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Finished")
                            .setMessage("Have you finished " + plans.get(position).getTraining().getName() + "?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    for (Plan p: Utils.getPlans()){
                                        if (p.equals(plans.get(position))){
                                            p.setAccomplished(true);
                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            });
                    builder.create().show();
                }
            });
            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Remove")
                            .setMessage("Do you want to delete " + plans.get(position).getTraining().getName() + "from your plan?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                        try {
                                            removePlan = (RemovePlan) context;
                                            removePlan.onRemovePlanResult(plans.get(position));
                                        }catch (ClassCastException e) {
                                            e.printStackTrace();
                                        }
                                    }
                            });
                    builder.create().show();
                    return true;
                }
            });
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTrainingName, txtTime, txtDescription;
        private MaterialCardView parent;
        private ImageView emptyCircle, checkedCircle, trainingImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTrainingName = itemView.findViewById(R.id.txtName);
            txtTime = itemView.findViewById(R.id.txtMinutes);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            parent = itemView.findViewById(R.id.parent);

            emptyCircle = itemView.findViewById(R.id.emptyCircle);
            checkedCircle = itemView.findViewById(R.id.checkedCircle);
            trainingImage = itemView.findViewById(R.id.imgPlan);

        }
    }
}
