package spring.n4d3sh1k4.securities.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "fin_assets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tiker_id")
    private Tiker tiker;

    @Column(name = "registration")
    private String registration;

    @Column(name = "data_registration")
    private LocalDateTime dataRegistration; // Можно заменить на LocalDate, если нужна работа с датами

    @Column(name = "emitent")
    private String emitent;

    @Column(name = "form_issue")
    private String formIssue;

    @Column(name = "principal")
    private Double principal;

    @Column(name = "amount")
    private Integer amount;
}