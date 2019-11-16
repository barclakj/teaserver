package io.realizr.teaserver.model;


import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Represents an Task record as it may be in a database.
 * Note that the reports relationship is from child to parent here.
 */
public class Task {
    @NotNull
    private int id;
    @NotNull
    private String name;
    private String due;
    private Integer priority;
    private Long createdTs;
    private Long dueTs;
    private Set<String> topics;
}
