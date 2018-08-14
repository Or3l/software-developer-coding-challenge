package app.data.repository;

import app.data.model.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, Long> {
    Optional<Auction> findByItemId(long itemId);
}
