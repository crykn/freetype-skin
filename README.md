# LibGDX freetype skin

This library extends LibGDX asset management with [`FreeTypeSkin`](src/main/java/com/github/acanthite/gdx/graphics/g2d/FreeTypeSkin.java) and [`FreeTypeSkinLoader`](src/main/java/com/github/acanthite/gdx/graphics/g2d/FreeTypeSkinLoader.java) classes, which allow you to specify TTF fronts right in the `uiskin.json` file. You don't need to pack bitmap fonts manually anymore, or to specify them [via skin parameter](https://stackoverflow.com/a/39174630/13202054).
Just drop some `.ttf` into your assets folder, put some parameters and load it.

## Usage
You need to create an instance of `FreeTypeSkin` and pass it to where you needed `Skin`, just like you would do this with standard `Skin`. 

If you want to load via `AssetManager`, just register `FreeTypeSkinLoader` as a loader for `Skin.class`. Load them like you would do it normally.

Here is a quick example. Put a fonts section into your `uiskin.json`:
```libgdxjson
{
  com.badlogic.gdx.graphics.g2d.BitmapFont: {
    default: {
      file: myfont.ttf,
      size: 14
    }
    small: {
      file: myfont.ttf,
      size: 10
    }
    title: {
      file: myfont.ttf,
      size: 20
    }
  }
  // some other styles ...
}
```
And load it:
```java
Skin skin = new FreeTypeSkin(Gdx.files.internal("assets/uiskin.json"));
```
It's that simple. Just what we've been dreaming about :D

**Note**: the `skin` variable is defined as `Skin` not `FreeTypeSkin`. That is, `FreeTypeSkin` is a `Skin` (e.g. extends it), and you can freely pass it where `Skin` is required. It will work just as you expect.

### With `AssetManager`
To use it with `AssetManager` you will need to replace the default asset loader for `Skin.class`. Then just do it as usual:
```java
AssetManager manager = new AssetManager();

// replace the builtin skin loader with FreeTypeSkinLoader
manager.setLoader(Skin.class, new FreeTypeSkinLoader());

// queue skin loading
manager.load(Skin.class, "assets/uiskin.json");
manager.finishLoading();

// and use it
Skin skin = manager.get("assets/uiskin.json", Skin.class);
```
## Font (FreeType) parameters

When you define bitmap fonts in a skin file, you _must_ specify TTF font via `file` property _and_ font size via `size` property. Those are the required minimum.

Other than that, you can specify almost any other property from [FreeTypeFontParameter](https://github.com/libgdx/libgdx/blob/48877d97317ca8063b9bf4479d3c253db417677a/extensions/gdx-freetype/src/com/badlogic/gdx/graphics/g2d/freetype/FreeTypeFontGenerator.java#L740) class, except the [`packer`](https://github.com/libgdx/libgdx/blob/48877d97317ca8063b9bf4479d3c253db417677a/extensions/gdx-freetype/src/com/badlogic/gdx/graphics/g2d/freetype/FreeTypeFontGenerator.java#L778) and the [`incremental`](https://github.com/libgdx/libgdx/blob/48877d97317ca8063b9bf4479d3c253db417677a/extensions/gdx-freetype/src/com/badlogic/gdx/graphics/g2d/freetype/FreeTypeFontGenerator.java#L792) properties.

All the omitted properties will simply default to their value as specified in `FreeTypeFontParameter`.

Here is an example using some other properties:
```libgdxjson
{
  com.badlogic.gdx.graphics.g2d.BitmapFont: {
    title: {
      // these two are required
      file: myfont.ttf,
      size: 36,

      borderColor: { a: 1, b: 0, g: 0, r: 0 },
      borderWidth: 2,
      shadowColor: { a: 1, b: 1, g: 1, r: 1 },
      shadowOffsetX: 3,
      shadowOffsetY: 3,
    }
  }
}
```

## Vis UI Support?
Yes! It works out of the box. As it was said earlier, `FreeTypeSkin` is a `Skin`. So that can use it in any place where `Skin` is needed. It is indeed, that simple:
```java
FileHandle skinFile = Gdx.files.internal("assets/uiskin.json");
VisUI.load(new FreeTypeSkin(skinFile));
```
