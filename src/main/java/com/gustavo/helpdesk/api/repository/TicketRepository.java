package com.gustavo.helpdesk.api.repository;

import com.gustavo.helpdesk.api.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;

public interface TicketRepository extends MongoRepository<Ticket, String> {


    Page<Ticket> findByUserIdOrderByDateDesc(Pageable pages, String id);

    Page<Ticket> findByTitleIgnoringCaseContainingAndStatusAndPriorityOrderByDateDesc(
            Pageable pages, String title, String priority, String status);

    Page<Ticket> findByTitleIgnoringCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(
            Pageable pages, String title, String priority, String status, String userId);

    Page<Ticket> findByTitleIgnoringCaseContainingAndStatusAndPriorityit AndAssignedUserIdOrderByDateDesc(
            Pageable pages, String title, String priority, String status, String assignedUserId);

    Page<Ticket> findByNumber(Integer number, Pageable pages);
}
