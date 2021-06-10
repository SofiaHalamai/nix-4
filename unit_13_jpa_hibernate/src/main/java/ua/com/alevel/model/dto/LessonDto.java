package ua.com.alevel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LessonDto {

    private Integer id;

    private String name;

    private Date dateAndTime;

}
