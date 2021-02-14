package org.alex.railway.tickets.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="stations",
        uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Station {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "arriveStation")
    public List<Train> trainsArriving;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "departStation")
    public List<Train> trainsDeparting;


    @Override
    public String toString() {
        return name;
    }
}
