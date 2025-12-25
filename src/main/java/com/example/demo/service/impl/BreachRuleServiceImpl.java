@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    @Autowired
    private BreachRuleRepository repository;

    // REQUIRED BY TEST
    public BreachRuleServiceImpl() {}

    public BreachRuleServiceImpl(BreachRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        return repository.save(rule);
    }

    @Override
    public BreachRule getRuleById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
