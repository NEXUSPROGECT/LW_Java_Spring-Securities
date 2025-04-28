package spring.n4d3sh1k4.securities.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tikers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tiker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_tiker")
    private String nameTiker;

    @Column(name = "deal_place")
    private String dealPlace;
}