package app.service;

import app.entity.Client;
import app.entity.Employee;
import app.entity.Piece;
import app.entity.Sale;
import app.repository.ClientRepository;
import app.repository.EmployeeRepository;
import app.repository.PieceRepository;
import app.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PieceRepository pieceRepository;

    public String saveSale(Sale sale) {
        Client client = this.clientRepository.findById(sale.getClient().getId()).get();
        sale.setClient(client);

        if (sale.getEmployee() != null) {
            Employee employee = this.employeeRepository.findById(sale.getEmployee().getId()).get();
            sale.setEmployee(employee);
        }

        List<Piece> items = new ArrayList<>();
        Double total_value = 0.0;
        // Iterar pelos itens fornecidos na venda, buscar cada Piece no banco de dados, somar o preço ao total e adicionar à lista
        for (Piece item : sale.getItems()) {
            Piece piece = this.pieceRepository.findById(item.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

            // Somar o preço da peça ao valor total
            total_value += piece.getPrice();

            // Adicionar a peça à lista de itens da venda
            items.add(piece);
        }

        sale.setItems(items);
        sale.setTotal_value(total_value);


        if (sale.getClient().getAge() < 18 && sale.getTotal_value() > 500.0) {
            return "Cliente menor de 18 anos não pode fazer compras de mais de 500.00";
        }

        this.saleRepository.save(sale);
        return (
                "Venda salva com sucesso!"
        );
    }

    public List<Sale> findAllSales() {
        List<Sale> sales = this.saleRepository.findAll();
        return sales;
    }

    public Sale findSaleById(Long id) {
        Sale sale = this.saleRepository.findById(id).get();
        return sale;
    }

    public String updateSale(Long id, Sale sale) {
        sale.setId(id);
        this.saleRepository.save(sale);
        return (
                "Venda atualizada com sucesso!"
        );
    }

    public String deleteSale(Long id) {
        this.saleRepository.deleteById(id);
        return (
                "Venda com id " + id + " deletada com sucesso"
        );
    }
}
