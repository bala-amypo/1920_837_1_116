@Service
public class BreachReportServiceImpl implements BreachReportService {

    private BreachReportRepository breachReportRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;

    public BreachReportServiceImpl() {}

    public BreachReportServiceImpl(
            BreachReportRepository breachReportRepository,
            PenaltyCalculationRepository penaltyCalculationRepository,
            ContractRepository contractRepository) {
        this.breachReportRepository = breachReportRepository;
        this.penaltyCalculationRepository = penaltyCalculationRepository;
        this.contractRepository = contractRepository;
    }
}
