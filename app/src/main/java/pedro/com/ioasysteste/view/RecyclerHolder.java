package pedro.com.ioasysteste.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pedro.com.ioasysteste.R;

public class RecyclerHolder extends RecyclerView.ViewHolder {

    public TextView pEnterpriseNameTextView;
    public TextView pEnterpriseTypeTextView;
    public TextView pEnterpriseCountryTextView;
    public ImageView pEnterprisePhotoImageView;
    public CardView pItemCardView;

    public RecyclerHolder(View itemView) {
        super(itemView);
        pEnterpriseNameTextView = itemView.findViewById(R.id.enterpriseNameId);
        pEnterpriseTypeTextView = itemView.findViewById(R.id.enterpriseTypeId);
        pEnterpriseCountryTextView = itemView.findViewById(R.id.countryEnterpriseId);
        pEnterprisePhotoImageView = itemView.findViewById(R.id.enterprisePhotoId);
        pItemCardView = itemView.findViewById(R.id.itemCardViewId);
    }
}
