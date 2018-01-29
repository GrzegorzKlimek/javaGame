package com.mygdx.game.Tools;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Sprites.agents.enemies.DarkKnight;
import com.mygdx.game.Sprites.agents.enemies.Enemy;
import com.mygdx.game.screens.PlayScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzegorz on 29.01.18.
 */

public class NPCgenerator {

    public List<Enemy> generate(PlayScreen screen, List<Pair<Enemy.KIND , Vector2>> inputData) {
        List<Enemy> resultList = new ArrayList<Enemy>(inputData.size());
        for (Pair<Enemy.KIND , Vector2> pair : inputData) {
            Enemy enemy = produceEnemy(screen, pair);
            if (enemy != null) {
                resultList.add(enemy);
            }
        }
        return resultList;
    }

    private Enemy produceEnemy(PlayScreen screen, Pair<Enemy.KIND , Vector2> inputData) {
        switch (inputData.getA()) {
            case DARK_KNIGHT:
                return new DarkKnight(screen, inputData.getB());
            default:
                return null;
        }
    }
}
