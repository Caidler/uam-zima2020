package pl.psi.battleengine;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.battleengine.creatures.CreatureStack;


import static org.junit.jupiter.api.Assertions.*;

class SpellBookTest {

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
    void ImpShouldHaveAttackIncreased(){
        // before buff
        assertEquals(2, imp.getAttack().lowerEndpoint());
        // after buff
        //BUFF
        assertEquals(3, imp.getAttack().lowerEndpoint());
    }
}