@Entity
public class BreachReport {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private Timestamp reportGeneratedAt;
    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private String remarks;
}
