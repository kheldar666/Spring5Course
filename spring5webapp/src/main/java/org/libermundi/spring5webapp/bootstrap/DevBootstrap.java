package org.libermundi.spring5webapp.bootstrap;

import org.libermundi.spring5webapp.model.Author;
import org.libermundi.spring5webapp.model.Book;
import org.libermundi.spring5webapp.model.Publisher;
import org.libermundi.spring5webapp.repositories.AuthorRepository;
import org.libermundi.spring5webapp.repositories.BookRepository;
import org.libermundi.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        //Eric
        Author eric = new Author( "Eric", "Evans" );
        Publisher hc = new Publisher("Harper Collins");
        Book ddd = new Book( "Domain Driven Design", "1234", hc);
        ddd.getAuthors().add(eric);

        publisherRepository.save(hc);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod","Johnson");
        Publisher wx = new Publisher("Worx");
        Book noEJB = new Book("J2EE Development without EJB", "2345", wx);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(wx);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
