package com.example.domain.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class Loan {
    private final UUID id;
    private final UUID clientId;
    private final UUID bookId;
    private final ZonedDateTime loanDate;
    private final ZonedDateTime dueDate;
    private final BigDecimal loanPrice;
    private final BigDecimal dailyFine;
    private ZonedDateTime returnDate;

    public Loan(UUID id,
                UUID clientId,
                UUID bookId,
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

    public Loan(UUID clientId,
                UUID bookId,
                ZonedDateTime loanDate,
                ZonedDateTime dueDate,
                ZonedDateTime returnDate,
                BigDecimal loanPrice,
                BigDecimal dailyFine
    ) {
        this(UUID.randomUUID(), clientId, bookId, loanDate, dueDate, returnDate, loanPrice, dailyFine);
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

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getBookId() {
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
