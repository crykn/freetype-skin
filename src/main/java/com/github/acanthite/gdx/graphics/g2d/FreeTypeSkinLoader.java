package com.github.acanthite.gdx.graphics.g2d;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Skin loader that supports generating {@link BitmapFont}s from TFF via {@link FreeTypeFontGenerator}
 *
 * @see FreeTypeSkin
 * @author acanthite
 */
public class FreeTypeSkinLoader extends SkinLoader {
    public FreeTypeSkinLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    protected Skin newSkin(TextureAtlas atlas) {
        return new FreeTypeSkin(atlas);
    }
}
