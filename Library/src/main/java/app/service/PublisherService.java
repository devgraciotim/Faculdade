package app.service;

import app.entity.Publisher;
import app.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public String savePublisher(Publisher publisher){
        this.publisherRepository.save(publisher);
        return (
                "Editora " + publisher.getName() + " salva com sucesso!"
        );
    }

    public List<Publisher> findAllPublishers(){
        List<Publisher> publishers = this.publisherRepository.findAll();
        return publishers;
    }

    public Publisher findPublisherById(Long id){
        Publisher publisher = this.publisherRepository.findById(id).get();
        return publisher;
    }

    public String updatePublisher(Long id, Publisher publisher){
        publisher.setId(id);
        this.publisherRepository.save(publisher);
        return (
                "Editora " + publisher.getName() + " atualizada com sucesso!"
        );
    }

    public String deletePublisher(Long id){
        this.publisherRepository.deleteById(id);
        return (
                "Editora com id " + id + " deletada com sucesso"
        );
    }
}
