package com.healthsystem.dao;

import com.healthsystem.entity.Billing;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BillingDAO {
    private  List<Billing> billings;

    public BillingDAO() {
        this.billings = new ArrayList<>();
    }

    public void addBilling(Billing billing) {
        billings.add(billing);
    }

    public List<Billing> getAllBillings() {
        return new ArrayList<>(billings);
    }

    public Billing getBillingById(String id) {
        Optional<Billing> billing = billings.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        if (billing.isPresent()) {
            return billing.get();
        } else {
            throw new HealthSystemException("Billing record not found", Response.Status.NOT_FOUND);
        }
    }

    public void updateBilling(String id, Billing updatedBilling) {
        Billing existing = getBillingById(id);
        int index = billings.indexOf(existing);
        billings.set(index, updatedBilling);
    }

    public void deleteBilling(String id) {
        Billing existing = getBillingById(id);
        billings.remove(existing);
    }
}
