package com.example.tresenraya;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;

import java.lang.reflect.Field;

import pl.droidsonroids.gif.GifImageView;


public class Comingsoon extends DialogFragment {

    private GifImageView mGifImageView;
    private ImageView mCloseImageView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                dismiss();
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comingsoon, container, false);
        mGifImageView = view.findViewById(R.id.gif_image_view);
        mCloseImageView = view.findViewById(R.id.close_image_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String gifUrl = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/c4074ccc-1fbc-453c-aeb7-f985787f5d59/de69j6b-9198d5da-87d6-4a51-9932-5b361e0381a9.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2M0MDc0Y2NjLTFmYmMtNDUzYy1hZWI3LWY5ODU3ODdmNWQ1OVwvZGU2OWo2Yi05MTk4ZDVkYS04N2Q2LTRhNTEtOTkzMi01YjM2MWUwMzgxYTkuZ2lmIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.o-V_NkIqnEd81GutbfELm0TjSCpWMSrR3Js1tiXuvmE";
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(requireContext())
                .load(gifUrl)
                .apply(options)
                .into(mGifImageView);

        mCloseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mGifImageView != null) {
            try {
                GifDrawable gifDrawable = (GifDrawable) mGifImageView.getDrawable();
                Field stateField = GifDrawable.class.getDeclaredField("mState");
                stateField.setAccessible(true);
                Object state = stateField.get(gifDrawable);
                Field isRecycledField = state.getClass().getDeclaredField("isRecycled");
                isRecycledField.setAccessible(true);
                boolean isRecycled = isRecycledField.getBoolean(state);
                if (!isRecycled) {
                    gifDrawable.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}