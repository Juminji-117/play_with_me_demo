package com.idea5.playwithme.event.domain.dto;

import com.idea5.playwithme.event.domain.Event;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private Integer categoryId;
    private String name;
    private String location;
    private LocalDateTime date;

}
