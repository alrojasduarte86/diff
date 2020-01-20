package com.diff.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "DiffEntry")
@Table(name = "diff_entry")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiffEntry {

    @Id
    @Column(name="_id")
    private String id;

    @Lob
    @Column(name="left_data")
    private String leftData;

    @Lob
    @Column(name="right_data")
    private String rightData;
}
