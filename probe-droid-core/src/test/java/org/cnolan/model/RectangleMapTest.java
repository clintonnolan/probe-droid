package org.cnolan.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RectangleMapTest {
    private RectangleMap sampleMap = new RectangleMap(5,7);

    @Test
    void pointOnMapIsOnMap(){
        assertTrue(sampleMap.isCoordinateOnMap(2, 4));
    }

    @Test
    void XLessThanZeroIsOffMap(){
        assertFalse(sampleMap.isCoordinateOnMap(-1, 4));
    }

    @Test
    void YLessThanZeroIsOffMap(){
        assertFalse(sampleMap.isCoordinateOnMap(2, -1));
    }

    @Test
    void XGreaterThanWidthIsOffMap(){
        assertFalse(sampleMap.isCoordinateOnMap(6, 4));
    }

    @Test
    void YGreaterThanHeightIsOffMap(){
        assertFalse(sampleMap.isCoordinateOnMap(2, 8));
    }

    @Test
    void XOfZeroIsOnMap(){
        assertTrue(sampleMap.isCoordinateOnMap(0, 4));
    }

    @Test
    void YOfZeroIsOnMap(){
        assertTrue(sampleMap.isCoordinateOnMap(2, 0));
    }

    @Test
    void XEqualWidthIsOffMap(){
        assertFalse(sampleMap.isCoordinateOnMap(5, 4));
    }

    @Test
    void YEqualHeightIsOffMap(){
        assertFalse(sampleMap.isCoordinateOnMap(2, 7));
    }

    @Test
    void XOneLessThanWidthIsOnMap(){
        assertTrue(sampleMap.isCoordinateOnMap(4, 4));
    }

    @Test
    void YOneLessThanHeightIsOnMap(){
        assertTrue(sampleMap.isCoordinateOnMap(2, 6));
    }
}
