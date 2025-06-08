package cz.jpcz.cryptomanager.repository;

import cz.jpcz.cryptomanager.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Integer> {

}
