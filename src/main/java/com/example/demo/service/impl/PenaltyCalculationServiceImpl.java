@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private PenaltyCalculationRepository penaltyRepo;
    private ContractRepository contractRepo;
    private DeliveryRecordRepository deliveryRepo;
    private BreachRuleRepository ruleRepo;

    // REQUIRED by tests
    public PenaltyCalculationServiceImpl() {
    }

    public PenaltyCalculationServiceImpl(
            PenaltyCalculationRepository p,
            ContractRepository c,
            DeliveryRecordRepository d,
            BreachRuleRepository b) {
        this.penaltyRepo = p;
        this.contractRepo = c;
        this.deliveryRepo = d;
        this.ruleRepo = b;
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {

        Contract contract = contractRepo.findById(contractId).orElseThrow();
        DeliveryRecord record = deliveryRepo.findFirstByContractIdOrderByDeliveryDateDesc(contractId).orElseThrow();
        BreachRule rule = ruleRepo.findFirstByActiveTrueOrderByIsDefaultRuleDesc().orElseThrow();

        long days = ChronoUnit.DAYS.between(
                contract.getAgreedDeliveryDate(),
                record.getDeliveryDate()
        );

        BigDecimal penalty =
                rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(days));

        BigDecimal max =
                contract.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage()))
                        .divide(BigDecimal.valueOf(100));

        if (penalty.compareTo(max) > 0) {
            penalty = max;
        }

        PenaltyCalculation pc = new PenaltyCalculation();
        pc.setDaysDelayed((int) days);
        pc.setCalculatedPenalty(penalty);
        pc.setContract(contract);
        pc.setDeliveryRecord(record);
        pc.setBreachRule(rule);

        return penaltyRepo.save(pc);
    }
}
