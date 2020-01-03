package pl.psi.battleengine.spellbook;

import java.awt.*;

public class BuffAttackSpell implements AttackBuffIf{

    @Override
    public int getBuff(int attack) {
        return 2;
    }

    @Override
    public int howManyTurns() {
        return 2;
    }

    @Override
    public String name() {
        return "Cos";
    }

    @Override
    public Point point() {
        return null;
    }
}
