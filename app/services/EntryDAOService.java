
package services;

import models.Entry;
import dao.EntryDAO;
import play.Logger;

public class EntryDAOService implements DAOService {

    final Logger.ALogger logger = Logger.of(this.getClass());

    EntryDAO dao;
    Entry e;

    /**
      * create new Entry
      * Receive title and content form Form 
      * @param description: description of new task
      */ 
    public void createEntryFromForm() {
        
        logger.debug("create new Entry from form");

        //TODO
        //if (description == null || description.trim().equals(""))
        //    throw new IllegalStateException("No Description for Task available.");

        this.e = new Entry();
        //this.e.description = description;

        logger.debug("delegation to EntryDAO"); 

        this.dao = new EntryDAO();
        //this.dao.create(e);

    }

}