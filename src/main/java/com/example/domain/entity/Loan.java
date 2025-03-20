package com.example.domain.entity;

import com.example.domain.value.LoanStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class Loan {
    private final String id;
    private final String clientId;
    private final String bookId;
    private final ZonedDateTime loanDate;
    private final ZonedDateTime dueDate;
    private final BigDecimal loanPrice;
    private final BigDecimal dailyFine;
    private ZonedDateTime returnDate;

    public Loan(String id,
                String clientId,
                String bookId,
                ZonedDateTime loanDate,
                ZonedDateTime dueDate,
                ZonedDateTime returnDate,
                BigDecimal loanPrice,
                BigDecimal dailyFine
    ) {
        Objects.requireNonNull(id, "Loan id must not be null");
        Objects.requireNonNull(clientId, "Loan clientId must not be null");
        Objects.requireNonNull(bookId, "Loan bookId must not be null");
        Objects.requireNonNull(loanDate, "Loan loanDate must not be null");
        Objects.requireNonNull(dueDate, "Loan dueDate must not be null");
        Objects.requireNonNull(loanPrice, "Loan loanPrice must not be null");
        Objects.requireNonNull(dailyFine, "Loan dailyFine must not be null");

        this.id = id;
        this.clientId = clientId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.loanPrice = loanPrice;
        this.dailyFine = dailyFine;
    }

    public Loan(String clientId,
                String bookId,
                ZonedDateTime loanDate,
                ZonedDateTime dueDate,
                ZonedDateTime returnDate,
                BigDecimal loanPrice,
                BigDecimal dailyFine
    ) {
        this(UUID.randomUUID().toString(), clientId, bookId, loanDate, dueDate, returnDate, loanPrice, dailyFine);
    }

    public LoanStatus getStatus() {
        if (returnDate != null) {
            return LoanStatus.RETURNED;
        }

        if (ZonedDateTime.now().isAfter(dueDate)) {
            return LoanStatus.OVERDUE;
        }

        return LoanStatus.PENDING;
    }

    public BigDecimal getFine() {
        var returnDate = this.returnDate != null ? this.returnDate : ZonedDateTime.now();
        var daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
        return dailyFine.multiply(BigDecimal.valueOf(daysOverdue));
    }

    public void returnBook() {
        if (returnDate != null) {
            throw new IllegalStateException("Book already returned");
        }

        returnDate = ZonedDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getBookId() {
        return bookId;
    }

    public ZonedDateTime getLoanDate() {
        return loanDate;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public ZonedDateTime getReturnDate() {
        return returnDate;
    }

    public BigDecimal getLoanPrice() {
        return loanPrice;
    }

    public BigDecimal getDailyFine() {
        return dailyFine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id.equals(loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
