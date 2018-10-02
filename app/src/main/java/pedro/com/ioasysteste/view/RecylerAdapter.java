package pedro.com.ioasysteste.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import pedro.com.ioasysteste.R;
import pedro.com.ioasysteste.controllers.ApiController;
import pedro.com.ioasysteste.view.connector.BindWithActivities;
import pedro.com.ioasysteste.models.data.Enterprise;

public class RecylerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private List<Enterprise> mEnterprises;
    private Context mContext;

    public RecylerAdapter(List<Enterprise> Enterprises, Context context) {
        this.mEnterprises = Enterprises;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.enterprises_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int position) {

        final Enterprise enterprise = mEnterprises.get(position);
        holder.pEnterpriseNameTextView.setText(enterprise.getEnterpriseName());
        holder.pEnterpriseTypeTextView.setText(enterprise.getEnterpriseType().getEnterpriseTypeName());
        holder.pEnterpriseCountryTextView.setText(enterprise.getCountry());

        if (enterprise.getPhoto() != null
                && (!enterprise.getPhoto().toString().equals(""))) {

            Glide.with(mContext)
                    .load(ApiController.BASE_URL + enterprise.getPhoto().toString())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.download)
                    .into(holder.pEnterprisePhotoImageView);

        } else {
            holder.pEnterprisePhotoImageView.setImageDrawable(
                    mContext.getDrawable(
                            R.drawable.imageReport
                    )
            );
        }

        holder.pItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BindWithActivities bind = new BindWithActivities(mContext);
                bind.toAboutActivity(enterprise);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEnterprises != null ? mEnterprises.size() : 0;
    }
}
