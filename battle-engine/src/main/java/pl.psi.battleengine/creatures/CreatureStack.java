package pl.psi.battleengine.creatures;

import com.google.common.collect.Range;
import lombok.Builder;
import pl.psi.CreatureStatistic;
import pl.psi.battleengine.move.GuiTileIf;
import pl.psi.battleengine.spellbook.AttackBuffIf;
import pl.psi.battleengine.spellbook.SpellIf;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class CreatureStack implements GuiTileIf {


    private boolean counterAttackedInThisTurn;
    private int currentHp;
    private int amount;
    private final CreatureStatistic statistic;
    private DealDamageStrategyIf dealDamageStrategy;
    private List<AttackBuffIf> attackSpells;
    private List<SpellIf> spells;


    @Builder
    public CreatureStack(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, int aMoveRange) {
        statistic = CreatureStatistic.builder().aName(aName).aMaxHp(aMaxHp).aAttack(aAttack).aDefence(aDefence).aMoveRange(aMoveRange).build();
        currentHp = statistic.getMaxHp();
        dealDamageStrategy = new DefaultDamageStrategy();
        attackSpells = new Vector<AttackBuffIf>();
        spells = new Vector<SpellIf>();
    }

    public CreatureStack(CreatureStatistic aStatistic, Integer aAmount) {
        amount = aAmount;
        statistic = aStatistic;
        attackSpells = new LinkedList<AttackBuffIf>();
    }

    public void addSpell(AttackBuffIf aBuff){
        attackSpells.add(aBuff);
        spells.add(aBuff);
    }

    void attack(CreatureStack aDefender) {
        dealDamage(aDefender);
        aDefender.counterAttack(this);
    }

    private void counterAttack(CreatureStack aDefender){
        if(!counterAttackedInThisTurn){
            dealDamage(aDefender);
            counterAttackedInThisTurn = true;
        }
    }

    protected void dealDamage(CreatureStack aDefender) {
        int damage = dealDamageStrategy.dealDamage(this, aDefender);
        aDefender.currentHp -= damage;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public CreatureStatistic getStatistic() {
        return statistic;
    }

    public double getAttackRange() {
        return 1.0;
    }

    public int getMaxHp() {
        return getStatistic().getMaxHp();
    }

    public int getDefence() {
        return getStatistic().getDefence();
    }

    public Range<Integer> getAttack() {

        int base_lower_attack = getStatistic().getAttack().lowerEndpoint();
        int base_upper_attack = getStatistic().getAttack().upperEndpoint();
        int attack_low = base_lower_attack;
        int attack_up = base_upper_attack;

        for(AttackBuffIf buff : attackSpells){

            attack_low += buff.getBuff(base_lower_attack);
            attack_up += buff.getBuff(base_upper_attack);

        }

        return Range.closed(attack_low, attack_up);

    }

    public String getName() {
        return getStatistic().getName();
    }

    public int getMoveRange() {
        return getStatistic().getMoveRange();
    }

    @Override
    public String getIcon() {
        return getStatistic().getName();
    }
}
