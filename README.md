# Litho - Glide
Litho Glide is a Litho compatible library, which provides an Image Component compatible with Glide. Litho-Glide supports a wide part of Glide's functionality. If you notice that anything is missing ping us :)

Inspiration: http://www.jayrambhia.com/blog/android-litho-gifs

## How to use

Add the following dependency on your app's `build.gradle`:
```
compile 'com.github.pavlospt:litho-glide:1.2'
```

Add GlideImage component on your own Component :) 

```java
GlideImage.create(c)
    .imageUrl(image)
    .aspectRatio(aspectRatio)
    .centerCrop(true)
    .buildWithLayout();
```

[Here](https://github.com/pavlospt/litho-glide/blob/master/app/src/main/java/com/github/pavlospt/litho_glide_component_sample/lithography/GlideSingleImageComponentSpec.java) you can find a sample ComponentSpec that uses GlideImage component.


## Other

Litho repository: https://github.com/facebook/litho

Litho documentation: http://fblitho.com/docs/getting-started

License
=======

    MIT License

    Copyright (c) 2017 Pavlos-Petros Tournaris & Vasilis Charalampakis

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
