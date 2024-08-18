package app.repository;

import app.entity.PieceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceTypeRepository extends JpaRepository<PieceType, Long> {
}
