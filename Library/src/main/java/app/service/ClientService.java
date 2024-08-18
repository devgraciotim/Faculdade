package app.service;

import app.entity.Client;
import app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public String saveClient(Client client){
        this.clientRepository.save(client);
        return (
                "Cliente " + client.getName() + " salvo com sucesso!"
        );
    }

    public List<Client> findAllClients(){
        List<Client> clients = this.clientRepository.findAll();
        return clients;
    }

    public Client findClientById(Long id){
        Client client = this.clientRepository.findById(id).get();
        return client;
    }

    public String updateClient(Long id, Client client){
        client.setId(id);
        this.clientRepository.save(client);
        return (
                "Cliente " + client.getName() + " atualizado com sucesso!"
        );
    }

    public String deleteClient(Long id){
        this.clientRepository.deleteById(id);
        return (
                "Cliente com id " + id + " deletado com sucesso"
        );
    }
}
