@Entity
public class DeliveryRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Contract contract;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    private String notes;
    private Timestamp createdAt;
}
