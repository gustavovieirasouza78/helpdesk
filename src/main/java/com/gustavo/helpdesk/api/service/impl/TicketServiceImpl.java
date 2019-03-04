package com.gustavo.helpdesk.api.service.impl;

import com.gustavo.helpdesk.api.entity.ChangeStatus;
import com.gustavo.helpdesk.api.entity.Ticket;
import com.gustavo.helpdesk.api.repository.ChangeStatusRepository;
import com.gustavo.helpdesk.api.repository.TicketRepository;
import com.gustavo.helpdesk.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ChangeStatusRepository changeStatusRepository;

    @Override
    public Ticket createOrUpdate(Ticket ticket) {
        return this.ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(String id) {
        return this.ticketRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        this.ticketRepository.deleteById(id);
    }

    @Override
    public Page<Ticket> listTicket(int page, int count) {
        return this.ticketRepository.findAll(PageRequest.of(page, count));
    }

    @Override
    public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {
        return this.changeStatusRepository.insert(changeStatus);
    }

    @Override
    public Iterable<ChangeStatus> listChangeStatus(String ticketId) {
        return this.changeStatusRepository.findByTicketIdOrderByDateChangeStatusDesc(ticketId);
    }

    @Override
    public Page<Ticket> findByCurrentUser(int page, int count, String userId) {
        return this.ticketRepository.findByUserIdOrderByDateDesc(PageRequest.of(page, count), userId);
    }

    @Override
    public Page<Ticket> findByParameters(int page, int count, String title, String status, String priority) {
        return this.ticketRepository.findByTitleIgnoringCaseContainingAndStatusContainingAndPriorityContainingOrderByDateDesc(
                PageRequest.of(page, count),title,status,priority);
    }

    @Override
    public Page<Ticket> findByParametersAndCurrentUser(int page, int count, String title, String status, String priority, String userId) {
        return this.ticketRepository.findByTitleIgnoringCaseContainingAndStatusContainingAndPriorityContainingAndUserIdOrderByDateDesc(
                PageRequest.of(page, count), title, status, priority, userId);
    }

    @Override
    public Page<Ticket> findByNumber(int page, int count, Integer number) {
        return this.ticketRepository.findByNumber(number, PageRequest.of(page, count));
    }

    @Override
    public Iterable<Ticket> findAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Page<Ticket> findByParametersAndAssignedUser(int page, int count, String title, String status, String priority, String assignedUserId) {
        return this.ticketRepository.findByTitleIgnoringCaseContainingAndStatusContainingAndPriorityContainingAndAssignedUserIdOrderByDateDesc(
                PageRequest.of(page, count), title, priority, status, assignedUserId);
    }
}
