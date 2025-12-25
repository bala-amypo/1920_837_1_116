@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    public ContractServiceImpl() {
        // required for tests
    }

    public ContractServiceImpl(
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }
}
