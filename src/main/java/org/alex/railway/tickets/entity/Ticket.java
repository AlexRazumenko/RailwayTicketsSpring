package org.alex.railway.tickets.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table (name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

@DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
@Column(name = "departure_date", nullable = false)
    private LocalDate departDate;

    @Column(name = "place", nullable = false)
    private int place;

    @Column(name = "occupied", nullable = false)
    private boolean occupied;





}
