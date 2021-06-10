package ua.com.alevel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupDto {

    private Integer id;

    private String name;

    private int avgMark;
}
