/**
 * Copyright 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE-examples file in the root directory of this source tree.
 */

package com.github.pavlospt.litho_glide_component_sample.lithography;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

@LayoutSpec
public class FeedItemComponentSpec {

  @OnCreateLayout
  static Component onCreateLayout(ComponentContext c, @Prop final ArtistDatum artist,
      @Prop final RecyclerBinder binder) {
    return Column.create(c)
        .child(Column.create(c)
            .child(artist.getImages().length == 1 ? getImageComponent(c, artist)
                : getRecyclerComponent(c, binder))
            .child(TitleComponent.create(c).title(artist.getName()))
            .child(ActionsComponent.create(c)))
        .child(FooterComponent.create(c).text(artist.getBiography()))
        .build();
  }

  private static Component getImageComponent(ComponentContext c,
      ArtistDatum artistDatum) {
    String imageUrl = artistDatum.getImages()[0];
    return GlideSingleImageComponent.create(c).image(imageUrl).aspectRatio(2).build();
  }

  private static Component getRecyclerComponent(ComponentContext c,
      RecyclerBinder binder) {
    return Recycler.create(c).binder(binder).flexShrink(0).aspectRatio(2).build();
  }
}
