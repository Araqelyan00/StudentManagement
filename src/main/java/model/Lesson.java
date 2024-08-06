package model;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private String name;
    private String teacherName;
    private int duration;
    private double price;
    private Date startDate;
}
