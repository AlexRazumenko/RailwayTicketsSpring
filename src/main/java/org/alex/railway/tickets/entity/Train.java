package org.alex.railway.tickets.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table( name="trains",
        uniqueConstraints={@UniqueConstraint(columnNames={"number"})})
public class Train {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn (name = "departstation_id")
//    @Column(name = "departstation", nullable = false)
    private Station departStation;

    @ManyToOne
    @JoinColumn (name = "arrivestation_id")
//    @Column(name = "arrivestation", nullable = false)
    private Station arriveStation;

    @DateTimeFormat (iso = DateTimeFormat.ISO.TIME)
    @Column(name = "departtime", nullable = false)
    private LocalTime departTime;

    @DateTimeFormat (iso = DateTimeFormat.ISO.TIME)
    @Column(name = "arrivetime", nullable = false)
    private LocalTime arriveTime;

    @Column(name = "price", nullable = false)
    private int price;

}
