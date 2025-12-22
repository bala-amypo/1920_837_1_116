public interface VendorTierRepository extends JpaRepository<VendorTier, Long> {
    Optional<VendorTier> findByActiveTrueOrderByMinScoreThresholdDesc();
    boolean existsByTierName(String name);
}
