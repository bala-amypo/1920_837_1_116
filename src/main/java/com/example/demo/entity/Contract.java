package com.example.demo.entity;

@Entity
@Table(name = "contracts", uniqueConstraints = @UniqueConstraint(columnNames = "contractNumber"))
public class Contract {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contractNumber;

    private String title;
    private String counterpartyName;

    @Temporal(TemporalType.DATE)
    private Date agreedDeliveryDate;

    @Column(nullable = false)
    private BigDecimal baseContractValue;

    private String status; // ACTIVE, COMPLETED, BREACHED

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
