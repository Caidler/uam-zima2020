package pl.psi.battleengine.creatures;

import com.google.common.collect.Range;
import lombok.Builder;
import pl.psi.CreatureStatistic;
import pl.psi.battleengine.move.GuiTileIf;

public class CreatureStack implements GuiTileIf {


    private boolean counterAttackedInThisTurn;
    private int currentHp;
    private int amount;
    private final CreatureStatistic statistic;
    private DealDamageStrategyIf dealDamageStrategy;


    @Builder
    public CreatureStack(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, int aMoveRange) {
        statistic = CreatureStatistic.builder().aName(aName).aMaxHp(aMaxHp).aAttack(aAttack).aDefence(aDefence).aMoveRange(aMoveRange).build();
        currentHp = statistic.getMaxHp();
        dealDamageStrategy = new DefaultDamageStrategy();
    }

    public CreatureStack(CreatureStatistic aStatistic, Integer aAmount) {
        amount = aAmount;
        statistic = aStatistic;
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
        return getStatistic().getAttack();
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
