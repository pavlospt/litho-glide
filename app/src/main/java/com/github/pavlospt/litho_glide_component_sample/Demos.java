/**
 * Copyright 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE-examples file in the root directory of this source tree.
 */

package com.github.pavlospt.litho_glide_component_sample;

import android.content.Context;
import android.support.v7.widget.OrientationHelper;

import com.github.pavlospt.litho_glide_component_sample.lithography.DataModel;
import com.github.pavlospt.litho_glide_component_sample.lithography.LithographyRootComponent;
import com.github.pavlospt.litho_glide_component_sample.playground.PlaygroundComponent;
import java.util.Map;
import java.util.LinkedHashMap;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.RecyclerBinder;

/**
 * The list of Litho demos -- Add your demos below!
 */
public final class Demos {

  private static Map<String, Component<?>> demoModels;

  private Demos() {
  }

  public static void initialize(Context context) {
    final ComponentContext c = new ComponentContext(context);
    final RecyclerBinder glideRecyclerBinder = new RecyclerBinder(
        c,
        4.0f,
        new LinearLayoutInfo(c, OrientationHelper.VERTICAL, false));
    DataModel.populateBinderWithSampleDataForGlide(glideRecyclerBinder, c);

    demoModels = new LinkedHashMap<>();
    demoModels.put(
        "Lithography - Glide",
        LithographyRootComponent.create(c)
            .recyclerBinder(glideRecyclerBinder)
            .build());
    demoModels.put("Playground", PlaygroundComponent.create(c).build());
  }

  public static Component<?> getComponent(String name) {
    return demoModels.get(name);
  }

  public static void addAllToBinder(RecyclerBinder recyclerBinder, ComponentContext c) {
    for (String name : demoModels.keySet()) {
      ComponentInfo.Builder componentInfoBuilder = ComponentInfo.create();
      componentInfoBuilder.component(
          DemoListItemComponent.create(c)
              .name(name)
              .build());
      recyclerBinder.insertItemAt(recyclerBinder.getItemCount(), componentInfoBuilder.build());
    }
  }
}
