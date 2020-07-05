package com.github.acanthite.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;

public class FreeTypeSkin extends Skin {
    private FontGeneratorPool fontGeneratorPool;

    public FreeTypeSkin() {
    }

    public FreeTypeSkin(FileHandle skinFile) {
        super(skinFile);
    }

    public FreeTypeSkin(FileHandle skinFile, TextureAtlas atlas) {
        super(skinFile, atlas);
    }

    public FreeTypeSkin(TextureAtlas atlas) {
        super(atlas);
    }

    @Override
    public void load(FileHandle skinFile) {
        fontGeneratorPool = new FontGeneratorPool();
        super.load(skinFile);
        fontGeneratorPool.dispose();
    }

    @Override
    protected Json getJsonLoader(FileHandle skinFile) {
        Json json = super.getJsonLoader(skinFile);
        json.setSerializer(FreeTypeFontParameter.class, new FreeTypeFontParametersDeserializer());
        json.setSerializer(BitmapFont.class, new BitmapFontDeserializer(skinFile, fontGeneratorPool));
        return json;
    }

    @Override
    public void dispose() {
        if (fontGeneratorPool != null) {
            fontGeneratorPool.dispose();
        }
        super.dispose();
    }
}