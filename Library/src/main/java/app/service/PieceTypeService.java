package app.service;

import app.entity.PieceType;
import app.repository.PieceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceTypeService {
    @Autowired
    private PieceTypeRepository pieceTypeRepository;

    public String savePieceType(PieceType pieceType){
        this.pieceTypeRepository.save(pieceType);
        return (
                "Tipo  " + pieceType.getName() + " salvo com sucesso!"
        );
    }

    public List<PieceType> findAllPieceTypes(){
        List<PieceType> pieceTypes = this.pieceTypeRepository.findAll();
        return pieceTypes;
    }

    public PieceType findPieceTypeById(Long id){
        PieceType pieceType = this.pieceTypeRepository.findById(id).get();
        return pieceType;
    }

    public String updatePieceType(Long id, PieceType pieceType){
        pieceType.setId(id);
        this.pieceTypeRepository.save(pieceType);
        return (
                "Tipo " + pieceType.getName() + " atualizado com sucesso!"
        );
    }

    public String deletePieceType(Long id){
        this.pieceTypeRepository.deleteById(id);
        return (
                "Tipo com id " + id + " deletado com sucesso"
        );
    }
}
