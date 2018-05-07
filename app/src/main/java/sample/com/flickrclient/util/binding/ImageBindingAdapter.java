package sample.com.flickrclient.util.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import sample.com.flickrclient.util.view.CircleTransform;


public class ImageBindingAdapter {

    public enum ImageShape {
        CIRCLE
    }

    @BindingAdapter(value = {"imageUrl", "placeHolder", "imageShape"}, requireAll = false)
    public static void loadImage(ImageView imageView,
                                 String imageUrl,
                                 Drawable placeHolder,
                                 ImageShape shape) {

        if (imageUrl != null && !imageUrl.isEmpty()) {
            RequestCreator requestCreator = Picasso.with(imageView.getContext())
                    .load(imageUrl)
                    .placeholder(placeHolder)
                    .centerCrop()
                    .fit();

            if (shape != null) {
                switch (shape) {
                    case CIRCLE:
                        requestCreator.transform(new CircleTransform());
                        break;
                    default:
                        break;
                }
            }
            requestCreator.into(imageView);
        }
    }

    @BindingAdapter(value = {"url"})
    public static void loadOriginalImage(ImageView imageView, String imageUrl) {

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.with(imageView.getContext())
                    .load(imageUrl)
                    .into(imageView);
        }
    }
}
