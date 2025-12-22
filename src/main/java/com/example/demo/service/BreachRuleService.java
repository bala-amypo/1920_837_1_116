@Service
public class BreachRuleService {

    private final BreachRuleRepository breachRuleRepository;

    public BreachRuleService(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> 
                    new ResourceNotFoundException("No active breach rule"));
    }
}
