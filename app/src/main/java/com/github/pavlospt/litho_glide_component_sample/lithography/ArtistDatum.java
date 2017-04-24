package com.github.pavlospt.litho_glide_component_sample.lithography;

import com.facebook.litho.Component;

interface ArtistDatum extends Datum {
  String[] getImages();

  String getBiography();

  String getName();
}
