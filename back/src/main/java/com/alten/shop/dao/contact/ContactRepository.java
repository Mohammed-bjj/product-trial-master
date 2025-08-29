package com.alten.shop.dao.contact;

import com.alten.shop.utils.entities.messaging.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> { }
