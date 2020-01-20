package com.diff.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Holds the two pieces of data to be compared
 */
@Entity(name = "DiffEntry")
@Table(name = "diff_entry")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiffEntry {

    /**
     * The id of the piece of the diff
     */
    @Id
    @Column(name="_id")
    private String id;

    /**
     * The left piece of data to be compared
     */
    @Lob
    @Column(name="left_data")
    private String leftData;

    /**
     * The right piece of data to be compared
     */
    @Lob
    @Column(name="right_data")
    private String rightData;
}
