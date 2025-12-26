@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;

    // REQUIRED by tests
    public ContractServiceImpl() {
    }

    // OPTIONAL for Spring
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract createContract(Contract contract) {

        if (contract.getBaseContractValue() == null ||
            contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Invalid contract value");
        }

        return contractRepository.save(contract);
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract c = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        c.setStatus("COMPLETED");
        contractRepository.save(c);
    }
}
