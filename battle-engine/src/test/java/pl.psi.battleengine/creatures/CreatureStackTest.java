package pl.psi.battleengine.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatureStackTest {

    private CreatureStack centaur;
    private CreatureStack imp;

    @BeforeEach
    void init(){
        centaur = CreatureStack.builder()
                .aName("centaur")
                .aMaxHp(10)
                .aAttack(Range.closed(5,10))
                .aDefence(3)
                .build();
        imp = new CreatureStack("imp", 5,Range.closed(2,10),2, 0);
    }

    @Test
    void attackedImpShouldLostThreeHp(){
        //given

        //when
        centaur.attack(imp);
        //then
        assertEquals(2, imp.getCurrentHp());
    }

    @Test
    void attackedCentaurShouldLost(){
        imp.attack(centaur);

        assertEquals(9, centaur.getCurrentHp());
    }

    @Test
    void attackedCentaurShouldCounterAttack(){
        imp.attack(centaur);

        assertEquals(2, imp.getCurrentHp());
    }

    @Test
    void defenderShouldCounterAttackOnlyOnce(){
        imp.attack(centaur);
        imp.attack(centaur);

        assertEquals(2, imp.getCurrentHp());
    }

    @Test
    void shooterCouldNotBeCaunterAttacked(){
        ShootingCreatureStack shooter = new ShootingCreatureStack("centaur",10,Range.closed(5,10),3, 0);
        shooter.attack(imp);

        assertEquals(shooter.getMaxHp(), shooter.getCurrentHp());
    }

    @Test
    void randomStrategyTest(){

    }
}