package com.esiea.tp4A;
import com.esiea.tp4A.domain.*;
import com.esiea.tp4A.roverApi.LocalMap;
import com.esiea.tp4A.roverApi.TheGame;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
public class RoverPositionTest {
    @Test
    void RoverPosition_test(){
        Mars mars = new Mars(100, Stream.of(new Obstacle(0,1)).collect(Collectors.toSet()));
        RoverPosition roverPosition = new RoverPosition( mars ) ;
        Position position = Position.of(0,50, Direction.NORTH);
        assertThat(roverPosition.commandSwitch('n', position)).as("CheckAA").isNull();
    }
}
