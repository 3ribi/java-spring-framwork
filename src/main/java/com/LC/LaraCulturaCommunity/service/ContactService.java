package com.LC.LaraCulturaCommunity.service;

import com.LC.LaraCulturaCommunity.config.LCProps;
import com.LC.LaraCulturaCommunity.constants.LCConstants;
import com.LC.LaraCulturaCommunity.model.Contact;
import com.LC.LaraCulturaCommunity.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



import java.util.List;



@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    LCProps lCProps;
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(LCConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);

        if(null != savedContact && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus(LCConstants.OPEN);
        return contactMsgs;
    }
    public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(
                LCConstants.OPEN,pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        int rows = contactRepository.updateMsgStatus(LCConstants.CLOSE,contactId);
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }

}
