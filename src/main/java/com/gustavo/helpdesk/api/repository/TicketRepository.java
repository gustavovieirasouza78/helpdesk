package com.gustavo.helpdesk.api.repository;

import com.gustavo.helpdesk.api.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TicketRepository extends MongoRepository<Ticket, String> {


    Page<Ticket> findByUserIdOrderByDateDesc(Pageable pages, String id);

    Page<Ticket> findByTitleIgnoringCaseContainingAndStatusContainingAndPriorityContainingOrderByDateDesc(
            Pageable pages, String title, String priority, String status);

    Page<Ticket> findByTitleIgnoringCaseContainingAndStatusContainingAndPriorityContainingAndUserIdOrderByDateDesc(
            Pageable pages, String title, String priority, String status, String userId);

    Page<Ticket> findByTitleIgnoringCaseContainingAndStatusContainingAndPriorityContainingAndAssignedUserIdOrderByDateDesc(
            Pageable pages, String title, String priority, String status, String assignedUserId);

    Page<Ticket> findByNumber(Integer number, Pageable pages);
}
