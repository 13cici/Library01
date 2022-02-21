package Models;

import java.util.List;
import java.util.UUID;

public interface BuyerRepository {
    Buyer get(UUID id);
    void add(Buyer buyer);
    boolean update(Buyer buyer);
    void remove(Buyer buyer);
    List<Buyer> getAllBuyers();
}