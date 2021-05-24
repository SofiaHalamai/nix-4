package ua.com.alevel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Route {

    private final int id;
    private final int fromId;
    private final int toId;
    private final int cost;
}
