@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    // REQUIRED FOR TEST
    public ContractServiceImpl() {}

    public ContractServiceImpl(ContractRepository contractRepository,
                               DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public void updateContract(Long id, Contract contract) {
        contract.setId(id);
        contractRepository.save(contract);
    }
}
