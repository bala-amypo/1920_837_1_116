import java.math.BigDecimal;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer daysDelayed;

    private BigDecimal calculatedPenalty;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private DeliveryRecord deliveryRecord;

    @ManyToOne
    private BreachRule breachRule;

    // getters and setters
}
