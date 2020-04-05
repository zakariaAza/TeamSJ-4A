package com.esiea.tp4A.domain;

import java.util.Set;

public interface PlanetMap {

    Set<Obstacle> obstaclePositions();
    void removeObstacle(Obstacle obstacle);
    int getSize();
    int getLimit_pos();
    int getLimit_neg();
    boolean isObstacle(Position temp_position);
}
