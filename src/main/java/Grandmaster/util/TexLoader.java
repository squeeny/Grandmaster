package Grandmaster.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;

import static basemod.abstracts.CustomCard.imgMap;
import static Grandmaster.grandmasterMod.makeCardPath;
import static Grandmaster.grandmasterMod.makeImagePath;

public class TexLoader {
    private static HashMap<String, Texture> textures = new HashMap<>();

    /**
     * @param textureString - String path to the texture you want to load relative to resources,
     *                      Example: makeImagePath("missing.png")
     * @return <b>com.badlogic.gdx.graphics.Texture</b> - The texture from the path provided
     */
    public static Texture getTexture(final String textureString) {
        if (textures.get(textureString) == null) {
            try {
                loadTexture(textureString, true);
            } catch (GdxRuntimeException e) {
                return getTexture(makeImagePath("ui/missing.png"));
            }
        }
        return textures.get(textureString);
    }

    public static String getAndLoadCardTextureString(final String cardName) {
        String textureString = makeCardPath(cardName + ".png");
        String originalString = textureString;
        if (textures.get(textureString) == null) {
            try { loadTexture(textureString);
            } catch (GdxRuntimeException e) {
                textureString = makeCardPath("Default.png");
                loadCardTexture(originalString, textureString, true);
            }
        }
        //no exception, file exists
        return originalString;
    }

    private static void loadCardTexture(final String textureKey, final String textureString, boolean linearFilter) throws GdxRuntimeException {
        if (!textures.containsKey(textureString))
        {
            Texture texture = new Texture(textureString);
            if (linearFilter) { texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); }
            else { texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest); }
            if (!imgMap.containsKey(textureKey)) { imgMap.put(textureKey, texture); }
            textures.put(textureKey, texture);
        } else {
            if (!imgMap.containsKey(textureKey)) { imgMap.put(textureKey, textures.get(textureString)); }
            textures.put(textureKey, textures.get(textureString));
        }
    }

    private static void loadTexture(final String textureString) throws GdxRuntimeException {
        loadTexture(textureString, false);
    }

    private static void loadTexture(final String textureString, boolean linearFilter) throws GdxRuntimeException {
        Texture texture = new Texture(textureString);
        if (linearFilter) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } else {
            texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }
        textures.put(textureString, texture);
    }

    public static boolean testTexture(String filePath) {
        return Gdx.files.internal(filePath).exists();
    }

    public static TextureAtlas.AtlasRegion getTextureAsAtlasRegion(String textureString) {
        Texture texture = getTexture(textureString);
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}