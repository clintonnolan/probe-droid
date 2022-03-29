package org.cnolan.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.cnolan.simulation.SimulationState;
import org.junit.jupiter.api.Test;

public class DroidLeftInstructionTest {

    //TODO: rewrite these to not use mocks inappropriately
    @Test
    void whenTurnLeftAtThreeThenEndAtTwo(){
        Droid mockDroid = mock(Droid.class);
        when(mockDroid.getDirection()).thenReturn(3);
        SimulationState mockState = mock(SimulationState.class);
        DroidLeftInstruction instruction = new DroidLeftInstruction(mockDroid);
        instruction.performAction(mockState);
        verify(mockDroid).setDirection(2);
    }

    @Test
    void whenTurnLeftAtZeroThenEndAtThree(){
        Droid mockDroid = mock(Droid.class);
        when(mockDroid.getDirection()).thenReturn(0);
        SimulationState mockState = mock(SimulationState.class);
        DroidLeftInstruction instruction = new DroidLeftInstruction(mockDroid);
        instruction.performAction(mockState);
        verify(mockDroid).setDirection(3);
    }
}
