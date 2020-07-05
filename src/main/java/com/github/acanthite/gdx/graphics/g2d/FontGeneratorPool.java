package com.github.acanthite.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

class FontGeneratorPool implements Disposable {
    private final ObjectMap<FileHandle, FreeTypeFontGenerator> pool = new ObjectMap<>();

    public FreeTypeFontGenerator getFor(FileHandle ttfFile) {
        FreeTypeFontGenerator generator = pool.get(ttfFile);
        if (generator == null) {
            generator = new FreeTypeFontGenerator(ttfFile);
            pool.put(ttfFile, generator);
        }
        return generator;
    }

    @Override
    public void dispose() {
        for (FreeTypeFontGenerator generator : pool.values()) {
            generator.dispose();
        }

        pool.clear();
    }
}
