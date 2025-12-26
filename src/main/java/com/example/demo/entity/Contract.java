import java.math.BigDecimal;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal baseContractValue;

    private LocalDate agreedDeliveryDate;

    private String status;

    // getters and setters
}
