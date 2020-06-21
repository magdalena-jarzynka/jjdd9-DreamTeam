package com.infoshareacademy.dreamteam.domain.entity;


import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Rating.findByBookId",
                query = "SELECT r FROM Rating r WHERE r.book.id=:bookId")
})
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberOfVotes;
    private Long sumOfVotes;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Long getSumOfVotes() {
        return sumOfVotes;
    }

    public void setSumOfVotes(Long sumOfVotes) {
        this.sumOfVotes = sumOfVotes;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
