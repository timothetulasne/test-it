package com.example.testit.repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;

@SpringBootTest
public class BookRepositoryIT {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired 
    private MemberRepository memberRepository;
    
    @Test
    void verifierRenvoiFonctionMembreEtStatus() {
        Book book1 = new Book("A", "a");
        Book book2 = new Book("B", "b");

        Member membre1 = new Member("U");
        Member membre2 = new Member("V");

        membre1.setId(1L);
        membre2.setId(2L);

        memberRepository.save(membre1);
        memberRepository.save(membre2);

        book1.setStatus(Status.EMPRUNTE);
        book1.setBorrower(membre1);

        book2.setStatus(Status.DISPONIBLE);

        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> books1 = bookRepository.findByMemberAndStatus(2L, Status.DISPONIBLE);

        List<Book> books2 = bookRepository.findByMemberAndStatus(1L, Status.DISPONIBLE);

        List<Book> books3 = bookRepository.findByMemberAndStatus(1L, Status.EMPRUNTE);

        assertEquals(0, books1.size()); // faux car requete code avec un or et donc renvoie une liste avec un livre
        assertEquals(0, books2.size()); // faux car mm probleme
        assertEquals(1, books3.size()); // faux car mm probleme
    }
        
}
