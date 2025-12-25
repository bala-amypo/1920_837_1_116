@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository breachRuleRepository;

    public BreachRuleServiceImpl() {}

    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }
}
