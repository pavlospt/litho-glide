package com.github.pavlospt.litho.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Output;
import com.facebook.litho.Size;
import com.facebook.litho.annotations.FromBoundsDefined;
import com.facebook.litho.annotations.MountSpec;
import com.facebook.litho.annotations.OnBoundsDefined;
import com.facebook.litho.annotations.OnCreateMountContent;
import com.facebook.litho.annotations.OnMeasure;
import com.facebook.litho.annotations.OnMount;
import com.facebook.litho.annotations.OnUnmount;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.annotations.ResType;
import com.facebook.litho.utils.MeasureUtils;

import java.io.File;

import static com.facebook.litho.annotations.ResType.DRAWABLE;

@MountSpec
public class GlideImageSpec {

  private static final int DEFAULT_INT_VALUE = -1;
  private static final int TYPE_BITMAP = 1;
  private static final int TYPE_DRAWABLE = 2;

  @PropDefault
  protected static final float imageAspectRatio = 1f;

  @PropDefault
  protected static final int crossFadeDuration = DEFAULT_INT_VALUE;
//  private static final String TAG = "glide_li";

  @OnMeasure
  static void onMeasureLayout(ComponentContext c, ComponentLayout layout, int widthSpec,
                              int heightSpec, Size size,
                              @Prop(optional = true, resType = ResType.FLOAT) float imageAspectRatio) {
    MeasureUtils.measureWithAspectRatio(widthSpec, heightSpec, imageAspectRatio, size);
//    Log.d(TAG, "onMeasureLayout: width:" + size.width + ", height:" + size.height + ", aspectRatio:" + imageAspectRatio);
  }

  @OnCreateMountContent
  static ImageView onCreateMountContent(Context c) {
    return new ImageView(c);
  }

  @OnBoundsDefined
  static void onBoundsDefined(
          ComponentContext c,
          ComponentLayout layout,
          Output<Integer> width,
          Output<Integer> height
  ) {
    width.set(layout.getWidth() - (layout.getPaddingLeft() + layout.getPaddingRight()));
    height.set(layout.getHeight() - (layout.getPaddingTop() + layout.getPaddingBottom()));
  }


  static void applyCrossFade(RequestBuilder<?> request, int type, boolean crossFade, int duration) {
    if (!crossFade) {
      return;
    }
    if (duration == DEFAULT_INT_VALUE) {
      duration = 300;//default duration
    }
    switch (type) {
      case TYPE_BITMAP:
        ((RequestBuilder<Drawable>)request).transition(DrawableTransitionOptions.withCrossFade(duration));
        break;
      case TYPE_DRAWABLE:
        ((RequestBuilder<Bitmap>)request).transition(BitmapTransitionOptions.withCrossFade(duration));
        break;
    }
  }

  @OnMount
  static void onMount(ComponentContext c, ImageView imageView,
                      @Prop(optional = true) String imageUrl, @Prop(optional = true) File file,
                      @Prop(optional = true) Uri uri, @Prop(optional = true) Integer resourceId,
                      @Prop(optional = true) RequestManager glideRequestManager,
                      @Prop(optional = true, resType = DRAWABLE) Drawable failureImage,
                      @Prop(optional = true, resType = DRAWABLE) Drawable fallbackImage,
                      @Prop(optional = true, resType = DRAWABLE) Drawable placeholderImage,
                      @Prop(optional = true) DiskCacheStrategy diskCacheStrategy,
                      @Prop(optional = true) RequestListener requestListener,
                      @Prop(optional = true) boolean asBitmap, @Prop(optional = true) boolean asGif,
                      @Prop(optional = true) boolean crossFade, @Prop(optional = true) int crossFadeDuration,
                      @Prop(optional = true) boolean centerCrop, @Prop(optional = true) boolean fitCenter,
                      @Prop(optional = true) boolean skipMemoryCache, @Prop(optional = true) Target target,
                      @FromBoundsDefined Integer width,
                      @FromBoundsDefined Integer height
                      ) {

    if (imageUrl == null && file == null && uri == null && resourceId == null) {
      throw new IllegalArgumentException(
          "You must provide at least one of String, File, Uri or ResourceId");
    }

    if (glideRequestManager == null) {
      glideRequestManager = Glide.with(c.getAndroidContext());
    }

    RequestBuilder<?> request;

    if (asBitmap) {
      request = glideRequestManager.asBitmap();
      applyCrossFade(request, TYPE_BITMAP, crossFade,crossFadeDuration);
    } else if (asGif) {
      request = glideRequestManager.asGif();
    }else {
      request = glideRequestManager.asDrawable();
      applyCrossFade(request, TYPE_DRAWABLE, crossFade,crossFadeDuration);
    }

    if (imageUrl != null) {
      request = request.load(imageUrl);
    } else if (file != null) {
      request = request.load(file);
    } else if (uri != null) {
      request = request.load(uri);
    } else {
      request = request.load(resourceId);
    }

    if (request == null) {
      throw new IllegalStateException("Could not instantiate DrawableTypeRequest");
    }

    if (diskCacheStrategy != null) {
      request = request.diskCacheStrategy(diskCacheStrategy);
    }

    if (centerCrop) {
      request = request.centerCrop();
    }

    if (failureImage != null) {
      request = request.error(failureImage);
    }

    if (fallbackImage != null) {
      request = request.fallback(fallbackImage);
    }

    if (fitCenter) {
      request = request.fitCenter();
    }

    if (requestListener != null) {
      request = request.listener(requestListener);
    }

    if (placeholderImage != null) {
      request = request.placeholder(placeholderImage);
    }

    request = request.skipMemoryCache(skipMemoryCache);

//    Log.d(TAG, "onMount: width:" + width + ", height:" + height);
    if (width != null && height != null) {
      request = request.override(width, height);
    }

    if (target != null) {
      request.into(target);
    } else {
      request.into(imageView);
    }
  }

  @OnUnmount
  static void onUnmount(ComponentContext c, ImageView imageView) {
    Glide.with(c.getAndroidContext()).clear(imageView);
  }
}
