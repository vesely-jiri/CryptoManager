package cz.jpcz.cryptomanager.mapper;

import cz.jpcz.cryptomanager.dto.CryptoRequestDTO;
import cz.jpcz.cryptomanager.dto.CryptoResponseDTO;
import cz.jpcz.cryptomanager.model.Crypto;

import java.util.List;

public class CryptoMapper {
    public static CryptoResponseDTO toResponseDTO(Crypto entity) {
        return new CryptoResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getSymbol(),
                entity.getPrice(),
                entity.getQuantity());
    }

    public static Crypto toEntity(CryptoRequestDTO dto) {
        return new Crypto(
                dto.getName(),
                dto.getSymbol(),
                dto.getPrice(),
                dto.getQuantity());
    }

    public static List<CryptoResponseDTO> toResponseDTO(List<Crypto> cryptos) {
        return cryptos.stream().map(CryptoMapper::toResponseDTO).toList();
    }
}
