@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "ruleName"))
public class BreachRule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private BigDecimal penaltyPerDay;
    private Double maxPenaltyPercentage;
    private Boolean active;
    private Boolean isDefaultRule;
}
