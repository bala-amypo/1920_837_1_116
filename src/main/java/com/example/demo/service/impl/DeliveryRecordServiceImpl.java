@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private DeliveryRecordRepository deliveryRecordRepository;
    private ContractRepository contractRepository;

    public DeliveryRecordServiceImpl() {}

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository deliveryRecordRepository,
            ContractRepository contractRepository) {
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.contractRepository = contractRepository;
    }
}
