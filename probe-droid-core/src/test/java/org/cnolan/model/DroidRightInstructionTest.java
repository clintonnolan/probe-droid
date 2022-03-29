package org.cnolan.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.cnolan.simulation.SimulationState;
import org.junit.jupiter.api.Test;

public class DroidRightInstructionTest {

    //TODO: rewrite these to not use mocks inappropriately
    @Test
    void whenTurnRightAtThreeThenEndAtZero(){
        Droid mockDroid = mock(Droid.class);
        when(mockDroid.getDirection()).thenReturn(3);
        SimulationState mockState = mock(SimulationState.class);
        DroidRightInstruction instruction = new DroidRightInstruction(mockDroid);
        instruction.performAction(mockState);
        verify(mockDroid).setDirection(0);
    }

    @Test
    void whenTurnRightAtZeroThenEndAtOne(){
        Droid mockDroid = mock(Droid.class);
        when(mockDroid.getDirection()).thenReturn(0);
        SimulationState mockState = mock(SimulationState.class);
        DroidRightInstruction instruction = new DroidRightInstruction(mockDroid);
        instruction.performAction(mockState);
        verify(mockDroid).setDirection(1);
    }
}
