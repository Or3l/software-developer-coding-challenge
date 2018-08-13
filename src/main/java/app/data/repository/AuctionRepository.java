package app.data.repository;

import app.data.model.Auction;
import app.data.model.AuctionImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends CrudRepository<AuctionImpl, Long> {
    @Query()
    Auction findByItemId(long itemId);
}
