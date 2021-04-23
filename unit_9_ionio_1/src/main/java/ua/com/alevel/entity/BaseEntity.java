package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseEntity {

    private Integer id;
    private Boolean visible;

}
