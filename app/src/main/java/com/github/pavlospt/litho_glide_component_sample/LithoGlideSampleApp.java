package com.github.pavlospt.litho_glide_component_sample;

import android.app.Application;
import com.facebook.soloader.SoLoader;

public class LithoGlideSampleApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    SoLoader.init(this, false);
  }
}
