package com.example.testit.service;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.testit.model.Book;
import com.example.testit.model.Member;
import com.example.testit.model.Status;
import com.example.testit.repository.BookRepository;
import com.example.testit.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    
    @Mock
    private BookRepository bookRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private BookService bookService;

    @Test 
    public void verifierEmpruntLivre() {
        //given
        Book book = new Book("H G", "S C");
        Member member = new Member("U");

        book.setId(1L);
        book.setStatus(Status.DISPONIBLE);

        member.setId(60L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(60L)).thenReturn(Optional.of(member));
        when(bookRepository.findByMemberAndStatus(60L,Status.RENDU)).thenReturn(new ArrayList<>());
        when(bookRepository.save(book)).thenReturn(book);
        //when
        Book result = bookService.borrowBook(1L, 60L);

        //then
        assertTrue(result.getStatus()==Status.EMPRUNTE,"Livre emprunté");       
        
    }
}
