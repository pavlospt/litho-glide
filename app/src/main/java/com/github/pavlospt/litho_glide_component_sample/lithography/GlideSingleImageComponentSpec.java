package com.github.pavlospt.litho_glide_component_sample.lithography;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.github.pavlospt.litho.glide.GlideImage;

@LayoutSpec
public class GlideSingleImageComponentSpec {

  @PropDefault
  protected static final float imageAspectRatio = 1f;

  @OnCreateLayout
  static Component onCreateLayout(
      ComponentContext c,
      @Prop String image,
      @Prop(optional = true) float imageAspectRatio) {
    return GlideImage.create(c)
        .imageUrl(image)
        .aspectRatio(imageAspectRatio)
        .centerCrop(true)
        .build();
  }
}
