package ua.com.alevel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Problem {

    private final int id;
    private final int fromId;
    private final int toId;
}
