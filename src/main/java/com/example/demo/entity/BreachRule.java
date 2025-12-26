import java.math.BigDecimal;

@Entity
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal penaltyPerDay;

    private Integer maxPenaltyPercentage;

    private Boolean active;

    private Boolean isDefaultRule;

    // getters and setters
}
