package com.github.acanthite.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

class BitmapFontDeserializer extends Json.ReadOnlySerializer<BitmapFont> {
    private final FileHandle skinFile;
    private final FontGeneratorPool generatorPool;

    public BitmapFontDeserializer(FileHandle skinFile, FontGeneratorPool generatorPool) {
        if (skinFile == null) throw new IllegalArgumentException("skinFile == null");
        if (generatorPool == null) throw new IllegalArgumentException("generatorPool == null");
        this.skinFile = skinFile;
        this.generatorPool = generatorPool;
    }

    @Override
    public BitmapFont read(Json json, JsonValue jsonData, Class type) {
        String file = json.readValue("file", String.class, jsonData);
        FreeTypeFontParameter param = json.readValue(FreeTypeFontParameter.class, jsonData);

        return generatorPool.getFor(skinFile.sibling(file)).generateFont(param);
    }
}

