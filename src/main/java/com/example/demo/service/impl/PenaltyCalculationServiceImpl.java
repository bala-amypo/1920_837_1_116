@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;
    private BreachRuleRepository breachRuleRepository;

    public PenaltyCalculationServiceImpl() {}

    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository penaltyCalculationRepository,
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository,
            BreachRuleRepository breachRuleRepository) {
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRuleRepository = breachRuleRepository;
    }
}
