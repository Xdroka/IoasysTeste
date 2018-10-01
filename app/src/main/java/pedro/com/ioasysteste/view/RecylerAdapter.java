package pedro.com.ioasysteste.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import pedro.com.ioasysteste.R;
import pedro.com.ioasysteste.controllers.ApiController;
import pedro.com.ioasysteste.controllers.connector.BindWithActivities;
import pedro.com.ioasysteste.models.data.Enterprise;

public class RecylerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private List<Enterprise> mEnterprises;

    public RecylerAdapter(List<Enterprise> Enterprises) {
        this.mEnterprises = Enterprises;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.enterprises_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int position) {

        holder.pEnterpriseNameTextView.setText(mEnterprises.get(position).getEnterpriseName());
        holder.pEnterpriseTypeTextView.setText(mEnterprises.get(position).getEnterpriseType().getEnterpriseTypeName());
        holder.pEnterpriseCountryTextView.setText(mEnterprises.get(position).getCountry());

        if (mEnterprises.get(position).getPhoto() != null
                && (!mEnterprises.get(position).getPhoto().toString().equals(""))) {

            Target<GlideDrawable> glide = Glide.with(BindWithActivities.sContext)
                    .load(ApiController.BASE_URL + mEnterprises.get(position).getPhoto().toString())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.download)
                    .into(holder.pEnterprisePhotoImageView);
        } else {
            holder.pEnterprisePhotoImageView.setImageDrawable(
                    BindWithActivities.sContext.getDrawable(
                            R.drawable.imageReport
                    )
            );
        }

        holder.pItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BindWithActivities.toAboutActivity(mEnterprises.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEnterprises != null ? mEnterprises.size() : 0;
    }
}
